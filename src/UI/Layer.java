package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a layer location within the animation
 */

public class Layer extends Node {
	
	boolean current;
	int color;

	public Layer(PApplet p, float x, float y, float w, float h) {
		super();
		parent = p;
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
		current = false;
	}
	
	public void setColor(int c){
		color = c;
	}
	
	public int getColor(){
		return color;
	}
	
	public void display(){
		parent.stroke(255);
		parent.fill(color);
		parent.rectMode(parent.CENTER);
		parent.rect(position.x, position.y, width, height);
	}

}
