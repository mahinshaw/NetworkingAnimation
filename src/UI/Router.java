package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a router location within the anmination.
 */

public class Router extends Node {

    PImage router;

    public Router(PApplet p, int o, float x, float y, float w, float h, String n) {
        super(p, o);
        super.setVector(x, y);
        super.setWidth(w);
        super.setHeight(h);
        this.router = parent.loadImage("Router1.jpg");
        this.name = n;
    }

    public Router(PApplet p, int o, PVector v, float w, float h, String n) {
        super(p, o);
        super.setVector(v);
        super.setWidth(w);
        super.setHeight(h);
        this.router = parent.loadImage("Router1.jpg");
        this.name = n;
    }

    public String className() {
        return "Router";
    }

    @SuppressWarnings("static-access")
    public void display() {
        super.display();
        parent.imageMode(parent.CENTER);
        parent.image(router, position.x, position.y);
    }

    @SuppressWarnings("static-access")
    public void output(Message m, int i) {
        if (m.position.x >= (position.x - (width)) && m.position.x <= (position.x + (width))
                && m.position.y >= (position.y - (height / 2))
                && m.position.y <= (position.y + (height / 2))) {
            float y = parent.map(750, 0, 900, 0, parent.height);
            m.drawSineWave(i, y);
        }
    }
}