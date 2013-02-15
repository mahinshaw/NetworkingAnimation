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
	float rectX = 150;
	float rectY = 30;

	public void setup() {
		size(displayWidth, displayHeight);
		

		f = createFont("Arial", 16, true);

		// set images
		router = loadImage("Router1.jpg");
		computer = loadImage("Computer1.jpg");
		
		AL1X = (width/8);
		AL1Y = map(50, 0, 900, 0, height);	// application layer left
		AL2X = (width/8)*7;
		AL2Y = map(50, 0, 900, 0, height);	// application layer right
		PL1X = (width/8);
		PL1Y = map(90, 0, 900, 0, height);	// presentation layer left
		PL2X = (width/8)*7;
		PL2Y = map(90, 0, 900, 0, height);	// presentation layer right
		SL1X = (width/8);
		SL1Y = map(130, 0, 900, 0, height);	// session layer left
		SL2X = (width/8)*7;
		SL2Y = map(130, 0, 900, 0, height);	// session layer right
		TL1X = (width/8);
		TL1Y = map(170, 0, 900, 0, height);	// transportation layer left
		TL2X = (width/8)*7;
		TL2Y = map(170, 0, 900, 0, height);	// transportation layer right
		NL1X = (width/8);
		NL1Y = map(210, 0, 900, 0, height);	// network layer left
		NL2X = (width/8)*7;
		NL2Y = map(210, 0, 900, 0, height);	// network layer right
		DL1X = (width/8);
		DL1Y = map(250, 0, 900, 0, height);	// datalink layer left
		DL2X = (width/8)*7;
		DL2Y = map(250, 0, 900, 0, height);	// datalink layer right
		HL1X = (width/8);
		HL1Y = map(290, 0, 900, 0, height);	// physical layer left
		HL2X = (width/8)*7;
		HL2Y = map(290, 0, 900, 0, height);	// physical layer right

		// declare location variables for the computers and routers
		C1X = (width/8);
		C1Y = map(350, 0, 900, 0, height); // computer 1
		C2X = (width/8)*7;
		C2Y = map(350, 0, 900, 0, height); // computer 2
		R1X = (width/8)*2;
		R1Y = map(350, 0, 900, 0, height); // router 1
		R2X = (width/8)*6;
		R2Y = map(350, 0, 900, 0, height); // router 2

		// set the start location for
		locx = AL1X;
		locy = AL1Y;
	}

	public void draw() {
		background(255);

		// set rect properties
		fill(123);
		rectMode(CENTER);
		strokeWeight(2);

		// draw the 7 layer architecture on side 1
		if (locx == C1X && locy < PL1Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(AL1X, AL1Y, rectX, rectY);
		if (locx == C1X && locy >= PL1Y && locy < SL1Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(PL1X, PL1Y, rectX, rectY);
		if (locx == C1X && locy >= SL1Y && locy < TL1Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(SL1X, SL1Y, rectX, rectY);
		if (locx == C1X && locy >= TL1Y && locy < NL1Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(TL1X, TL1Y, rectX, rectY);
		if (locx == C1X && locy >= NL1Y && locy < DL1Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(NL1X, NL1Y, rectX, rectY);
		if (locx == C1X && locy >= DL1Y && locy < HL1Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(DL1X, DL1Y, rectX, rectY);
		if (locx == C1X && locy >= HL1Y && locy < C1Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(HL1X, HL1Y, rectX, rectY);

		// draw the 7 layer architecture on side 2
		if (locx == C2X && locy >= AL2Y && locy < PL2Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(AL2X, AL2Y, rectX, rectY);
		if (locx == C2X && locy >= PL2Y && locy < SL2Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(PL2X, PL2Y, rectX, rectY);
		if (locx == C2X && locy >= SL2Y && locy < TL2Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(SL2X, SL2Y, rectX, rectY);
		if (locx == C2X && locy >= TL2Y && locy < NL2Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(TL2X, TL2Y, rectX, rectY);
		if (locx == C2X && locy >= NL2Y && locy < DL2Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(NL2X, NL2Y, rectX, rectY);
		if (locx == C2X && locy >= DL2Y && locy < HL2Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(DL2X, DL2Y, rectX, rectY);
		if (locx == C2X && locy >= HL2Y && locy < C2Y) {
			fill(255, 0, 0);
		} else {
			fill(123);
		}
		rect(HL2X, HL2Y, rectX, rectY);

		// draw text for 7L side 1
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
		textAlign(CENTER, CENTER);
		text("Application Layer", AL2X, AL2Y);
		text("Presentation Layer", PL2X, PL2Y);
		text("Session Layer", SL2X, SL2Y);
		text("Transport Layer", TL2X, TL2Y);
		text("Network Layer", NL2X, NL2Y);
		text("Datalink Layer", DL2X, DL2Y);
		text("Physical Layer", HL2X, HL2Y);

		// draw the lines that will connect out routers
		stroke(0);
		strokeWeight(4);
		line(C1X, C1Y, R1X, R2Y);
		line(R1X, R1Y, R2X, R2Y);
		line(R2X, R2Y, C2X, C2Y);

		imageMode(CENTER);
		// draw the computers
		//rect(200, 350, 50, 50); // c1
		image(computer, C1X, C1Y);	// c1
		//rect(1400, 350, 50, 50); // c2
		image(computer, C2X, C2Y);	// c2

		// draw the routers
		//rect(400, 350, 40, 40); // r1
		image(router, R1X, R1Y);	// r1
		//rect(1200, 350, 40, 40); // r2
		image(router, R2X, R2Y);	// r2

		// draw the transfer node
		if (locy == C1Y) {
			fill(255, 0, 0);
			strokeWeight(2);
			ellipse(locx, locy, 30, 30);
		}

		/*
		 * Incrementing the location variables start at AP1. Move down the
		 * y-axis until C1 is reached. Once C1 has been reached, increment along
		 * the x-axis until C2 has been reached. From C2, move up the y-axis
		 * until AL2 has been reached.
		 */

		if (locy < C1Y && locx == C1X) {
			locy = locy + 2;
		} else if (locy == C1Y && locx < C2X) {
			locx = locx + 5;
		} else if (locx == C2X && locy > AL2Y) {
			locy = locy - 2;
		} else if (locx == AL2X && locy == AL2Y) {
			locx = AL1X;
			locy = AL2Y;
		}
	}

}
