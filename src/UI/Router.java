package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a router location within the anmination.
 */

public class Router extends Node {

	PImage router;
	
	public Router(PApplet p, float x, float y, float w, float h) {
		super();
		parent = p;
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
		router = parent.loadImage("Router1.jpg");
	}
	
	public Router(PApplet p, PVector v, float w, float h) {
		super();
		parent = p;
		super.setVector(v);
		super.setWidth(w);
		super.setHeight(h);
		router = parent.loadImage("Router1.jpg");
	}
	
	public String className(){
		return "Router";
	}
	
@SuppressWarnings("static-access")
public void display(){
		parent.imageMode(parent.CENTER);
		parent.image(router, position.x, position.y);
	}	
}
