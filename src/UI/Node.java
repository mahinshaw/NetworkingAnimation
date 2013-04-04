package UI;

import java.util.ArrayList;

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
	ArrayList<Edge> edges;
	int edgeCount;
	int distance;
	
	public Node(PApplet p){
		parent = p;
		position = new PVector();
		edges = new ArrayList<Edge>();
		edgeCount = 0;
		distance = 1000000;
	}
	
	public Node(PApplet p, PVector v){
		parent = p;
		position = new PVector(v.x, v.y);
		edges = new ArrayList<Edge>();
		width = 0;
		height = 0;
		edgeCount = 0;
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
	
	public int getDistance(){
		return distance;
	}
	
	public void setDistance(int d){
		distance = d;
	}
	
	public String className(){
		return "Node";
	}
	
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	
	public void addEdge(Node n){
		Edge e = new Edge(parent, this, n);
		edges.add(e);
		edgeCount++;
	}
	
	public int edgeCount(){
		return edgeCount;
	}
	
	public void display(){
		if(edgeCount > 0){
			for (int i = 0; i < edgeCount; i++){
				Edge e = edges.get(i);
				e.display();
			}
		}
	}
	
	public void output(Message m, int i){
		
	}
}
