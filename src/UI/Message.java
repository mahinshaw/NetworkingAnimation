package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a message that travels from location to location
 */

public class Message extends Node{

	PVector start;
	PVector end;
	
	private PVector speed;
	
	private String message;
	private String binary;
	private String source;
	private String destination;
	private String type;
	private String crc;
	private String preamble;
	private String sfd;
	
	int color;
	
	public Message(PApplet p, float x, float y, float w, float h, int c, String m) {
		super(p);
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
		speed = new PVector((float)0.05, (float)0.05);
		color = c;
		message = m;
		calcBinary();
		source = "192.168.1.1";
		destination = "192.168.1.158";
		type = "21";
		crc = "";
		preamble = "";
		sfd = "";
	}
	
	public Message(PApplet p, PVector v, float w, float h, int c, String m) {
		super(p);
		super.setVector(v);
		super.setWidth(w);
		super.setHeight(h);
		speed = new PVector((float)0.05, (float)0.05);
		color = c;
		message = m;
		calcBinary();
		source = "192.168.1.1";
		destination = "192.168.1.158";
		type = "21";
		crc = "";
		preamble = "";
		sfd = "";
	}
	
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

	public void setSource(String s){
		source = s;
	}
	
	public void setDestination(String d){
		destination = d;
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
	
	public void display(){
		parent.fill(color);
		parent.strokeWeight(2);
		parent.ellipse(position.x, position.y, width, height);
	}
	
	public void calcBinary(){
		byte[] bytes = message.getBytes();
		StringBuilder binaryStr = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binaryStr.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
		}
		binary = binaryStr.toString();
	}
	
	public String getBinary(){
		return binary;
	}
}
