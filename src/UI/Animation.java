package UI;

import processing.core.*;

/*
 * This project is meant to animate computer networks as a package travels from one location to another.
 * 
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 */

public class Animation extends PApplet {

	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "UI.Animation" });
	  }
	
	// declarations for Layer classes
	Layer AL1, AL2;
	Layer PL1, PL2;
	Layer SL1, SL2;
	Layer TL1, TL2;
	Layer NL1, NL2;
	Layer DL1, DL2;
	Layer HL1, HL2;
	int layerColor, selectColor;
	
	// declaration for Computer classes
	Computer C1, C2;	
	
	// declaration for Router classes
	Router R1, R2;
	
	
	// declare the variables used for navigating the network
	float locx, locy;

	// declare location variables for layers
	float AL1X, AL1Y; // application layer left
	float AL2X, AL2Y; // application layer right
	float PL1X, PL1Y; // presentation layer left
	float PL2X, PL2Y; // presentation layer right
	float SL1X, SL1Y; // session layer left
	float SL2X, SL2Y; // session layer right
	float TL1X, TL1Y; // transportation layer left
	float TL2X, TL2Y; // transportation layer right
	float NL1X, NL1Y; // network layer left
	float NL2X, NL2Y; // network layer right
	float DL1X, DL1Y; // datalink layer left
	float DL2X, DL2Y; // datalink layer right
	float HL1X, HL1Y; // physical layer left
	float HL2X, HL2Y; // physical layer right

	// declare location variables for the computers and routers
	float C1X, C1Y; // computer 1
	float C2X, C2Y; // computer 2
	float R1X, R1Y; // router 1
	float R2X, R2Y; // router 2

	// declare variable for font
	PFont f;

	// declare variable for images
	PImage router;
	PImage computer;

	// rectangle sizes
	float rectW = 150;
	float rectH = 30;

	// button variables
	RectButton start, stop, pause, resume;
	float startX, startY;
	float stopX, stopY;
	float pauseX, pauseY;
	float resumeX, resumeY;
	float bW = 100, bH = 50; // width and height of all buttons
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

		f = createFont("Arial", 16, true);

		// button location setup
		startX = width / 2;
		startY = map(50, 0, 900, 0, height);
		stopX = width / 2;
		stopY = map(100, 0, 900, 0, height);
		pauseX = width / 2;
		pauseY = map(150, 0, 900, 0, height);
		resumeX = width / 2;
		resumeY = map(200, 0, 900, 0, height);

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
		AL1Y = map(50, 0, 900, 0, height); // application layer left
		AL2X = (width / 8) * 7;
		AL2Y = map(50, 0, 900, 0, height); // application layer right
		PL1X = (width / 8);
		PL1Y = map(90, 0, 900, 0, height); // presentation layer left
		PL2X = (width / 8) * 7;
		PL2Y = map(90, 0, 900, 0, height); // presentation layer right
		SL1X = (width / 8);
		SL1Y = map(130, 0, 900, 0, height); // session layer left
		SL2X = (width / 8) * 7;
		SL2Y = map(130, 0, 900, 0, height); // session layer right
		TL1X = (width / 8);
		TL1Y = map(170, 0, 900, 0, height); // transportation layer left
		TL2X = (width / 8) * 7;
		TL2Y = map(170, 0, 900, 0, height); // transportation layer right
		NL1X = (width / 8);
		NL1Y = map(210, 0, 900, 0, height); // network layer left
		NL2X = (width / 8) * 7;
		NL2Y = map(210, 0, 900, 0, height); // network layer right
		DL1X = (width / 8);
		DL1Y = map(250, 0, 900, 0, height); // datalink layer left
		DL2X = (width / 8) * 7;
		DL2Y = map(250, 0, 900, 0, height); // datalink layer right
		HL1X = (width / 8);
		HL1Y = map(290, 0, 900, 0, height); // physical layer left
		HL2X = (width / 8) * 7;
		HL2Y = map(290, 0, 900, 0, height); // physical layer right

		// declare location variables for the computers and routers
		C1X = (width / 8);
		C1Y = map(350, 0, 900, 0, height); // computer 1
		C2X = (width / 8) * 7;
		C2Y = map(350, 0, 900, 0, height); // computer 2
		R1X = (width / 8) * 2;
		R1Y = map(350, 0, 900, 0, height); // router 1
		R2X = (width / 8) * 6;
		R2Y = map(350, 0, 900, 0, height); // router 2
		
		//initialize layer colors
		layerColor = color(255, 0, 0);	//red
		selectColor = color(0, 255, 0);	// green
		
		// initialize layer classes
		AL1 = new Layer(this, AL1X, AL1Y, rectW, rectH, "Application Layer", layerColor);
		AL2 = new Layer(this, AL2X, AL2Y, rectW, rectH, "Application Layer", layerColor);
		SL1 = new Layer(this, SL1X, SL1Y, rectW, rectH, "Session Layer", layerColor);
		SL2 = new Layer(this, SL2X, SL2Y, rectW, rectH, "Session Layer", layerColor);
		PL1 = new Layer(this, PL1X, PL1Y, rectW, rectH, "Presentation Layer", layerColor);
		PL2 = new Layer(this, PL2X, PL2Y, rectW, rectH, "Presentation Layer", layerColor);
		NL1 = new Layer(this, NL1X, NL1Y, rectW, rectH, "Network Layer", layerColor);
		NL2 = new Layer(this, NL2X, NL2Y, rectW, rectH, "Network Layer", layerColor);
		TL1 = new Layer(this, TL1X, TL1Y, rectW, rectH, "Transportation Layer", layerColor);
		TL2 = new Layer(this, TL2X, TL2Y, rectW, rectH, "Transportation Layer", layerColor);
		DL1 = new Layer(this, DL1X, DL1Y, rectW, rectH, "Data Link Layer", layerColor);
		DL2 = new Layer(this, DL2X, DL2Y, rectW, rectH, "Data Link Layer", layerColor);
		HL1 = new Layer(this, HL1X, HL1Y, rectW, rectH, "Physical Layer", layerColor);
		HL2 = new Layer(this, HL2X, HL2Y, rectW, rectH, "Physical Layer", layerColor);

		// set the begin location -- animation starts with start button
		locx = 0;
		locy = 0;
	}

	public void draw() {
		background(255);
		update(mouseX, mouseY);

		// set rect properties
		fill(123);
		rectMode(CENTER);
		strokeWeight(2);

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
			AL1.display(selectColor);
		} else {
			AL1.display();
		}
				
		if (locx == C1X && locy >= PL1Y && locy < SL1Y) {
			PL1.display(selectColor);
		} else {
			PL1.display();
		}
		
		if (locx == C1X && locy >= SL1Y && locy < TL1Y) {
			SL1.display(selectColor);
		} else {
			SL1.display();
		}
		
		if (locx == C1X && locy >= TL1Y && locy < NL1Y) {
			TL1.display(selectColor);
		} else {
			TL1.display();
		}
		
		if (locx == C1X && locy >= NL1Y && locy < DL1Y) {
			NL1.display(selectColor);
		} else {
			NL1.display();
		}
		
		if (locx == C1X && locy >= DL1Y && locy < HL1Y) {
			DL1.display(selectColor);
		} else {
			DL1.display();
		}
		
		if (locx == C1X && locy >= HL1Y && locy < C1Y) {
			HL1.display(selectColor);
		} else {
			HL1.display();
		}
		
		// draw the 7 layer architecture on side 2
		if (locx >= C2X && locy >= AL2Y && locy < PL2Y) {
			AL2.display(selectColor);
		} else {
			AL2.display();
		}
		
		if (locx >= C2X && locy >= PL2Y && locy < SL2Y) {
			PL2.display(selectColor);
		} else {
			PL2.display();
		}
		
		if (locx >= C2X && locy >= SL2Y && locy < TL2Y) {
			SL2.display(selectColor);
		} else {
			SL2.display();
		}
		
		if (locx >= C2X && locy >= TL2Y && locy < NL2Y) {
			TL2.display(selectColor);
		} else {
			TL2.display();
		}
		
		if (locx >= C2X && locy >= NL2Y && locy < DL2Y) {
			NL2.display(selectColor);
		} else {
			NL2.display();
		}
		
		if (locx >= C2X && locy >= DL2Y && locy < HL2Y) {
			DL2.display(selectColor);
		} else {
			DL2.display();
		}
		
		if (locx >= C2X && locy >= HL2Y && locy < C2Y) {
			HL2.display(selectColor);
		} else {
			HL2.display();
		}

		// draw text for 7L side 1
		/*
		textFont(f, 16);
		fill(0);
		textAlign(CENTER, CENTER);
		text("Application Layer", AL1X, AL1Y);
		text("Presentation Layer", PL1X, PL1Y);
		text("Session Layer", SL1X, SL1Y);
		text("Transport Layer", TL1X, TL1Y);
		text("Network Layer", NL1X, NL1Y);
		text("Datalink Layer", DL1X, DL1Y);
		text("Physical Layer", HL1X, HL1Y);

		// draw text for 7L side 2
		text("Application Layer", AL2X, AL2Y);
		text("Presentation Layer", PL2X, PL2Y);
		text("Session Layer", SL2X, SL2Y);
		text("Transport Layer", TL2X, TL2Y);
		text("Network Layer", NL2X, NL2Y);
		text("Datalink Layer", DL2X, DL2Y);
		text("Physical Layer", HL2X, HL2Y);
		*/

		// draw the lines that will connect out routers
		stroke(0);
		strokeWeight(4);
		line(C1X, C1Y, R1X, R2Y);
		line(R1X, R1Y, R2X, R2Y);
		line(R2X, R2Y, C2X, C2Y);

		imageMode(CENTER);
		// draw the computers
		// rect(200, 350, 50, 50); // c1
		image(computer, C1X, C1Y); // c1
		// rect(1400, 350, 50, 50); // c2
		image(computer, C2X, C2Y); // c2

		// draw the routers
		// rect(400, 350, 40, 40); // r1
		image(router, R1X, R1Y); // r1
		// rect(1200, 350, 40, 40); // r2
		image(router, R2X, R2Y); // r2

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

	// method used for iteration of location variables
	void drive(boolean pause) {
		/*
		 * Incrementing the location variables start at AP1. Move down the
		 * y-axis until C1 is reached. Once C1 has been reached, increment along
		 * the x-axis until C2 has been reached. From C2, move up the y-axis
		 * until AL2 has been reached.
		 */
		
		float vert = map(2, 50, 350, AL1Y, C1Y);
		float horiz = map(5, 200, 1400, C1X, C2X);
		
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
