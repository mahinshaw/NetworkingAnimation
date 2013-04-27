package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a computer location within the animation
 */

public class Computer extends Node {

    PImage computer;

    public Computer(PApplet p, int o, float x, float y, float w, float h) {
        super(p, o);
        super.setVector(x, y);
        super.setWidth(w);
        super.setHeight(h);
        computer = parent.loadImage("Computer1.jpg");
    }

    public Computer(PApplet p, int o, PVector v, float w, float h) {
        super(p, o);
        super.setVector(v);
        super.setWidth(w);
        super.setHeight(h);
        computer = parent.loadImage("Computer1.jpg");
    }

    public String className() {
        return "Computer";
    }

    @SuppressWarnings("static-access")
    public void display() {
        super.display();
        parent.imageMode(parent.CENTER);
        parent.image(computer, position.x, position.y);
    }

    @SuppressWarnings("static-access")
    public void output(Message m, int i) {
        if (m.position.x >= (position.x - (width / 2)) && m.position.x <= (position.x + (width / 2))
                && m.position.y >= (position.y - (height / 2))
                && m.position.y <= (position.y + (height / 2))) {
            float y = parent.map(750, 0, 900, 0, parent.height);
            m.drawDigitalWave(i, y);
        }
    }
}