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
		super(p);
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
		router = parent.loadImage("Router1.jpg");
	}
	
	public Router(PApplet p, PVector v, float w, float h) {
		super(p);
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
		super.display();
		parent.imageMode(parent.CENTER);
		parent.image(router, position.x, position.y);
	}
	
	public void output(Message m){
		if (m.position.x >= (position.x - (width)) && m.position.x <= (position.x + (width))
				&& m.position.y >= (position.y - (height / 2))
				&& m.position.y <= (position.y + (height / 2))){
			float x = (parent.width / 2);
			float y = parent.map(700, 0, 900, 0, parent.height);
			parent.rectMode(parent.CENTER);

			PFont f = parent.createFont("Georgia", 16, true);
			parent.textFont(f);
			parent.fill(225);
			parent.rect(x, y, 600, 200);
		}
	}
}
