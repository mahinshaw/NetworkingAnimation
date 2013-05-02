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
        PApplet.main(new String[]{"--present", "UI.Animation"});
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
    Router r1, r2, r3, r4, r5, r6, r7, r8;

    // declaration for Message classes
    Message m1, m2, md;
    int m1Color, m2Color, mdColor;
    String message1, message2;

    // declare the variables used for navigating the network
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
    boolean g1Move;

    DrawDijkstra dijkstra;
    boolean draw;

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

        // initialize the controller
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

        controller.addTextfield("Message1_Route")
                .setPosition(map(500, 0, 1600, 0, width), map(150, 0, 900, 0, height))
                .setSize(200, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("Message 1: Route").setFont(f).setColor(0)
        ;

        controller.addTextfield("Message1_Weight")
                .setPosition(map(500, 0, 1600, 0, width), map(250, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("Message 1: Weight").setFont(f).setColor(0);
        ;

        controller.addTextfield("Message2_Route")
                .setPosition(map(900, 0, 1600, 0, width), map(150, 0, 900, 0, height))
                .setSize(200, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("Message 2: Route").setFont(f).setColor(0)
        ;

        controller.addTextfield("Message2_Weight")
                .setPosition(map(900, 0, 1600, 0, width), map(250, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("Message 2: Weight").setFont(f).setColor(0)
        ;

        controller.addTextfield("R1_R2")
                .setPosition(map(420, 0, 1600, 0, width), map(400, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R1 to R2").setColor(0)
        ;

        controller.addTextfield("R1_R7")
                .setPosition(map(420, 0, 1600, 0, width), map(520, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R1 to R7").setColor(0)
        ;

        controller.addTextfield("R2_R3")
                .setPosition(map(780, 0, 1600, 0, width), map(340, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R2 to R3").setColor(0)
        ;

        controller.addTextfield("R2_R5")
                .setPosition(map(530, 0, 1600, 0, width), map(400, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R2 to R5").setColor(0)
        ;

        controller.addTextfield("R3_R4")
                .setPosition(map(1130, 0, 1600, 0, width), map(400, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R3 to R4").setColor(0)
        ;

        controller.addTextfield("R5_R6")
                .setPosition(map(780, 0, 1600, 0, width), map(460, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R5 to R6").setColor(0)
        ;

        controller.addTextfield("R6_R3")
                .setPosition(map(1030, 0, 1600, 0, width), map(400, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R6 to R3").setColor(0)
        ;

        controller.addTextfield("R6_R8")
                .setPosition(map(1030, 0, 1600, 0, width), map(520, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R6 to R8").setColor(0)
        ;

        controller.addTextfield("R7_R5")
                .setPosition(map(530, 0, 1600, 0, width), map(520, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R7 to R5").setColor(0)
        ;

        controller.addTextfield("R7_R8")
                .setPosition(map(780, 0, 1600, 0, width), map(580, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R7 to R8").setColor(0)
        ;

        controller.addTextfield("R8_R4")
                .setPosition(map(1130, 0, 1600, 0, width), map(520, 0, 900, 0, height))
                .setSize(30, 30)
                .setFont(f)
                .setFocus(false)
                .setColor(color(0, 0, 0))
                .setColorBackground(color(156, 156, 156))
                .setAutoClear(false)
                .getCaptionLabel().setText("R8 to R4").setColor(0)
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

        controller.addBang("Draw_Shortest")
                .setPosition(map(500, 0, 1600, 0, width), map(50, 0, 900, 0, height))
                .setSize(80, 40)
                .getCaptionLabel().align(CENTER, CENTER).setText("Dijkstra")
                .setFont(f)
        ;

        controller.addKnob("Speed")
                .setPosition(map(350, 0, 1600, 0, width), map(50, 0, 900, 0, height))
                .setColorValueLabel(color(0, 0, 0))
                .setRadius(50)
                .setRange(1, 3)
                .setNumberOfTickMarks(8)
                .setColorForeground(color(255))
                        //.setColorBackground(color(0, 160, 100))
                .setColorActive(color(255, 255, 0))
                .snapToTickMarks(true)
                .getCaptionLabel().setColor(color(0, 0, 0)).setFont(f)
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
        layerColor = color(123, 123, 123);    // grey
        selectColor = color(0, 255, 0);    // green

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
        r1 = new Router(this, 9, R1, 40, 40, "R1");
        r2 = new Router(this, 10, R2, 40, 40, "R2");
        r3 = new Router(this, 11, R3, 40, 40, "R3");
        r4 = new Router(this, 12, R4, 40, 40, "R4");
        r5 = new Router(this, 13, R5, 40, 40, "R5");
        r6 = new Router(this, 14, R6, 40, 40, "R6");
        r7 = new Router(this, 15, R7, 40, 40, "R7");
        r8 = new Router(this, 16, R8, 40, 40, "R8");

        // add edges
        start.addEdge(al1, 0);
        al1.addEdge(pl1, 0);
        pl1.addEdge(sl1, 0);
        sl1.addEdge(tl1, 0);
        tl1.addEdge(nl1, 0);
        nl1.addEdge(dl1, 0);
        dl1.addEdge(hl1, 0);
        hl1.addEdge(c1, 0);
        c1.addEdge(r1, 0);
        r1.addEdge(r2);
        r1.addEdge(r7);
        r2.addEdge(r3);
        r2.addEdge(r5);
        r3.addEdge(r4);
        r4.addEdge(c2, 0);
        r5.addEdge(r6);
        r6.addEdge(r3);
        r6.addEdge(r8);
        r7.addEdge(r5);
        r7.addEdge(r8);
        r8.addEdge(r4);
        c2.addEdge(hl2, 0);
        hl2.addEdge(dl2, 0);
        dl2.addEdge(nl2, 0);
        nl2.addEdge(tl2, 0);
        tl2.addEdge(sl2, 0);
        sl2.addEdge(pl2, 0);
        pl2.addEdge(al2, 0);
        al2.addEdge(end, 0);

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

        // initialize the graphs and assign the shortest path
        g1 = new Graph(this, m1);
        //g1.shortestPath(start, end);
        g2 = new Graph(this, m2);
        //g2.shortestPath(start, end);

        // input the values for the edge weights into the text fields
        setWeightTextFields();

        // draw Dijkstra variables
        mdColor = color(0, 255, 0);
        md = new Message(this, r1.position, 30, 30, mdColor, "");
        dijkstra = new DrawDijkstra(this, md);
    }

    public void draw() {
        background(100);

        // instruction text
        fill(0);
        text("Press 'Enter' to accept message.", map(175, 0, 1600, 0, width), map(30, 0, 900, 0, height));

        //display using message input
        if (g1Move) {
            al1.display(m1);
            pl1.display(m1);
            sl1.display(m1);
            tl1.display(m1);
            nl1.display(m1);
            dl1.display(m1);
            hl1.display(m1);
            al2.display(m1);
            pl2.display(m1);
            sl2.display(m1);
            tl2.display(m1);
            nl2.display(m1);
            dl2.display(m1);
            hl2.display(m1);
        } else if (!g1Move) {
            al1.display(m2);
            pl1.display(m2);
            sl1.display(m2);
            tl1.display(m2);
            nl1.display(m2);
            dl1.display(m2);
            hl1.display(m2);
            al2.display(m2);
            pl2.display(m2);
            sl2.display(m2);
            tl2.display(m2);
            nl2.display(m2);
            dl2.display(m2);
            hl2.display(m2);
        }

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

        fill(225);
        stroke(0);
        rect(width / 2, map(750, 0, 900, 0, height), width, 200);

        if (g1Move && isStart) {
            m1.setMessage(message1);
            g1.travel(isStart, isPaused);
            g1.output(i);
            if (m1.position.equals(nl1.position)) {
                Message1_Route(g1.routersToString());
                Message1_Weight(g1.PathLength());
            }
            if (g1.message.position.equals(end.position)) {
                Stop();
                g1Move = false;
                resetRouterWeights();
                setWeightTextFields();
            }

        } else if (!g1Move && isStart) {
            m2.setMessage(message2);
            g2.travel(isStart, isPaused);
            g2.output(i);
            if (m2.position.equals(nl1.position)) {
                Message2_Route(g2.routersToString());
                Message2_Weight(g2.PathLength());
            }
            if (g2.message.position.equals(end.position)) {

                Stop();
                g1Move = true;
                resetRouterWeights();
                setWeightTextFields();
            }
        }


        if(!isStart){
            if (draw){
                draw = dijkstra.travel();
            }
            dijkstra.drawSettled();
            dijkstra.drawRoute();
        }
    }

	/*
     * Controller Methods
	 */

    public void controlEvent(ControlEvent event) {

    }

    public void Message_1(String text) {
        message1 = text;
    }

    public void Message_2(String text) {
        message2 = text;
    }

    public void Message1_Route(String s) {
        controller.get(Textfield.class, "Message1_Route").setText(s);
    }

    public void Message1_Weight(String s) {
        controller.get(Textfield.class, "Message1_Weight").setText(s);

    }

    public void Message2_Route(String s) {
        controller.get(Textfield.class, "Message2_Route").setText(s);
    }

    public void Message2_Weight(String s) {
        controller.get(Textfield.class, "Message2_Weight").setText(s);
    }

    public void Start_Pause(boolean value) {
        if (!value) {
            dijkstra.clearDraw();
            controller.get(Toggle.class, "Start_Pause").setCaptionLabel("Pause");
            if (!isPaused && !isStart) {
                if (g1Move) {
                    controller.get(Textfield.class, "Message1_Route").setText("");
                    controller.get(Textfield.class, "Message1_Weight").setText("");
                    controller.get(Textfield.class, "Message2_Route").setText("");
                    controller.get(Textfield.class, "Message2_Weight").setText("");

                    m1.setPosition(start.position);
                    g1.setCurrentNode(0);
                    g1.shortestPath(start, end);


                } else {
                    m2.setPosition(start.position);
                    g2.setCurrentNode(0);
                    g2.shortestPath(start, end);


                }
                isStart = true;
            } else if (isPaused && isStart) {
                isPaused = false;
            }
        } else if (value) {
            controller.get(Toggle.class, "Start_Pause").setCaptionLabel("Play");
            if (!isPaused && isStart) {
                isPaused = true;
            }
        }
    }

    public void Shift_Key(boolean value) {
        if (!value) {
            controller.get(Toggle.class, "Shift_Key").setCaptionLabel("FSK");
            m1.setShiftKey(true);
            m2.setShiftKey(true);
        } else if (value) {
            controller.get(Toggle.class, "Shift_Key").setCaptionLabel("ASK");
            m1.setShiftKey(false);
            m2.setShiftKey(false);
        }
    }

    public void Speed(float value) {
        PVector speed = new PVector(value, value);
        g1.horizSpeed = PVector.mult(speed, 2);
        g1.vertSpeed = speed.get();
        g2.horizSpeed = PVector.mult(speed, 2);
        g2.vertSpeed = speed.get();
    }

    public void R1_R2(String value) {
        if (value.matches("[1-7]"))
            r1.getEdge(r2).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R1_R2").setText(String.valueOf(r1.getEdge(r2).getWeight()));
    }

    public void R1_R7(String value) {
        if (value.matches("[1-7]"))
            r1.getEdge(r7).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R1_R7").setText(String.valueOf(r1.getEdge(r7).getWeight()));
    }

    public void R2_R3(String value) {
        if (value.matches("[1-7]"))
            r2.getEdge(r3).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R2_R3").setText(String.valueOf(r2.getEdge(r3).getWeight()));
    }

    public void R2_R5(String value) {
        if (value.matches("[1-7]"))
            r2.getEdge(r5).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R2_R5").setText(String.valueOf(r2.getEdge(r5).getWeight()));
    }

    public void R3_R4(String value) {
        if (value.matches("[1-7]"))
            r3.getEdge(r4).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R3_R4").setText(String.valueOf(r3.getEdge(r4).getWeight()));
    }

    public void R5_R6(String value) {
        if (value.matches("[1-7]"))
            r5.getEdge(r6).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R5_R6").setText(String.valueOf(r5.getEdge(r6).getWeight()));
    }

    public void R6_R3(String value) {
        if (value.matches("[1-7]"))
            r6.getEdge(r3).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R6_R3").setText(String.valueOf(r6.getEdge(r3).getWeight()));
    }

    public void R6_R8(String value) {
        if (value.matches("[1-7]"))
            r6.getEdge(r8).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R6_R8").setText(String.valueOf(r6.getEdge(r8).getWeight()));
    }

    public void R7_R5(String value) {
        if (value.matches("[1-7]"))
            r7.getEdge(r5).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R7_R5").setText(String.valueOf(r7.getEdge(r5).getWeight()));
    }

    public void R7_R8(String value) {
        if (value.matches("[1-7]"))
            r7.getEdge(r8).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R7_R8").setText(String.valueOf(r7.getEdge(r8).getWeight()));
    }

    public void R8_R4(String value) {
        if (value.matches("[1-7]"))
            r8.getEdge(r4).setWeight(Integer.parseInt(value));
        else
            controller.get(Textfield.class, "R8_R4").setText(String.valueOf(r8.getEdge(r4).getWeight()));
    }

    public void Draw_Shortest(){
        if(!isStart){
            dijkstra.drawShortest(r1, r4);
            draw = true;
        }
    }

    public void Stop() {
        if (isStart) {
            isStart = false;
            isPaused = false;
            controller.get(Toggle.class, "Start_Pause").setValue(true);
            m1.setPosition(start.position);
            m2.setPosition(start.position);
            g1.clearPath();
            g2.clearPath();
        }
    }

    public void resetRouterWeights() {
        r1.randomWeights();
        r2.randomWeights();
        r3.randomWeights();
        r4.randomWeights();
        r5.randomWeights();
        r6.randomWeights();
        r7.randomWeights();
        r8.randomWeights();
    }

    public void setWeightTextFields() {
        // input the values for the edge weights into the text fields
        controller.get(Textfield.class, "R1_R2").setText(Integer.toString(r1.getEdge(r2).getWeight()));
        controller.get(Textfield.class, "R1_R7").setText(Integer.toString(r1.getEdge(r7).getWeight()));
        controller.get(Textfield.class, "R2_R3").setText(Integer.toString(r2.getEdge(r3).getWeight()));
        controller.get(Textfield.class, "R2_R5").setText(Integer.toString(r2.getEdge(r5).getWeight()));
        controller.get(Textfield.class, "R3_R4").setText(Integer.toString(r3.getEdge(r4).getWeight()));
        controller.get(Textfield.class, "R5_R6").setText(Integer.toString(r5.getEdge(r6).getWeight()));
        controller.get(Textfield.class, "R6_R3").setText(Integer.toString(r6.getEdge(r3).getWeight()));
        controller.get(Textfield.class, "R6_R8").setText(Integer.toString(r6.getEdge(r8).getWeight()));
        controller.get(Textfield.class, "R7_R5").setText(Integer.toString(r7.getEdge(r5).getWeight()));
        controller.get(Textfield.class, "R7_R8").setText(Integer.toString(r7.getEdge(r8).getWeight()));
        controller.get(Textfield.class, "R8_R4").setText(Integer.toString(r8.getEdge(r4).getWeight()));
    }
}