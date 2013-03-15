package UI;

import processing.core.*;

public class Edge {
	
	private PApplet parent;
	
	PVector start, end;
	
	private int weight;
	private int color;
	
	public Edge(PApplet p, PVector s, PVector e){
		parent = p;
		start = s.get();
		end = e.get();
		color = parent.color(0, 0, 0 );
	}
	
	public int getWeight(){
		return weight;
	}
	
	public void setWeight(int w){
		weight = w;
	}
	
	public void setColor(int c){
		color = c;
	}
	
	public void display(){
		parent.fill(color);
		parent.line(start.x, start.y, end.x, end.y);
	}
}
