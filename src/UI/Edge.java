package UI;

import processing.core.*;

import java.util.Random;

/*
 * @ author: Mark Hinshaw
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 *
 * This class is a message that travels from location to location.  This method will contain all the information needed for display within the message.
 * All calculations are done locally and can be called for use in other classes.
 *
 */

public class Edge {
	
	private PApplet parent;

    Random rand = new Random();
    private final int MAX = 7;
    private final int MIN =1;
	
	Node start, end;
	
	private int weight;
	private int color;
	
	public Edge(PApplet p, Node s, Node e){
		parent = p;
		start = s;
		end = e;
		weight = getRandom();
		color = parent.color(0, 0, 0 );
	}

    public Edge(PApplet p, Node s, Node e, int w){
        parent = p;
        start = s;
        end = e;
        weight = w;
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

    public int getRandom(){
        return MIN + (int)(Math.random() * ((MAX - MIN) + 1));
    }
	
	public void display(){
		if(start.className() != "Layer" && end.className() != "Layer"){
			parent.fill(color);
			parent.line(start.position.x, start.position.y, end.position.x, end.position.y);
		}
	}
}
