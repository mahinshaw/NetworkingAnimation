package UI;

import processing.core.*;
import controlP5.*;

/*
 * This project is meant to animate computer networks as a package travels from one location to another.
 * 
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 */

@SuppressWarnings("serial")
public class Animation extends PApplet {

	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "UI.Animation" });
	  }
	
	//declare the controlP5 variable for button usage
	ControlP5 controller;
	
	// declarations for Layer classes
	Layer al1, al2;
	Layer pl1, pl2;
	Layer sl1, sl2;
	Layer tl1, tl2;
	Layer nl1, nl2;
	Layer dl1, dl2;
	Layer hl1, hl2;
	int layerColor, selectColor;
	
	// declaration for Computer classes
	Computer c1, c2;	
	
	// declaration for Router classes
	Router r1, r2;
	
	// declaration for Message classes 
	Message m1, m2;
	int m1Color, m2Color;
	String message1, message2;
	
	// declare the variables used for navigating the network
	PVector loc;
	Node start, end;

	// declare location variables for layers
	float AL1X, AL1Y; // application layer left
	PVector AL1;
	float AL2X, AL2Y; // application layer right
	PVector AL2;
	float PL1X, PL1Y; // presentation layer left
	PVector PL1;
	float PL2X, PL2Y; // presentation layer right
	PVector PL2;
	float SL1X, SL1Y; // session layer left
	PVector SL1;
	float SL2X, SL2Y; // session layer right
	PVector SL2;
	float TL1X, TL1Y; // transportation layer left
	PVector TL1;
	float TL2X, TL2Y; // transportation layer right
	PVector TL2;
	float NL1X, NL1Y; // network layer left
	PVector NL1;
	float NL2X, NL2Y; // network layer right
	PVector NL2;
	float DL1X, DL1Y; // datalink layer left
	PVector DL1;
	float DL2X, DL2Y; // datalink layer right
	PVector DL2;
	float HL1X, HL1Y; // physical layer left
	PVector HL1;
	float HL2X, HL2Y; // physical layer right
	PVector HL2;

	// declare location variables for the computers and routers
	float C1X, C1Y; // computer 1
	PVector C1;
	float C2X, C2Y; // computer 2
	PVector C2;
	float R1X, R1Y; // router 1
	PVector R1;
	float R2X, R2Y; // router 2
	PVector R2;
	
	// declare the Graph
	Graph g1;
	Graph g2;
	
	// declare variable for font
	PFont f;

	// declare variable for images
	PImage router;
	PImage computer;

	// rectangle sizes
	float rectW = 180;
	float rectH = 30;

	// button variables
	boolean isPaused;
	boolean isStart;
	boolean isEnd;
	
	
	public void setup() {
		size(displayWidth, displayHeight);

		isPaused = false;

		f = createFont("Georgia", 16, true);
		
		// set the begin location -- animation starts with start button
		loc = new PVector(0, 0);
		PVector v1 = new PVector((width / 8), map(160, 0, 900, 0, height));
		PVector v2 = new PVector((width / 8) * 7, map(160, 0, 900, 0, height));
		start = new Node(this, v1);
		end = new Node(this, v2);
		
		// intitialize the controller 
		controller = new ControlP5(this);
		
		// initialize the controller objects
		controller.addTextfield("Message_1")
		.setPosition(map(50, 0, 1600, 0, width), map(50, 0, 900, 0, height))
		.setSize(200, 30)
		.setFont(f)
		.setFocus(true)
		.setColor(color(0, 0, 0))
		.setColorBackground(color(156, 156, 156))
		.setText("Insert Message 1.")
		.setAutoClear(true)
		.setCaptionLabel("")
		;
		
		controller.addTextfield("Message_2")
		.setPosition(map(50, 0, 1600, 0, width), map(90, 0, 900, 0, height))
		.setSize(200, 30)
		.setFont(f)
		.setFocus(false)
		.setColor(color(0, 0, 0))
		.setColorBackground(color(156, 156, 156))
		.setText("Insert Message 2.")
		.setAutoClear(true)
		.setCaptionLabel("")
		;
		
		controller.addToggle("Start_Pause")
		.setPosition(map(1290, 0, 1600, 0, width), map(50, 0, 900, 0, height))
		.setSize(80, 40)
		.setValue(true)
		.setCaptionLabel("Play")
		.getCaptionLabel().align(CENTER, CENTER)
		.setFont(f);
		;
		
		controller.addBang("Stop")
		.setPosition(map(1410, 0, 1600, 0, width), map(50, 0, 900, 0, height))
		.setSize(80, 40)
		.getCaptionLabel().align(CENTER, CENTER)
		.setFont(f)
		;
		
		// set flags for buttons
		isStart = false;
		isPaused = false;
		isEnd = true;
		
		// set images
		router = loadImage("Router1.jpg");
		computer = loadImage("Computer1.jpg");

		// declare location for layers
		AL1X = (width / 8);
		AL1Y = map(180, 0, 900, 0, height); // application layer left
		AL1 = new PVector((width / 8), map(180, 0, 900, 0, height));
		
		AL2X = (width / 8) * 7;
		AL2Y = map(180, 0, 900, 0, height); // application layer right
		AL2 = new PVector((width / 8) * 7, map(180, 0, 900, 0, height));
		
		PL1X = (width / 8);
		PL1Y = map(220, 0, 900, 0, height); // presentation layer left
		PL1 = new PVector((width / 8), map(220, 0, 900, 0, height));
		
		PL2X = (width / 8) * 7;
		PL2Y = map(220, 0, 900, 0, height); // presentation layer right
		PL2 = new PVector((width / 8) * 7, map(220, 0, 900, 0, height));
		
		SL1X = (width / 8);
		SL1Y = map(260, 0, 900, 0, height); // session layer left
		SL1 = new PVector((width / 8), map(260, 0, 900, 0, height));
		
		SL2X = (width / 8) * 7;
		SL2Y = map(260, 0, 900, 0, height); // session layer right
		SL2 = new PVector((width / 8) * 7, map(260, 0, 900, 0, height));
		
		TL1X = (width / 8);
		TL1Y = map(300, 0, 900, 0, height); // transportation layer left
		TL1 = new PVector((width / 8), map(300, 0, 900, 0, height));
		
		TL2X = (width / 8) * 7;
		TL2Y = map(300, 0, 900, 0, height); // transportation layer right
		TL2 = new PVector((width / 8) * 7, map(300, 0, 900, 0, height));
		
		NL1X = (width / 8);
		NL1Y = map(340, 0, 900, 0, height); // network layer left
		NL1 = new PVector((width / 8), map(340, 0, 900, 0, height));
		
		NL2X = (width / 8) * 7;
		NL2Y = map(340, 0, 900, 0, height); // network layer right
		NL2 = new PVector((width / 8) * 7, map(340, 0, 900, 0, height));
		
		DL1X = (width / 8);
		DL1Y = map(380, 0, 900, 0, height); // datalink layer left
		DL1 = new PVector((width / 8), map(380, 0, 900, 0, height));
		
		DL2X = (width / 8) * 7;
		DL2Y = map(380, 0, 900, 0, height); // datalink layer right
		DL2 = new PVector((width / 8) * 7, map(380, 0, 900, 0, height));
		
		HL1X = (width / 8);
		HL1Y = map(420, 0, 900, 0, height); // physical layer left
		HL1 = new PVector((width / 8), map(420, 0, 900, 0, height));
		
		HL2X = (width / 8) * 7;
		HL2Y = map(420, 0, 900, 0, height); // physical layer right
		HL2 = new PVector((width / 8) * 7, map(420, 0, 900, 0, height));
		
		// declare location variables for the computers and routers
		C1X = (width / 8);
		C1Y = map(480, 0, 900, 0, height); // computer 1
		C1 = new PVector((width / 8), map(480, 0, 900, 0, height));
		
		C2X = (width / 8) * 7;
		C2Y = map(480, 0, 900, 0, height); // computer 2
		C2 = new PVector((width / 8) * 7, map(480, 0, 900, 0, height));
		
		R1X = (width / 8) * 2;
		R1Y = map(480, 0, 900, 0, height); // router 1
		R1 = new PVector((width / 8) * 2, map(480, 0, 900, 0, height));
		
		R2X = (width / 8) * 6;
		R2Y = map(480, 0, 900, 0, height); // router 2
		R2 = new PVector((width / 8) * 6, map(480, 0, 900, 0, height));
		
		
		//initialize layer colors
		layerColor = color(123, 123, 123);	// grey
		selectColor = color(0, 255, 0);	// green
		
		// initialize layer classes
		al1 = new Layer(this, AL1, rectW, rectH, "Application Layer", layerColor);
		al2 = new Layer(this, AL2, rectW, rectH, "Application Layer", layerColor);
		sl1 = new Layer(this, SL1, rectW, rectH, "Session Layer", layerColor);
		sl2 = new Layer(this, SL2, rectW, rectH, "Session Layer", layerColor);
		pl1 = new Layer(this, PL1, rectW, rectH, "Presentation Layer", layerColor);
		pl2 = new Layer(this, PL2, rectW, rectH, "Presentation Layer", layerColor);
		nl1 = new Layer(this, NL1, rectW, rectH, "Network Layer", layerColor);
		nl2 = new Layer(this, NL2, rectW, rectH, "Network Layer", layerColor);
		tl1 = new Layer(this, TL1, rectW, rectH, "Transportation Layer", layerColor);
		tl2 = new Layer(this, TL2, rectW, rectH, "Transportation Layer", layerColor);
		dl1 = new Layer(this, DL1, rectW, rectH, "Data Link Layer", layerColor);
		dl2 = new Layer(this, DL2, rectW, rectH, "Data Link Layer", layerColor);
		hl1 = new Layer(this, HL1, rectW, rectH, "Physical Layer", layerColor);
		hl2 = new Layer(this, HL2, rectW, rectH, "Physical Layer", layerColor);
		
		//initialize Computer classes
		c1 = new Computer(this, C1, 50, 50);
		c2 = new Computer(this, C2, 50, 50);
		
		//initialize Router classes
		r1 = new Router(this, R1, 40, 40);
		r2 = new Router(this, R2, 40, 40);
		
		// add edges
		start.addEdge(al1);
		al1.addEdge(pl1);
		pl1.addEdge(sl1);
		sl1.addEdge(tl1);
		tl1.addEdge(nl1);
		nl1.addEdge(dl1);
		dl1.addEdge(hl1);
		hl1.addEdge(c1);
		c1.addEdge(r1);
		r1.addEdge(r2);
		r2.addEdge(c2);
		c2.addEdge(hl2);
		hl2.addEdge(dl2);
		dl2.addEdge(nl2);
		nl2.addEdge(tl2);
		tl2.addEdge(sl2);
		sl2.addEdge(pl2);
		pl2.addEdge(sl2);
		al2.addEdge(end);
		
		// initialize message colors
		m1Color = color(255, 0, 0);
		m2Color = color(0, 0, 255);
		
		// intitialize messages
		message1 = controller.get(Textfield.class, "Message_1").getText();
		message2 = controller.get(Textfield.class, "Message_2").getText();
		
		// initialize Message classes
		m1 = new Message(this, start.position, 30, 30, m1Color, message1);
		m2 = new Message(this, start.position, 30, 30, m2Color, message2);
		
		// initialize graph
		g1 = new Graph(this, m1);
		g1.addNode(start);
		g1.addNode(al1);
		g1.addNode(pl1);
		g1.addNode(sl1);
		g1.addNode(tl1);
		g1.addNode(nl1);
		g1.addNode(dl1);
		g1.addNode(hl1);
		g1.addNode(c1);
		g1.addNode(r1);
		g1.addNode(r2);
		g1.addNode(c2);
		g1.addNode(hl2);
		g1.addNode(dl2);
		g1.addNode(nl2);
		g1.addNode(tl2);
		g1.addNode(sl2);
		g1.addNode(pl2);
		g1.addNode(al2);
		g1.addNode(end);
	}

	public void draw() {
		background(200);
		
		//display using message input
		al1.display(m1);
		pl1.display(m1);
		sl1.display(m1);
		tl1.display(m1);
		nl1.display(m1);
		dl1.display(m1);
		hl1.display(m1);
		
		// display side 2 
		al2.display(m1);
		pl2.display(m1);
		sl2.display(m1);
		tl2.display(m1);
		nl2.display(m1);
		dl2.display(m1);
		hl2.display(m1);

		// draw the lines that will connect out routers
		/*stroke(0);
		strokeWeight(4);
		line(C1X, C1Y, R1X, R2Y);
		line(R1X, R1Y, R2X, R2Y);
		line(R2X, R2Y, C2X, C2Y);
		*/
		
		// draw the computers
		c1.display();
				
		// draw the routers
		r1.display();
		r2.display();
		
		// draw computer 2
		c2.display();
		
		g1.travel(isStart, isPaused);
		if(g1.message.position.equals(end.position)){
			Stop();
		}

		/*
		// draw the transfer node
		if (loc.y >= C1Y) {
			fill(255, 0, 0);
			strokeWeight(2);
			ellipse(loc.x, loc.y, 30, 30);
		}

		// drive through the animation
		drive(isPaused);
		output();
		
		//update message location
		m1.setVector(loc);
		m2.setVector(loc);
		*/
	}
	
	/*
	 * Controller Methods
	 */
	
	public void controlEvent(ControlEvent event){
		
	}
	
	public void Message_1(String text){
		message1 = text;
	}
	
	public void Message_2(String text){
		message2 = text;
	}
	
	public void Start_Pause(boolean value){
		if(!value){
			controller.get(Toggle.class, "Start_Pause").setCaptionLabel("Pause");
			if(!isPaused && !isStart){
				begin();
				m1.setVector(start.position);
				g1.setCurrentNode(0);
				isStart = true;
			}else if(isPaused && isStart){
				isPaused = false;
			}
		}else if (value){
			controller.get(Toggle.class, "Start_Pause").setCaptionLabel("Play");
			if(!isPaused && isStart){
				isPaused = true;
			}
		}		
	}
	
	public void Stop(){
		if(isStart){
			end();
			isStart = false;
			isPaused = false;
			controller.get(Toggle.class, "Start_Pause").setValue(true);
			m1.setVector(start.position);
		}
	}

	// method used for iteration of location variables
	public void drive(boolean pause) {
		/*
		 * Incrementing the location variables start at AP1. Move down the
		 * y-axis until C1 is reached. Once C1 has been reached, increment along
		 * the x-axis until C2 has been reached. From C2, move up the y-axis
		 * until AL2 has been reached.
		 */
		
		//vert and horiz are speed variables
		float vert = 1;		//map(2, 50, 350, AL1Y, C1Y);
		float horiz = 3;	//map(5, 200, 1400, C1X, C2X);
		
		if (pause == false) {
			if (loc.y < C1Y && loc.x == C1X) {
				loc.y = loc.y + vert;
			} else if (loc.y >= C1Y && loc.x <= C2X) {
				loc.x = loc.x + horiz;
			} else if (loc.x >= C2X && loc.y > AL2Y) {
				loc.x = C2X;
				loc.y = loc.y - vert;
			} else if (loc.x == AL2X && loc.y == AL2Y) {
				Stop();
			}
		}
	}
	
	void begin(){
		loc.x = AL1X;
		loc.y = AL1Y;
	}
	
	void end(){
		loc.x = 0;
		loc.y = 0;
	}

		
	void output(){
		if(isStart && isPaused){
			fill(225);
			float x = width/2;
			float y = map(550, 0, 900, 0, height);
			rectMode(CENTER);
			rect(x, y, 400, 200);
			
			textFont(f, 16);
			fill(0);
			textAlign(CENTER, CENTER);			
			// based on the location of the traffic, output a given message.
			if (loc.x == C1X && loc.y < PL1Y) {
				// Application Layer
				text("Application Layer:\nThe protocal type is chosen and the\n" +
						"message is sent as data in a packet to the\n" +
						" recieving computer.", x, y);
			}
			if (loc.x == C1X && loc.y >= PL1Y && loc.y < SL1Y) {
				// Presentation Layer
				text("Presentation Layer:\nThe protocal type is chosen and the\n" +
						"message is sent as data in a packet to the\n" +
						" recieving computer.", x, y);
				
			}
			if (loc.x == C1X && loc.y >= SL1Y && loc.y < TL1Y) {
				// Session Layer
				text("Session Layer:\nThe protocal type is chosen and the\n" +
						"message is sent as data in a packet to the\n" +
						" recieving computer.", x, y);
			}
			if (loc.x == C1X && loc.y >= TL1Y && loc.y < NL1Y) {
				// Transportation Layer
				text("Transportation Layer:\n" +
						"The protocal for the logical addressing is processed\n" +
						"here.  A port is chosen based on the type of protocol,\n" +
						"and an port number is attatched to the packet.", x, y);
			}
			if (loc.x == C1X && loc.y >= NL1Y && loc.y < DL1Y) {
				// Network Layer
				text("Network Layer:\n" +
						"The network layer creates a connection between the\n" +
						"source and the destination computer.  Here the IP\n" +
						"address is attached to packet for both the source\n" +
						"and the destination.", x, y);
			}
			if (loc.x == C1X && loc.y >= DL1Y && loc.y < HL1Y) {
				// DataLink Layer
				text("DataLink Layer:\n" +
						"The data-link laer is responsible for transffering\n" +
						"the message from router to router, finding a path for\n" +
						"the message.  At this level a MAC address\n is assigned", x, y);
			}
			if (loc.x == C1X && loc.y >= HL1Y && loc.y < C1Y) {
				// Physical Layer
				text("Physical Layer:\n" +
						"The signal is carried from one location to the next at\n" +
						"this level.  It is carried by an analog signal.", x, y);
			}
			if (loc.x >= C2X && loc.y >= AL2Y && loc.y < PL2Y) {
				// application Layer 2
				text("Application Layer:\nThe message is recieved and the\n" +
						"information is presented to the user.", x, y);
			}
			if (loc.x >= C2X && loc.y >= PL2Y && loc.y < SL2Y) {
				// Presentation Layer 2
				text("Presentation Layer:\nThe message is recieved and the\n" +
						"information is presented to the user.", x, y);
			}
			if (loc.x >= C2X && loc.y >= SL2Y && loc.y < TL2Y) {
				// Session Layer 2
				text("Session Layer:\nThe message is recieved and the information\n" +
						"is presented to the user.", x, y);
			}
			if (loc.x >= C2X && loc.y >= TL2Y && loc.y < NL2Y) {
				// Transportation Layer 2
				text("Transportation Layer:\n" +
						"Here the port number is read, so that the right message\n" +
						"protocal can be read to the application layer.", x, y);
			}
			if (loc.x >= C2X && loc.y >= NL2Y && loc.y < DL2Y) {
				// Network Layer 2
				text("Network Layer:\n" +
						"This is where the IP address is confirmed and the\n" +
						"message is sent back up the the Transportation layer.", x, y);
			}
			if (loc.x >= C2X && loc.y >= DL2Y && loc.y < HL2Y) {
				// Data Link Layer 2
				text("DataLink Layer:\n" +
						"The MAC address is read here.  The Data-link layer\n" +
						"translates the signal sent by the network layer.", x, y);
			}			
			if (loc.x >= C2X && loc.y >= HL2Y && loc.y < C2Y) {
				// Physical Layer 2
				text("Physical Layer:\n" +
						"The signal is recieved and it transferred back up to the\n" +
						" Data-linkLayer.", x, y);
			}
			if (loc.y >= C2Y && loc.x > C1X && loc.x < C2X){
				text("The message is being transmitted.", x ,y);
			}
		}
	}	
}
