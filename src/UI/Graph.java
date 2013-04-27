package UI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import processing.core.*;

/*
 * @ author: Mark Hinshaw
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 *
 * This class is a message that travels from location to location.  This method will contain all the information needed for display within the message.
 * All calculations are done locally and can be called for use in other classes.
 *
 */

public class Graph {

    PApplet parent;
    List<Node> nodes = new ArrayList<Node>();
    ;
    List<Node> routers = new ArrayList<Node>();
    Message message;
    PVector horizSpeed, vertSpeed;
    private int nodeCount;
    private int currentNode;
    private boolean ackSent;

    public Graph(PApplet p, Message m) {
        parent = p;
        message = m;
        horizSpeed = new PVector((float) 2, (float) 2);
        vertSpeed = new PVector((float) 1, (float) 1);
        nodeCount = 0;
        currentNode = 0;
        ackSent = true;
    }

    public void addNode(Node n) {
        nodes.add(n);
        nodeCount++;
        if (n.className().equalsIgnoreCase("Router"))
            routers.add(n);
    }

    public void setCurrentNode(int i) {
        currentNode = i;
    }

    public void output(int i) {
        for (Node n : nodes) {
            if (ackSent) {
                n.output(message, i);
            }
        }
    }

    public ArrayList<Node> getSuccessors(Node n) {
        return n.successors;
    }


    public int getDistance(Node n, Node m) {
        /*
        there is a better way to do this, WORK ON IT!!!!!!!!
         */

        int distance = Integer.MAX_VALUE;

        for (Edge e : n.getEdges()) {
            if (e.end.equals(m)) {
                distance = e.getWeight();
            }
        }
        return distance;
    }

    /*
     * need to finish the shortest path implementation
     */
    public void shortestPath(Node start, Node finish) {
        ShortestPath route = new ShortestPath(this);
        route.execute(start, finish);

        for (Node n = finish; n != null; n = route.getPredecessor(n)) {
            addNode(n);
        }

        Collections.reverse(nodes);
        Collections.reverse(routers);
    }

    public void clearPath() {
        nodes.clear();
        routers.clear();
        currentNode = 0;
        nodeCount = 0;
    }

    public void sendACK() {
        if (nodes.get(currentNode).className().equals("Router") && nodes.get(currentNode - 1).className().equals("Router")) {
            ackSent = false;
        } else {
            ackSent = true;
        }
    }

    public void travel(boolean start, boolean pause) {
        int latency;
        if (currentNode < nodeCount - 1 && start) {
            message.setStart(nodes.get(currentNode).position);
            message.setEnd(nodes.get(currentNode + 1).position);
            message.display();
            latency = nodes.get(currentNode).getEdge(nodes.get(currentNode+1)).getWeight();
            if (!pause && !ackSent) {
                message.displayACK();
                driveACK();
            } else if (!pause && ackSent) {
                drive(latency);
            }
        }
    }

    public void drive(int latency) {
        PVector slope = PVector.sub(message.end, message.start);
        slope.normalize();
        if (latency > 0){
            slope.div(latency);
        }
        if (slope.x == 0 && slope.y > 0) {
            if (message.position.y < message.end.y) {
                slope = PVector.mult(slope, vertSpeed);
                message.position.add(slope);
            } else {
                message.setACK(message.end);
                message.setACKStart(message.end);
                message.setACKEnd(message.start);
                currentNode++;
                sendACK();
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
                sendACK();
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
                sendACK();
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
                sendACK();
                message.setPosition(nodes.get(currentNode).position);
            }
        }
    }

    public void driveACK() {
        PVector slope = new PVector();
        PVector ackSpeed = PVector.mult(horizSpeed, new PVector(2, 2));
        slope = PVector.sub(message.ackEnd, message.ackStart);
        slope.normalize();

        if (slope.x < 0) {
            if (message.ack.x >= message.ackEnd.x) {
                slope = PVector.mult(slope, ackSpeed);
                message.ack.add(slope);
            } else {
                ackSent = true;
            }
        }
    }

    public String routersToString() {
        StringBuilder str = new StringBuilder();

        for (Node r : routers) {
            str.append(r.name + " ");
        }

        return str.toString();
    }

    public String PathLength() {
        int sum = 0;

        for (int i = 0; i < routers.size() - 1; i++) {
            sum += routers.get(i).getEdge(routers.get(i + 1)).getWeight();
        }

        return String.valueOf(sum);
    }
}