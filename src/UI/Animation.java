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
	int locx, locy;

	// declare location variables for layers
	final int AL1X = 200, AL1Y = 50; // application layer left
	final int AL2X = 1400, AL2Y = 50; // application layer right
	final int PL1X = 200, PL1Y = 90; // presentation layer left
	final int PL2X = 1400, PL2Y = 90; // presentation layer right
	final int SL1X = 200, SL1Y = 130; // session layer left
	final int SL2X = 1400, SL2Y = 130; // session layer right
	final int TL1X = 200, TL1Y = 170; // transportation layer left
	final int TL2X = 1400, TL2Y = 170; // transportation layer right
	final int NL1X = 200, NL1Y = 210; // network layer left
	final int NL2X = 1400, NL2Y = 210; // network layer right
	final int DL1X = 200, DL1Y = 250; // datalink layer left
	final int DL2X = 1400, DL2Y = 250; // datalink layer right
	final int HL1X = 200, HL1Y = 290; // physical layer left
	final int HL2X = 1400, HL2Y = 290; // physical layer right

	// declare location variables for the computers and routers
	final int C1X = 200, C1Y = 350; // computer 1
	final int C2X = 1400, C2Y = 350; // computer 2
	final int R1X = 400, R1Y = 350; // router 1
	final int R2X = 1200, R2Y = 350; // router 2

	// declare variable for font
	PFont f;

	// declare variable for images
	PImage router;
	PImage computer;

	// rectangle sizes
	int rectX = 150;
	int rectY = 30;

	public void setup() {
		size(1600, 900);

		f = createFont("Arial", 16, true);

		// set images
		router = loadImage("Router1.jpg");
		computer = loadImage("Computer1.jpg");

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
		rect(200, 350, 50, 50); // c1
		image(computer, C1X, C1Y);
		rect(1400, 350, 50, 50); // c2
		image(computer, C2X, C2Y);

		// draw the routers
		rect(400, 350, 40, 40); // r1
		image(router, R1X, R1Y);
		rect(1200, 350, 40, 40); // r2
		image(router, R2X, R2Y);

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
