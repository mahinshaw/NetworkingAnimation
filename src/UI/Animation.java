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
	float locx, locy;
	PVector loc;

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
	
	// declare variable for font
	PFont f;

	// declare variable for images
	PImage router;
	PImage computer;

	// rectangle sizes
	float rectW = 180;
	float rectH = 30;

	// button variables
	RectButton start, stop, pause, resume;
	float startX, startY;
	float stopX, stopY;
	float pauseX, pauseY;
	float resumeX, resumeY;
	float bW = 100, bH = 40; // width and height of all buttons
	boolean startOver;
	boolean stopOver;
	boolean pauseOver;
	boolean resumeOver;
	
	boolean isPaused;
	boolean isStart;
	
	boolean locked;

	public void setup() {
		size(displayWidth, displayHeight);

		isPaused = false;

		f = createFont("Georgia", 16, true);
		
		// initialize the location vector
		loc = new PVector(0, 0);
		
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
		;

		// button location setup
		startX = width / 2;
		startY = map(150, 0, 900, 0, height);
		stopX = width / 2;
		stopY = map(200, 0, 900, 0, height);
		pauseX = width / 2;
		pauseY = map(250, 0, 900, 0, height);
		resumeX = width / 2;
		resumeY = map(300, 0, 900, 0, height);

		// button setup
		int buttonColor = color(123, 123, 123);
		int highlight = color(0, 255, 255);

		start = new RectButton(startX, startY, bW, bH, buttonColor, highlight);
		stop = new RectButton(stopX, stopY, bW, bH, buttonColor, highlight);
		pause = new RectButton(pauseX, pauseY, bW, bH, buttonColor, highlight);
		resume = new RectButton(resumeX, resumeY, bW, bH, buttonColor,
				highlight);

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
		
		// initialize message colors
		m1Color = color(255, 0, 0);
		m2Color = color(0, 0, 255);
		
		// intitialize messages
		message1 = controller.get(Textfield.class, "Message_1").getText();
		message2 = controller.get(Textfield.class, "Message_2").getText();
		
		// initialize Message classes
		m1 = new Message(this, loc, 30, 30, m1Color, message1);
		m2 = new Message(this, loc, 30, 30, m2Color, message2);

		// set the begin location -- animation starts with start button
		locx = 0;
		locy = 0;
		
	}

	public void draw() {
		background(255);
		update(mouseX, mouseY);
		
		// draw buttons
		start.display();
		stop.display();
		pause.display();
		resume.display();

		// button text
		textFont(f, 16);
		fill(0);
		textAlign(CENTER, CENTER);
		text("Start", startX, startY);
		text("Stop", stopX, stopY);
		text("Pause", pauseX, pauseY);
		text("Resume", resumeX, resumeY);

		// draw the 7 layer architecture on side 1
		if (locx == C1X && locy < PL1Y) {
			al1.display(selectColor);
		} else {
			al1.display();
		}
				
		if (locx == C1X && locy >= PL1Y && locy < SL1Y) {
			pl1.display(selectColor);
		} else {
			pl1.display();
		}
		
		if (locx == C1X && locy >= SL1Y && locy < TL1Y) {
			sl1.display(selectColor);
		} else {
			sl1.display();
		}
		
		if (locx == C1X && locy >= TL1Y && locy < NL1Y) {
			tl1.display(selectColor);
		} else {
			tl1.display();
		}
		
		if (locx == C1X && locy >= NL1Y && locy < DL1Y) {
			nl1.display(selectColor);
		} else {
			nl1.display();
		}
		
		if (locx == C1X && locy >= DL1Y && locy < HL1Y) {
			dl1.display(selectColor);
		} else {
			dl1.display();
		}
		
		if (locx == C1X && locy >= HL1Y && locy < C1Y) {
			hl1.display(selectColor);
		} else {
			hl1.display();
		}
		
		// draw the 7 layer architecture on side 2
		if (locx >= C2X && locy >= AL2Y && locy < PL2Y) {
			al2.display(selectColor);
		} else {
			al2.display();
		}
		
		if (locx >= C2X && locy >= PL2Y && locy < SL2Y) {
			pl2.display(selectColor);
		} else {
			pl2.display();
		}
		
		if (locx >= C2X && locy >= SL2Y && locy < TL2Y) {
			sl2.display(selectColor);
		} else {
			sl2.display();
		}
		
		if (locx >= C2X && locy >= TL2Y && locy < NL2Y) {
			tl2.display(selectColor);
		} else {
			tl2.display();
		}
		
		if (locx >= C2X && locy >= NL2Y && locy < DL2Y) {
			nl2.display(selectColor);
		} else {
			nl2.display();
		}
		
		if (locx >= C2X && locy >= DL2Y && locy < HL2Y) {
			dl2.display(selectColor);
		} else {
			dl2.display();
		}
		
		if (locx >= C2X && locy >= HL2Y && locy < C2Y) {
			hl2.display(selectColor);
		} else {
			hl2.display();
		}

		// draw the lines that will connect out routers
		stroke(0);
		strokeWeight(4);
		line(C1X, C1Y, R1X, R2Y);
		line(R1X, R1Y, R2X, R2Y);
		line(R2X, R2Y, C2X, C2Y);

		
		// draw the computers
		c1.display();
		c2.display();
		
		// draw the routers
		r1.display();
		r2.display();

		// draw the transfer node
		if (locy >= C1Y) {
			fill(255, 0, 0);
			strokeWeight(2);
			ellipse(locx, locy, 30, 30);
		}

		// drive through the animation
		drive(isPaused);
		output();

	}
	
	/*
	 * Controller Methods
	 */
	
	@SuppressWarnings("unused")
	private void controlEvent(ControlEvent event){
		
	}
	
	@SuppressWarnings("unused")
	private void Message_1(String text){
		message1 = text;
	}
	
	@SuppressWarnings("unused")
	private void Message_2(String text){
		message2 = text;
	}

	// method used for iteration of location variables
	void drive(boolean pause) {
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
			if (locy < C1Y && locx == C1X) {
				locy = locy + vert;
			} else if (locy >= C1Y && locx <= C2X) {
				locx = locx + horiz;
			} else if (locx >= C2X && locy > AL2Y) {
				locy = locy - vert;
			} else if (locx == AL2X && locy == AL2Y) {
				locx = 0;
				locy = 0;
			}
		}
	}
	
	void begin(){
		locx = AL1X;
		locy = AL1Y;
	}
	
	void end(){
		locx = 0;
		locy = 0;
	}

	void update(float x, float y) {
		if (locked == false) {
			// update buttons
			start.update();
			stop.update();
			pause.update();
			resume.update();
		} else {
			locked = false;
		}

		if (mousePressed) {
			if (start.pressed() && !isPaused) {
				begin();
				isStart = true;
			} else if (stop.pressed() && !isPaused) {
				end();
				isStart = false;
			} else if (pause.pressed() && isStart) {
				isPaused = true;
			} else if (resume.pressed() && isStart) {
				isPaused = false;
			}
		}
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
			if (locx == C1X && locy < PL1Y) {
				// Application Layer
				text("Application Layer:\nThe protocal type is chosen and the\n" +
						"message is sent as data in a packet to the\n" +
						" recieving computer.", x, y);
			}
			if (locx == C1X && locy >= PL1Y && locy < SL1Y) {
				// Presentation Layer
				text("Presentation Layer:\nThe protocal type is chosen and the\n" +
						"message is sent as data in a packet to the\n" +
						" recieving computer.", x, y);
				
			}
			if (locx == C1X && locy >= SL1Y && locy < TL1Y) {
				// Session Layer
				text("Session Layer:\nThe protocal type is chosen and the\n" +
						"message is sent as data in a packet to the\n" +
						" recieving computer.", x, y);
			}
			if (locx == C1X && locy >= TL1Y && locy < NL1Y) {
				// Transportation Layer
				text("Transportation Layer:\n" +
						"The protocal for the logical addressing is processed\n" +
						"here.  A port is chosen based on the type of protocol,\n" +
						"and an port number is attatched to the packet.", x, y);
			}
			if (locx == C1X && locy >= NL1Y && locy < DL1Y) {
				// Network Layer
				text("Network Layer:\n" +
						"The network layer creates a connection between the\n" +
						"source and the destination computer.  Here the IP\n" +
						"address is attached to packet for both the source\n" +
						"and the destination.", x, y);
			}
			if (locx == C1X && locy >= DL1Y && locy < HL1Y) {
				// DataLink Layer
				text("DataLink Layer:\n" +
						"The data-link laer is responsible for transffering\n" +
						"the message from router to router, finding a path for\n" +
						"the message.  At this level a MAC address\n is assigned", x, y);
			}
			if (locx == C1X && locy >= HL1Y && locy < C1Y) {
				// Physical Layer
				text("Physical Layer:\n" +
						"The signal is carried from one location to the next at\n" +
						"this level.  It is carried by an analog signal.", x, y);
			}
			if (locx >= C2X && locy >= AL2Y && locy < PL2Y) {
				// application Layer 2
				text("Application Layer:\nThe message is recieved and the\n" +
						"information is presented to the user.", x, y);
			}
			if (locx >= C2X && locy >= PL2Y && locy < SL2Y) {
				// Presentation Layer 2
				text("Presentation Layer:\nThe message is recieved and the\n" +
						"information is presented to the user.", x, y);
			}
			if (locx >= C2X && locy >= SL2Y && locy < TL2Y) {
				// Session Layer 2
				text("Session Layer:\nThe message is recieved and the information\n" +
						"is presented to the user.", x, y);
			}
			if (locx >= C2X && locy >= TL2Y && locy < NL2Y) {
				// Transportation Layer 2
				text("Transportation Layer:\n" +
						"Here the port number is read, so that the right message\n" +
						"protocal can be read to the application layer.", x, y);
			}
			if (locx >= C2X && locy >= NL2Y && locy < DL2Y) {
				// Network Layer 2
				text("Network Layer:\n" +
						"This is where the IP address is confirmed and the\n" +
						"message is sent back up the the Transportation layer.", x, y);
			}
			if (locx >= C2X && locy >= DL2Y && locy < HL2Y) {
				// Data Link Layer 2
				text("DataLink Layer:\n" +
						"The MAC address is read here.  The Data-link layer\n" +
						"translates the signal sent by the network layer.", x, y);
			}			
			if (locx >= C2X && locy >= HL2Y && locy < C2Y) {
				// Physical Layer 2
				text("Physical Layer:\n" +
						"The signal is recieved and it transferred back up to the\n" +
						" Data-linkLayer.", x, y);
			}
			if (locy >= C2Y && locx > C1X && locx < C2X){
				text("The message is being transmitted.", x ,y);
			}
		}
	}

	public class Button {
		float x, y;
		float w, h;
		int baseColor, highlightColor;
		int currentColor;
		boolean over = false;
		boolean pressed = false;

		void update() {
			if (over()) {
				currentColor = highlightColor;
			} else {
				currentColor = baseColor;
			}
		}

		boolean pressed() {
			if (over) {
				locked = true;
				return true;
			} else {
				locked = false;
				return false;
			}
		}

		boolean over() {
			return true;
		}

		boolean overRect(float x, float y, float w, float h) {
			if (mouseX >= x - (w / 2) && mouseX <= x + (w / 2)
					&& mouseY >= y - (h / 2) && mouseY <= y + (h / 2)) {
				return true;
			} else {
				return false;
			}
		}
	}

	class RectButton extends Button {
		RectButton(float ix, float iy, float iw, float ih, int icolor,
				int ihighlight) {
			x = ix;
			y = iy;
			w = iw;
			h = ih;
			baseColor = icolor;
			highlightColor = ihighlight;
			currentColor = baseColor;
		}

		boolean over() {
			if (overRect(x, y, w, h)) {
				over = true;
				return true;
			} else {
				over = false;
				return false;
			}
		}

		void display() {
			stroke(255);
			fill(currentColor);
			rect(x, y, w, h);
		}
	}	
}
