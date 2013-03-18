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
	
	int color;
	String name;
	String font;
	int point;
	boolean aa;
	PFont f;

	public Layer(PApplet p, float x, float y, float w, float h, String t, int c) {
		super(p);
		super.setVector(x, y);
		super.setWidth(w);
		super.setHeight(h);
		name = t;
		font = "Arial";
		point = 16;
		aa = true;
		f = parent.createFont(font, point, aa);
		color = c;
	}
	
	public Layer(PApplet p, PVector v, float w, float h, String t, int c) {
		super(p);
		super.setVector(v);
		super.setWidth(w);
		super.setHeight(h);
		name = t;
		font = "Georgia";
		point = 16;
		aa = true;
		f = parent.createFont(font, point, aa);
		color = c;
	}
	
	public void setColor(int c){
		color = c;
	}
	
	public int getColor(){
		return color;
	}
	
	public void setText(String t){
		name = t;
	}
	
	public void setFont(String s){
		font = s;
		f = parent.createFont(font, point, aa);
	}
	
	public void setPoint(int p){
		point = p;
		f = parent.createFont(font, point, aa); 
	}
	
	public void setAA(boolean x){
		aa = x;
		f = parent.createFont(font, point, aa);
	}
	
	public String className(){
		return "Layer";
	}
	
	@SuppressWarnings("static-access")
	public void display(int c){
		
		parent.stroke(0);
		parent.strokeWeight(2);
		parent.fill(c);
		parent.rectMode(parent.CENTER);
		parent.rect(position.x, position.y, width, height);
		
		parent.textFont(f);
		parent.fill(0);
		parent.textAlign(parent.CENTER, parent.CENTER);
		parent.text(name, position.x, position.y);
	}
	
	public void display(){
		display(color);
	}
	
	public void display(Message m){
		if (m.position.x == position.x && m.position.y >= (position.y - (height/2)) && m.position.y <= (position.y + (height / 2))){
			display(m.color);
		}else{
			display(color);
		}
	}

}
