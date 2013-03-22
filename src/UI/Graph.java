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
		horizSpeed = new PVector((float)2.5, (float)2.5);
		vertSpeed = new PVector((float)0.5, (float)0.5);
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
		/*if(currentNode < nodeCount - 1){
			Node current = nodes.get(currentNode);
			Node next = nodes.get(currentNode+1);
			if(message.position.x == current.position.x && message.position.y <= (current.position.y - current.height/2)){
				current.output(message);
			}else if(message.position.x < (current.position.x + current.width/2) && message.position.y < (current.position.y - current.height/2)){
				current.output(message);
			}else if(message.position.x > (next.position.x + next.width/2) && message.position.y > (next.position.y - next.height/2)){
				next.output(message);
			}else if(message.position.x > (next.position.x + next.width/2) && message.position.y > (next.position.y - next.height/2)){
				next.output(message);
			}
		}
		nodes.get(currentNode).output(message);
		*/
		for(Node n : nodes){
			n.output(message);
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
		}else if (slope.x > 0 && slope.y !=0){
			if (message.position.x < message.end.x){
				slope = PVector.mult(slope, horizSpeed);
				message.position.add(slope);
			}else{
				currentNode++;
				message.setVector(nodes.get(currentNode).position);
			}
		}		
	}
}
