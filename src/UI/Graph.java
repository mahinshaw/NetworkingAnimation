package UI;

import java.util.ArrayList;
import processing.core.*;

public class Graph {
	
	PApplet parent;
	ArrayList<Node> nodes;
	Message message;
	PVector horizSpeed, vertSpeed;
	private int nodeCount;
	private int currentNode;
	
	public Graph(PApplet p, Message m){
		parent = p;
		nodes = new ArrayList<Node>();
		message = m;
		horizSpeed = new PVector(3, 3);
		vertSpeed = new PVector(2, 2);
		nodeCount = 0;
		currentNode = 0;
	}
	
	public void addNode(Node n){
		nodes.add(n);
		nodeCount++;
	}
	
	public void setCurrentNode(int i){
		currentNode = i;
	}
	
	public void output(){
		for(Node n : nodes){
			switch (n.className()){
			case "Layer":
				
				break;
			case "Computer":
				
				break;
			case "Router":
				
				break;
			}
		}
	}
	
	public void travel(boolean start, boolean pause){
		if (currentNode < nodeCount-1 && start){
			message.setStart(nodes.get(currentNode).position);
			message.setEnd(nodes.get(currentNode+1).position);
			message.display();
			if (!pause){
					drive();
			}
			
		}else{
			
		}
	}
	
	public void drive(){
		PVector slope = new PVector();
		slope = PVector.sub(message.end, message.start);
		slope.normalize();
		if (slope.x == 0 && slope.y > 0){
			if (message.position.y < message.end.y){
				slope = PVector.mult(slope, vertSpeed);
				message.position.add(slope);
			}else{
				currentNode++;
				message.setVector(nodes.get(currentNode).position);
			}
		}else if (slope.x > 0 && slope.y == 0){
			if (message.position.x < message.end.x){
				slope = PVector.mult(slope, horizSpeed);
				System.out.println(slope.x + " " + slope.y);
				message.position.add(slope);
			}else{
				currentNode++;
				message.setVector(nodes.get(currentNode).position);
			}
		}else if (slope.x == 0 && slope.y < 0){
			if (message.position.y > message.end.y){
				slope = PVector.mult(slope, vertSpeed);
				message.position.add(slope);
			}else{
				currentNode++;
				message.setVector(nodes.get(currentNode).position);
			}
		}
		
	}
}