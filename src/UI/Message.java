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
	PVector stop;
	
	public Message(PApplet p, float x, float y, float w, float h) {
		super();
		parent = p;
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
	}
	
	public void display(){
		parent.fill(255);
		parent.strokeWeight(2);
		parent.ellipse(position.x, position.y, width, height);
	}

}
