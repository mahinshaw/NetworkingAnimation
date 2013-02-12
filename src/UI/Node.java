package UI;

import processing.core.PApplet;

/*
 * This class is an ellipse that will travel through the 
 * network
 * 
 * @ parent -  the calling PApplet
 * @ x, y - the location of the node
 * @ speed - speed at which the node is traveling.
 * @ d1, d2 - horizontal and vertical diameters.
 * 
 */
public class Node {
	PApplet parent;
	float x, y; 
	float speed;
	float d1, d2;
	
	Node(PApplet p){
		parent = p;
		
	}
}
