package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a layer location within the animation
 */

public class Layer extends Node {

    int color;
    String name;
    String font;
    int point;
    boolean aa;
    PFont f;

    public Layer(PApplet p, int o, float x, float y, float w, float h, String t, int c) {
        super(p, o);
        super.setVector(x, y);
        super.setWidth(w);
        super.setHeight(h);
        name = t;
        font = "Arial";
        point = 16;
        aa = true;
        f = parent.createFont(font, point, aa);
        color = c;
    }

    public Layer(PApplet p, int o, PVector v, float w, float h, String t, int c) {
        super(p, o);
        super.setVector(v);
        super.setWidth(w);
        super.setHeight(h);
        name = t;
        font = "Georgia";
        point = 16;
        aa = true;
        f = parent.createFont(font, point, aa);
        color = c;
    }

    public void setColor(int c) {
        color = c;
    }

    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public void setName(String t) {
        name = t;
    }

    public void setFont(String s) {
        font = s;
        f = parent.createFont(font, point, aa);
    }

    public void setPoint(int p) {
        point = p;
        f = parent.createFont(font, point, aa);
    }

    public void setAA(boolean x) {
        aa = x;
        f = parent.createFont(font, point, aa);
    }

    public String className() {
        return "Layer";
    }

    @SuppressWarnings("static-access")
    public void display(int c) {

        parent.stroke(0);
        parent.strokeWeight(2);
        parent.fill(c);
        parent.rectMode(parent.CENTER);
        parent.rect(position.x, position.y, width, height);

        parent.textFont(f);
        parent.fill(0);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.text(name, position.x, position.y);
    }

    public void display() {
        display(color);
    }

    public void display(Message m) {
        if (m.position.x == position.x
                && m.position.y >= (position.y - (height / 2))
                && m.position.y <= (position.y + (height / 2))) {
            display(m.color);
        } else {
            display(color);
        }
    }

    @SuppressWarnings("static-access")
    public void output(Message m, int i) {
        if (m.position.x == position.x
                && m.position.y > (position.y - (height / 2))
                && m.position.y <= (position.y + (height / 2))) {

            float x = (parent.width / 2);
            float y = parent.map(750, 0, 900, 0, parent.height);

            switch (name) {
                case "Application Layer":
                    parent.fill(0);
                    parent.text("The message is: \n" + m.getMessage(), x, y);
                    break;
                case "Presentation Layer":

                    break;
                case "Session Layer":

                    break;
                case "Transportation Layer":
                    // port address
                    parent.fill(0);
                    parent.text("The port address is: \n" + m.getType(), x, y);

                    break;
                case "Network Layer":
                    // IP address
                    parent.fill(0);
                    if (m.position.x < parent.width / 2) {
                        parent.text("The Source IP address is: \n" + m.getSource(), x, y);
                    } else {
                        parent.text(
                                "The Destination IP address is: \n"
                                        + m.getDestination(), x, y);
                    }

                    break;
                case "Data Link Layer":
                    // MAC Address
                    parent.fill(0);
                    if (m.position.x < parent.width / 2) {
                        parent.text(
                                "The Source IP address is: \n" + m.getSourMAC(), x,
                                y);
                    } else {
                        parent.text(
                                "The Destination IP address is: \n"
                                        + m.getDestMAC(), x, y);
                    }
                    break;
                case "Physical Layer":
                    // preamble and sfd
                    parent.fill(0);
                    parent.text("The Preamble is: " + m.getPreamble()
                            + "\nThe SFD is: " + m.getSFD(), x, y);
                    break;
            }
        }
    }
}