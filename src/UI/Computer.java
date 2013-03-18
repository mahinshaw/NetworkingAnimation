package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a computer location within the animation
 */

public class Computer extends Node {
	
	PImage computer;

	public Computer(PApplet p, float x, float y, float w, float h) {
		super(p);
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
		computer = parent.loadImage("Computer1.jpg");
	}
	
	public Computer(PApplet p, PVector v, float w, float h) {
		super(p);
		super.setVector(v);
		super.setWidth(w);
		super.setHeight(h);
		computer = parent.loadImage("Computer1.jpg");
	}
	
	public String className(){
		return "Computer";
	}
	
	@SuppressWarnings("static-access")
	public void display(){
		super.display();
		parent.imageMode(parent.CENTER);
		parent.image(computer, position.x, position.y);
	}
}
