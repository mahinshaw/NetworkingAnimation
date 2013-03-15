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
	
	PVector speed;
	
	String message;
	
	int color;
	
	public Message(PApplet p, float x, float y, float w, float h, int c, String m) {
		super();
		parent = p;
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
		speed = new PVector(3, 1);
		color = c;
		message = m;
	}
	
	public Message(PApplet p, PVector v, float w, float h, int c, String m) {
		super();
		parent = p;
		super.setVector(v);
		super.setWidth(w);
		super.setHeight(h);
		speed = new PVector(3, 1);
		color = c;
		message = m;
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
	
	public void display(){
		parent.fill(color);
		parent.strokeWeight(2);
		parent.ellipse(position.x, position.y, width, height);
	}

}
