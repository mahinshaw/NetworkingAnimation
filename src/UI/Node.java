package UI;

import java.util.ArrayList;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a super class for all the locations within the animation.
 * 
 * @ parent -  the calling PApplet
 * @ v - the location of the node.
 * @ w, h - width and height.
 * 
 */
public class Node implements Comparable<Node> {
    PApplet parent;
    PVector position;
    float width, height;
    ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Node> successors = new ArrayList<Node>();
    int edgeCount;
    private final int order;

    public Node(PApplet p, int o) {
        parent = p;
        position = new PVector();
        successors.clear();
        edgeCount = 0;
        order = o;
    }

    public Node(PApplet p, int o, PVector v) {
        parent = p;
        position = new PVector(v.x, v.y);
        successors.clear();
        width = 0;
        height = 0;
        edgeCount = 0;
        order = o;
    }

    public PVector getVector() {
        return position.get();
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public void setVector(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public void setVector(PVector v) {
        position = v.get();
    }

    public void setX(float x) {
        position.x = x;
    }

    public void setY(float y) {
        position.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float w) {
        width = w;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float h) {
        height = h;
    }

    public int getOrder() {
        return order;
    }

    public String className() {
        return "Node";
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Edge getEdge(Node end) {
        Edge f = new Edge(parent, this, end);
        for (Edge e : edges) {
            if (e.start == this && e.end == end)
                f = e;
        }
        return f;
    }

    public void addEdge(Node n) {
        Edge e = new Edge(parent, this, n);
        edges.add(e);
        successors.add(n);
        edgeCount++;
    }

    public void addEdge(Node n, int w) {
        Edge e = new Edge(parent, this, n, w);
        edges.add(e);
        successors.add(n);
        edgeCount++;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public void display() {
        if (edgeCount > 0) {
            for (int i = 0; i < edgeCount; i++) {
                Edge e = edges.get(i);
                e.display();
            }
        }
    }

    public void output(Message m, int i) {

    }

    public void randomWeights() {
        for (Edge e : edges) {
            e.setRandomWeight();
        }
    }

    @Override
    public int compareTo(Node n) {
        return this.order - n.order;
    }
}
