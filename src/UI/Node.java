package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a super class for all the locations within the animation.
 * 
 * @ parent -  the calling PApplet
 * @ v - the location of the node.
 * @ w, h - width and height.
 * 
 */
public class Node {
	PApplet parent;
	PVector position;
	float width, height;
	
	public Node(){
		position = new PVector();
	}
	
	public PVector getVector(){
		return position.get();
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public void setVector(float x, float y){
		position.x = x;
		position.y = y;
	}
	
	public void setVector(PVector v){
		position = v.get();
	}
	
	public void setX(float x){
		position.x = x;
	}
	
	public void setY(float y){
		position.y = y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setWidth(float w){
		width = w;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void setHeight(float h){
		height = h;
	}
}
