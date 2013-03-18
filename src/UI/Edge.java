package UI;

import processing.core.*;

public class Edge {
	
	private PApplet parent;
	
	Node start, end;
	
	private int weight;
	private int color;
	
	public Edge(PApplet p, Node s, Node e){
		parent = p;
		start = s;
		end = e;
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
		if(start.className() != "Layer" && end.className() != "Layer"){
			parent.fill(color);
			parent.line(start.position.x, start.position.y, end.position.x, end.position.y);
		}
	}
}
