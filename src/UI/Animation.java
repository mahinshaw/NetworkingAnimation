package UI;

import processing.core.*;

public class Animation extends PApplet {

	public void setup() {
		size(1600, 900);
	}

	public void draw() {
		// draw the 7 layer architecture on side 1
		rect(100, 50, 100, 30);
		rect(100, 85, 100, 30);
		rect(100, 120, 100, 30);
		rect(100, 155, 100, 30);
		rect(100, 190, 100, 30);
		rect(100, 225, 100, 30);
		rect(100, 260, 100, 30);

		// draw the 7 layer architecture on side 2
		rect(1400, 50, 100, 30);
		rect(1400, 85, 100, 30);
		rect(1400, 120, 100, 30);
		rect(1400, 155, 100, 30);
		rect(1400, 190, 100, 30);
		rect(1400, 225, 100, 30);
		rect(1400, 260, 100, 30);

		// draw the routers
		// r1
		rect(300, 320, 40, 40);
		// r2
		rect(1260, 320, 40, 40);

		// draw the lines that will connect out routers

		ellipse(150, 50, 30, 30);
	}

}
