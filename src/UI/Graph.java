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
	private boolean ackSent;
	
	public Graph(PApplet p, Message m){
		parent = p;
		nodes = new ArrayList<Node>();
		message = m;
		horizSpeed = new PVector((float)2, (float)2);
		vertSpeed = new PVector((float)1, (float)1);
		nodeCount = 0;
		currentNode = 0;
		ackSent = true;
	}
	
	public void addNode(Node n){
		nodes.add(n);
		nodeCount++;
	}
	
	public void setCurrentNode(int i){
		currentNode = i;
	}
	
	public void output(int i){
		for(Node n : nodes){
			if(ackSent){
				n.output(message, i);
			}
		}
	}
	
	/*
	 * need to finish the shortest path implementation
	 */
	public void shortestPath(Node start){
		Node current = start;
		while(current.edgeCount > 0){
			
		}
	}
	
	public void sendACK(){
		if(nodes.get(currentNode).className().equals("Router")  && nodes.get(currentNode-1).className().equals("Router")){
			ackSent = false;
		}else{
			ackSent = true;
		}
	}
	
	public void travel(boolean start, boolean pause){
		if (currentNode < nodeCount-1 && start){
			message.setStart(nodes.get(currentNode).position);
			message.setEnd(nodes.get(currentNode+1).position);
			message.display();
			if (!pause && !ackSent){
				message.displayACK();
				driveACK();
			}else if (!pause && ackSent){
				drive();
			}
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
				message.setACK(message.getEnd());
				message.setACKStart(message.getEnd());
				message.setACKEnd(message.getStart());
				currentNode++;
				sendACK();
				message.setVector(nodes.get(currentNode).position);
			}
		}else if (slope.x > 0 && slope.y == 0){
			if (message.position.x < message.end.x){
				slope = PVector.mult(slope, horizSpeed);
				message.position.add(slope);
			}else{
				message.setACK(message.getEnd());
				message.setACKStart(message.getEnd());
				message.setACKEnd(message.getStart());
				currentNode++;
				sendACK();
				message.setVector(nodes.get(currentNode).position);
			}
		}else if (slope.x == 0 && slope.y < 0){
			if (message.position.y > message.end.y){
				slope = PVector.mult(slope, vertSpeed);
				message.position.add(slope);
			}else{
				message.setACK(message.getEnd());
				message.setACKStart(message.getEnd());
				message.setACKEnd(message.getStart());
				currentNode++;
				sendACK();
				message.setVector(nodes.get(currentNode).position);
			}
		}else if (slope.x > 0 && slope.y != 0){
			if (message.position.x < message.end.x){
				slope = PVector.mult(slope, horizSpeed);
				message.position.add(slope);
			}else{
				message.setACK(message.getEnd());
				message.setACKStart(message.getEnd());
				message.setACKEnd(message.getStart());
				currentNode++;
				sendACK();
				message.setVector(nodes.get(currentNode).position);
			}
		}		
	}
	
	public void driveACK(){
		PVector slope = new PVector();
		PVector ackSpeed = PVector.mult(horizSpeed, new PVector(2, 2));
		slope = PVector.sub(message.ackEnd, message.ackStart);
		slope.normalize();
		if(slope.x < 0){
			if (message.ack.x >= message.ackEnd.x){
				slope = PVector.mult(slope, ackSpeed);
				message.ack.add(slope);
			}else{
				ackSent = true;
			}
		}
	}
}
