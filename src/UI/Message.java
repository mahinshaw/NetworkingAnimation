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
	private int frequency, amplitude;
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
		frequency = 30;
		amplitude = 100;
		shiftKey = true; // default = fsk
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
		frequency = 30;
		amplitude = 100;
		shiftKey = true; // default = fsk
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
	
	public int getAmplitude(){
		return amplitude;
	}
	
	public int getFrequency(){
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
	public void sineWave(int i, int x, int y){
		
		int freq, amp;
		if (shiftKey){
			// FSK
			// increment the incoming integer so that 1 = 2 and 0 = 1, to be used as multiples for frequency
			i++;
			freq = frequency * i; 
			parent.bezier(x - freq, y/2, x - (freq*2/3), y/2 - amplitude, x - (freq/3), y/2 + amplitude, x, y/2);
			
		}else{
			// ASK
			// increment the incoming integer so that 1 = 2 and 0 = 1, to be used as multiples for frequency
			i++;
			amp = amplitude * i;
			parent.bezier(x-frequency, y, x - (frequency *3/4), y - amp, x - (frequency / 4), y + amp, x, y/2);
		}
	}
	
	public void drawWave(int x, int y){
		int index;
		for( index = 0; index<binary.length(); index++){
			int i = Character.getNumericValue(binary.charAt(index));
			sineWave(i, x, y);
			/*
			 * take a look at the processing sketch that you did to finish the sineWave implementation.
			 */
			
		}
	}
}
