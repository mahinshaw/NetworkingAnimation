package UI;

import processing.core.*;

import java.util.Random;

/*
 * @ author: Mark Hinshaw
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 *
 * This class is used to add an edge from node to node to connect the graph.
 *
 */

public class Edge {

    private PApplet parent;

    Random rand = new Random();
    private final int MAX = 7;
    private final int MIN = 1;

    Node start, end;

    private int weight;
    private int color;

    public Edge(PApplet p, Node s, Node e) {
        parent = p;
        start = s;
        end = e;
        weight = getRandom();
        color = parent.color(255, 255, 255);
    }

    public Edge(PApplet p, Node s, Node e, int w) {
        parent = p;
        start = s;
        end = e;
        weight = w;
        color = parent.color(255, 255, 255);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int w) {
        weight = w;
    }

    public void setColor(int c) {
        color = c;
    }

    public int getRandom() {
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }

    public void setRandomWeight() {
        this.weight = getRandom();
    }

    public void display() {
        if (start.className() != "Layer" && end.className() != "Layer") {
            parent.stroke(color);
            parent.line(start.position.x, start.position.y, end.position.x, end.position.y);
        }
    }
}