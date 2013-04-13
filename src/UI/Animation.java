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
	
	// integer for drawing waves
	int i, j;
	
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
	Router r1, r2, r3,  r4, r5, r6, r7, r8;
	
	// declaration for Message classes 
	Message m1, m2;
	int m1Color, m2Color;
	String message1, message2;
	
	// declare the variables used for navigating the network
	PVector loc;
	Node start, end;

	// declare location variables for layers
	PVector AL1, AL2;
	PVector PL1, PL2;
	PVector SL1, SL2;
	PVector TL1, TL2;
	PVector NL1, NL2;
	PVector DL1, DL2;
	PVector HL1, HL2;

	// declare location variables for the computers and routers
	PVector C1, C2;
	PVector R1, R2, R3, R4, R5, R6, R7, R8;
	
	
	// declare the Graph
	Graph g1;
	
	
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
	boolean g1Move;
	
	public void setup() {
		size(displayWidth, displayHeight);

		i = 0;
		j = 0;
		isPaused = false;

		f = createFont("Georgia", 16, true);
		
		// set the begin location -- animation starts with start button
		PVector v1 = new PVector((width / 8), map(160, 0, 900, 0, height));
		PVector v2 = new PVector((width / 8) * 7, map(160, 0, 900, 0, height));
		start = new Node(this, 0, v1);
		end = new Node(this, 25, v2);
		
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
		
		controller.addToggle("Shift_Key")
		.setPosition(map(1150, 0, 1600, 0, width), map(50, 0, 900, 0, height))
		.setSize(80, 40)
		.setCaptionLabel("FSK")
		.getCaptionLabel().align(CENTER, CENTER)
		.setFont(f);
		;
		
		controller.addBang("Stop")
		.setPosition(map(1410, 0, 1600, 0, width), map(50, 0, 900, 0, height))
		.setSize(80, 40)
		.getCaptionLabel().align(CENTER, CENTER)
		.setFont(f)
		;
		
		controller.addKnob("Speed")
		.setPosition((float)map(350, 0, 1600, 0, width), (float)map(50, 0, 900, 0, height))
		.setRadius(50)
		.setRange(1, 3)
		.setNumberOfTickMarks(8)
		.snapToTickMarks(true)
		.getCaptionLabel().setColor(0).setFont(f)
		;
		
		
		// set flags for buttons
		isStart = false;
		isPaused = false;
		isEnd = true;
		g1Move = true;
		
		// set images
		router = loadImage("Router1.jpg");
		computer = loadImage("Computer1.jpg");

		// declare location for layers
		AL1 = new PVector((width / 8), map(180, 0, 900, 0, height));
		AL2 = new PVector((width / 8) * 7, map(180, 0, 900, 0, height));
		PL1 = new PVector((width / 8), map(220, 0, 900, 0, height));
		PL2 = new PVector((width / 8) * 7, map(220, 0, 900, 0, height));
		SL1 = new PVector((width / 8), map(260, 0, 900, 0, height));
		SL2 = new PVector((width / 8) * 7, map(260, 0, 900, 0, height));
		TL1 = new PVector((width / 8), map(300, 0, 900, 0, height));
		TL2 = new PVector((width / 8) * 7, map(300, 0, 900, 0, height));
		NL1 = new PVector((width / 8), map(340, 0, 900, 0, height));
		NL2 = new PVector((width / 8) * 7, map(340, 0, 900, 0, height));
		DL1 = new PVector((width / 8), map(380, 0, 900, 0, height));
		DL2 = new PVector((width / 8) * 7, map(380, 0, 900, 0, height));
		HL1 = new PVector((width / 8), map(420, 0, 900, 0, height));
		HL2 = new PVector((width / 8) * 7, map(420, 0, 900, 0, height));
		
		// declare location variables for the computers and routers
		C1 = new PVector((width / 16) * 2, map(480, 0, 900, 0, height));
		C2 = new PVector((width / 16) * 14, map(480, 0, 900, 0, height));
		R1 = new PVector((width / 16) * 4, map(480, 0, 900, 0, height));
		R2 = new PVector((width / 16) * 5, map(360, 0, 900, 0, height));
		R3 = new PVector((width / 16) * 11, map(360, 0, 900, 0, height));
		R4 = new PVector((width / 16) * 12, map(480, 0, 900, 0, height));
		R5 = new PVector((width / 16) * 6, map(480, 0, 900, 0, height));
		R6 = new PVector((width / 16) * 10, map(480, 0, 900, 0, height));
		R7 = new PVector((width / 16) * 5, map(600, 0, 900, 0, height));
		R8 = new PVector((width / 16) * 11, map(600, 0, 900, 0, height));
		
		
		// initialize layer colors
		layerColor = color(123, 123, 123);	// grey
		selectColor = color(0, 255, 0);	// green
		
		// initialize layer classes
		al1 = new Layer(this, 1, AL1, rectW, rectH, "Application Layer", layerColor);
		al2 = new Layer(this, 24, AL2, rectW, rectH, "Application Layer", layerColor);
        pl1 = new Layer(this, 2, PL1, rectW, rectH, "Presentation Layer", layerColor);
        pl2 = new Layer(this, 23, PL2, rectW, rectH, "Presentation Layer", layerColor);
        sl1 = new Layer(this, 3, SL1, rectW, rectH, "Session Layer", layerColor);
        sl2 = new Layer(this, 22, SL2, rectW, rectH, "Session Layer", layerColor);
        nl1 = new Layer(this, 4, NL1, rectW, rectH, "Network Layer", layerColor);
		nl2 = new Layer(this, 21, NL2, rectW, rectH, "Network Layer", layerColor);
		tl1 = new Layer(this, 5, TL1, rectW, rectH, "Transportation Layer", layerColor);
		tl2 = new Layer(this, 20, TL2, rectW, rectH, "Transportation Layer", layerColor);
		dl1 = new Layer(this, 6, DL1, rectW, rectH, "Data Link Layer", layerColor);
		dl2 = new Layer(this, 19, DL2, rectW, rectH, "Data Link Layer", layerColor);
		hl1 = new Layer(this, 7, HL1, rectW, rectH, "Physical Layer", layerColor);
		hl2 = new Layer(this, 18, HL2, rectW, rectH, "Physical Layer", layerColor);
		
		
		//initialize Computer classes
		c1 = new Computer(this, 8, C1, 50, 50);
		c2 = new Computer(this, 17, C2, 50, 50);
		
		//initialize Router classes
		r1 = new Router(this, 9, R1, 40, 40);
		r2 = new Router(this, 10, R2, 40, 40);
		r3 = new Router(this, 11, R3, 40, 40);
		r4 = new Router(this, 12, R4, 40, 40);
		r5 = new Router(this, 13, R5, 40, 40);
		r6 = new Router(this, 14, R6, 40, 40);
		r7 = new Router(this, 15, R7, 40, 40);
		r8 = new Router(this, 16, R8, 40, 40);
		
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
		r1.addEdge(r2, 7);
		r1.addEdge(r7, 5);
		r2.addEdge(r3, 3);
		r2.addEdge(r5, 2);
		r3.addEdge(r4, 5);
		r4.addEdge(c2);
		r5.addEdge(r6, 3);
		r6.addEdge(r3, 6);
		r6.addEdge(r8, 2);
		r7.addEdge(r5, 4);
		r7.addEdge(r8, 5);
		r8.addEdge(r4, 3);
		c2.addEdge(hl2);
		hl2.addEdge(dl2);
		dl2.addEdge(nl2);
		nl2.addEdge(tl2);
		tl2.addEdge(sl2);
		sl2.addEdge(pl2);
		pl2.addEdge(al2);
		al2.addEdge(end);
		
		// initialize message colors
		m1Color = color(255, 0, 0);
		m2Color = color(0, 0, 255);
		
		// initialize messages
		message1 = controller.get(Textfield.class, "Message_1").getText();
		message2 = controller.get(Textfield.class, "Message_2").getText();
		
		// initialize Message classes
		m1 = new Message(this, start.position, 30, 30, m1Color, message1);
		m2 = new Message(this, start.position, 30, 30, m2Color, message2);
		m2.setDestination("174.15.121.5");
		m2.setDestMAC("62:FE:36:A5");
		
        // initialize the graph and assign the shortest path
        g1 = new Graph(this, m1);
        g1.shortestPath(start, end);
	}

	public void draw() {
		background(200);
		
		// instruction text
		fill(0);
		text("Press 'Enter' to accept message.", map(175, 0, 1600, 0, width), map(30, 0, 900, 0, height));
		
		//display using message input
		if (g1Move){
			al1.display(m1);
			pl1.display(m1);
			sl1.display(m1);
			tl1.display(m1);
			nl1.display(m1);
			dl1.display(m1);
			hl1.display(m1);
		}else if(!g1Move){
			al1.display(m2);
			pl1.display(m2);
			sl1.display(m2);
			tl1.display(m2);
			nl1.display(m2);
			dl1.display(m2);
			hl1.display(m2);	
		}
		// display side 2 
		al2.display(m1);
		pl2.display(m1);
		sl2.display(m1);
		tl2.display(m1);
		nl2.display(m1);
		dl2.display(m1);
		hl2.display(m1);
		
		// draw the computers
		c1.display();
				
		// draw the routers
		r1.display();
		r2.display();				
		r7.display();
		r5.display();
		r6.display();
		r3.display();
		r8.display();
		r4.display();
		
		// draw computer 2
		c2.display();
		
		if (g1Move && isStart){
			m1.setMessage(message1);
			g1.travel(isStart, isPaused);
			g1.output(i);
			//i--;
			if(g1.message.position.equals(end.position)){
				Stop();
				g1Move = false;
			}
			
		}
		
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
				m1.setPosition(start.position);
				g1.setCurrentNode(0);
				m2.setPosition(start.position);
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
	
	public void Shift_Key(boolean value){
		if (!value){
			controller.get(Toggle.class, "Shift_Key").setCaptionLabel("FSK");
			m1.setShiftKey(true);
			m2.setShiftKey(true);
		}else if (value){
			controller.get(Toggle.class, "Shift_Key").setCaptionLabel("ASK");
			m1.setShiftKey(false);
			m2.setShiftKey(false);
		}
	}
	
	public void Speed(float value){
		PVector speed = new PVector(value, value);
		g1.horizSpeed = PVector.mult(speed, 2);
		g1.vertSpeed = speed.get();
	}
	
	public void Stop(){
		if(isStart){
			isStart = false;
			isPaused = false;
			controller.get(Toggle.class, "Start_Pause").setValue(true);
			m1.setPosition(start.position);
			m2.setPosition(start.position);
		}
	}
}
