package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a message that travels from location to location.  This method will contain all the information needed for display within the message.
 * All calculations are done locally and can be called for use in other classes.
 * 
 */

public class Message extends Node{

	PVector start;
	PVector end;
	
	private PVector speed;
	
	private String message;
	private String binary;
	private String source;
	private String destination;
	private String destMAC;
	private String sourMAC;
	private String type;
	private String crc;
	private String preamble;
	private String sfd;
	
	// sine wave variables
	private float frequency, amplitude;
	private boolean shiftKey;			// if true then fsk, else, ask
	
	int color;
	
	public Message(PApplet p, float x, float y, float w, float h, int c, String m) {
		super(p);
		setVector(x, y);
		setWidth(w);
		setHeight(h);
		speed = new PVector((float)0.05, (float)0.05);
		color = c;
		message = m;
		binary = calcBinary(message);
		source = "192.168.1.1";
		destination = "192.168.1.158";
		destMAC = "04:8F:F2:23";
		sourMAC = "06:4C:F3:4B";
		type = "21";
		crc = "";
		preamble = "101010101010101010101010101010101010101010";
		sfd = "10101011";
		frequency = 9;
		amplitude = 200;
		shiftKey = false; // default = fsk
	}
	
	public Message(PApplet p, PVector v, float w, float h, int c, String m) {
		super(p);
		setVector(v);
		setWidth(w);
		setHeight(h);
		speed = new PVector((float)0.05, (float)0.05);
		color = c;
		message = m;
		binary = calcBinary(message);
		source = "192.168.1.1";
		destination = "192.168.1.158";
		destMAC = "04:8F:F2:23";
		sourMAC = "06:4C:F3:4B";
		type = "21";
		crc = "";
		preamble = "101010101010101010101010101010101010101010";
		sfd = "10101011";
		frequency = 9;
		amplitude = 200;
		shiftKey = false; // default = fsk
	}
	
	/*
	 * begin get and set methods for global variables
	 */
	public PVector getStart(){
		return start;
	}
	
	public void setStart(PVector v){
		start = v.get();
	}
	
	public PVector getEnd(){
		return end;
	}
	
	public void setEnd(PVector v){
		end = v.get();
	}
	
	public PVector getSpeed(){
		return speed.get();
	}
	
	public void setSpeed(PVector s){
		speed = s.get();
	}
	
	public void setSpeed(float x, float y){
		speed.x = x;
		speed.y = y;
	}
	
	public int getColor(){
		return color;
	}
	
	public boolean getShiftKey(){
		return shiftKey;
	}
	
	public void setColor(int c){
		color = c;
	}
	
	public String getMessage(){
		return message;
	}
	
	public void setMessage(String m){
		message = m;
	}
	
	public String className(){
		return "Message";
	}
	
	public String getSource(){
		return source;
	}
	
	public String getDestination(){
		return destination;
	}
	
	public String getDestMAC(){
		return destMAC;
	}
	
	public String getSourMAC(){
		return sourMAC;
	}
	
	public String getType(){
		return type;
	}
	
	public String getCRC(){
		return crc;
	}
	
	public String getPreamble(){
		return preamble;
	}
	
	public String getSFD(){
		return sfd;
	}

	public String getBinary(){
		return binary;
	}
	
	public float getAmplitude(){
		return amplitude;
	}
	
	public float getFrequency(){
		return frequency;
	}
	
	public void setSource(String s){
		source = s;
	}
	
	public void setDestination(String d){
		destination = d;
	}
	
	public void setDestMAC(String d){
		destMAC = d;
	}
	
	public void setSourMAC(String s){
		sourMAC = s;
	}
	
	public void setType(String t){
		type = t;
	}
	
	public void setCRC(String c){
		crc = c;
	}
	
	public void setPreamble(String p){
		preamble = p;
	}
	
	public void setSFD(String s){
		sfd = s;
	}
	
	public void setBinary(String m){
		binary = calcBinary(m);
	}
	
	public void setAmplitude(int a){
		amplitude = a;
	}
	
	public void setFrequency(int f){
		frequency = f;
	}

	public void setShiftKey(boolean s){
		shiftKey = s;
	}
	/*
	 * end get and set methods
	 */
	
	public void display(){
		parent.fill(color);
		parent.strokeWeight(2);
		parent.ellipse(position.x, position.y, width, height);
	}
	
	private String calcBinary(String m){
		byte[] bytes = m.getBytes();
		StringBuilder binaryStr = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binaryStr.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		return binaryStr.toString();
	}
	
	/*
	 * This method takes in a bit (0 or 1 as an integer, and prints out a sine wave for that bit with FSK or ASK
	 * @param x and y -  this will be in the width and height of the rectangle to draw sine waves inside of
	 * @param i - this will be the bit value passed in from the String of bits(most likely the binary variable 
	 */
	public void sineWave(int i, float x, float y){
		
		float freq = frequency;
		float amp = amplitude;
		if (shiftKey){
			// FSK
			// increment the incoming integer so that 1 = 2 and 0 = 1, to be used as multiples for frequency
			parent.stroke(0, 0, 0);
			parent.strokeWeight(2);
			freq *= i; 
			parent.bezier(x, y, x + (freq/3), y - amp, x + (freq*2/3), y + amp, x + freq, y);
			
		}else{
			// ASK
			// increment the incoming integer so that 1 = 2 and 0 = 1, to be used as multiples for frequency
			parent.stroke(0, 0, 0);
			parent.strokeWeight(2);
			amp /= i;
			parent.bezier(x, y, x + (freq/3), y - amp, x + (freq*2/3), y + amp, x + freq, y);
		}
	}
	
	public void drawSineWave(float x, float y){
		int index;
		for( index = 0; index<binary.length(); index++){
			int i = Character.getNumericValue(binary.charAt(index));
			i++;
			sineWave(i, x, y);
			if(shiftKey){
				x += (frequency * i);
			}else{
				x += frequency;
			}
		}
	}
	
	public void digitalWave(int i, float x, float y, boolean next){
		parent.stroke(0, 0, 0);
		parent.strokeWeight(2);
		float h = amplitude/2;
		float l = frequency;
		
		if(i == 0 && next){
			parent.line(x, y, x+l, y);
		}else if(i == 0 && !next){
			parent.line(x, y, x+l, y);
			parent.line(x+l, y, x+l, y-h);
		}else if(i == 1 && next){
			parent.line(x, y-h, x+l, y-h);
		}else if (i == 1 && !next){
			parent.line(x, y-h, x+l, y-h);
			parent.line(x+l, y-h, x+l, y);
		}
	}
	
	public void drawDigitalWave(float x, float y){
		int index;
		boolean prev = false; //was the previous bit the same as the current
		boolean next = false; // is the next bit the same
		y += amplitude/4;
		for(index = 0; index < binary.length(); index++){
			int i = Character.getNumericValue(binary.charAt(index));
			if (index < binary.length()-1){
				if(binary.charAt(index) == binary.charAt(index+1)){
					next = true;
				}else{
					next = false;
				}
			}
			digitalWave(i, x, y, next);
			x += frequency;
		}
	}

}
