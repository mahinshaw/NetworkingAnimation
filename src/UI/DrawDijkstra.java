package UI;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

/*
 * @ author: Mark Hinshaw
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 *
 * This class will be used to animate the execution of the ShortestPath class
 *
 */
public class DrawDijkstra {
    private List<Node> nodes = new ArrayList<Node>();
    private Graph graph;
    private ShortestPath route = new ShortestPath(graph);
    private Message message;

    private PApplet parent;

    private PVector vertSpeed, horizSpeed;

    private int currentNode;

    public DrawDijkstra(PApplet p, Message m){
        this.parent = p;
        message = m;
        graph = new Graph(parent, message);
    }

    public void drive() {
        PVector slope = PVector.sub(message.end, message.start);
        slope.normalize();

        if (slope.x == 0 && slope.y > 0) {
            if (message.position.y < message.end.y) {
                slope = PVector.mult(slope, vertSpeed);
                message.position.add(slope);
            } else {
                message.setACK(message.end);
                message.setACKStart(message.end);
                message.setACKEnd(message.start);
                currentNode++;
                message.setPosition(nodes.get(currentNode).position);
            }
        } else if (slope.x > 0 && slope.y == 0) {
            if (message.position.x < message.end.x) {
                slope = PVector.mult(slope, horizSpeed);
                message.position.add(slope);
            } else {
                message.setACK(message.end);
                message.setACKStart(message.end);
                message.setACKEnd(message.start);
                currentNode++;
                message.setPosition(nodes.get(currentNode).position);
            }
        } else if (slope.x == 0 && slope.y < 0) {
            if (message.position.y > message.end.y) {
                slope = PVector.mult(slope, vertSpeed);
                message.position.add(slope);
            } else {
                message.setACK(message.end);
                message.setACKStart(message.end);
                message.setACKEnd(message.start);
                currentNode++;
                message.setPosition(nodes.get(currentNode).position);
            }
        } else if (slope.x > 0 && slope.y != 0) {
            if (message.position.x < message.end.x) {
                slope = PVector.mult(slope, horizSpeed);
                message.position.add(slope);
            } else {
                message.setACK(message.end);
                message.setACKStart(message.end);
                message.setACKEnd(message.start);
                currentNode++;
                message.setPosition(nodes.get(currentNode).position);
            }
        }
    }
}
