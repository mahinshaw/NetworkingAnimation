package UI;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.*;

/*
 * @ author: Mark Hinshaw
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 *
 * This class will be used to animate the execution of the ShortestPath class
 *
 */
public class DrawDijkstra extends Graph {
    Node current, start, end;
    private List<Node> successorNodes = new ArrayList<Node>();

    public static final int HIGH_VALUE = Integer.MAX_VALUE;
    private static final int INITIAL_VALUE = 8;
    private final Map<Node, Integer> shortestDistances = new HashMap<Node, Integer>();
    private final Comparator<Node> shortestDistanceComparator = new Comparator<Node>() {
        public int compare(Node left, Node right) {
            int result = getShortestDistance(left) - getShortestDistance(right);
            if (left == end){
                return 1;
            }else if(right == end){
                return -1;
            }else{
                return (result == 0) ? left.compareTo(right) : result;
            }
        }
    };
    private final PriorityQueue<Node> unsettled = new PriorityQueue<Node>(INITIAL_VALUE, shortestDistanceComparator);
    private final Set<Node> settled = new HashSet<Node>();
    private final Map<Node, Node> predecessors = new HashMap<Node, Node>();

    private int successorLength;
    private int currentSuccessor;

    public DrawDijkstra(PApplet p, Message m) {
        super(p, m);

        currentNode = 0;
        nodeCount = 0;


    }

    public boolean travel() {

        drawSettled();
        drawUnsettled();

        if (!this.current.equals(this.end)) {


            if (currentSuccessor < successorLength) {
                if(!isSettled(successorNodes.get(currentSuccessor))){
                // send message to each successor
                message.setStart(this.current.position);
                message.setEnd(successorNodes.get(currentSuccessor).position);
                message.display();
                drive();
                }else{
                    currentSuccessor++;
                }
            }
            if (currentSuccessor == successorLength) {
                //get the next to check
                //set the current node to the node with the shortest distance
                settled.add(current);
                System.out.println(current.name + " is settled");

                Node a = current;
                current = NextNeighbor(a);
                successorNodes = getSuccessors(current);
                successorLength = successorNodes.size();
                message.setPosition(current.position);

                //set the currentSuccessor to 0
                currentSuccessor = 0;

            }

            return true;
        }else {
            settled.add(current);

            for (Node n = end; n != null; n = getPredecessor(n)) {
                addNode(n);
            }
            Collections.reverse(nodes);

            return false;
        }


    }

    public void drive() {
        PVector slope = PVector.sub(message.end, message.start);
        slope.normalize();

        if (slope.x == 0 && slope.y > 0) {
            if (message.position.y < message.end.y) {
                slope = PVector.mult(slope, vertSpeed);
                message.position.add(slope);
            } else {
                currentSuccessor++;
                message.setPosition(current.position);
            }
        } else if (slope.x > 0 && slope.y == 0) {
            if (message.position.x < message.end.x) {
                slope = PVector.mult(slope, horizSpeed);
                message.position.add(slope);
            } else {
                currentSuccessor++;
                message.setPosition(current.position);
            }
        } else if (slope.x == 0 && slope.y < 0) {
            if (message.position.y > message.end.y) {
                slope = PVector.mult(slope, vertSpeed);
                message.position.add(slope);
            } else {
                currentSuccessor++;
                message.setPosition(current.position);
            }
        } else if (slope.x > 0 && slope.y != 0) {
            if (message.position.x < message.end.x) {
                slope = PVector.mult(slope, horizSpeed);
                message.position.add(slope);
            } else {
                currentSuccessor++;
                message.setPosition(current.position);
            }
        }
    }

    public void drawShortest(Node s, Node e) {

        this.current = s;
        this.start = s;
        this.end = e;

        initialize(start);

        this.currentSuccessor = 0;
        successorNodes = getSuccessors(this.current);
        successorLength = successorNodes.size();




    }

    public Node NextNeighbor(Node m) {

        for (Node n : this.getSuccessors(m)) {
            if (isSettled(n)) continue;

            int shortDist = getShortestDistance(m) + this.getDistance(m, n);
            if (shortDist < getShortestDistance(n)) {
                setShortestDistance(n, shortDist);
                setPredecessor(n, m);
            }
        }

        return unsettled.poll();
    }

    private int getShortestDistance(Node n) {
        Integer d = shortestDistances.get(n);
        return (d == null) ? HIGH_VALUE : d;
    }

    private boolean isSettled(Node n) {
        return settled.contains(n);
    }

    private void setShortestDistance(Node n, int dist) {
        unsettled.remove(n);
        shortestDistances.put(n, dist);
        unsettled.add(n);
    }

    public Node getPredecessor(Node n) {
        return predecessors.get(n);
    }

    private void setPredecessor(Node n, Node m) {
        predecessors.put(n, m);
    }

    private void initialize(Node start) {
        nodes.clear();
        unsettled.clear();
        settled.clear();
        shortestDistances.clear();
        predecessors.clear();
        setShortestDistance(start, 0);
        unsettled.poll();
    }

    public void drawSettled() {
        for (Node n : settled) {
            parent.fill(parent.color(0, 0, 0));
            parent.strokeWeight(1);
            parent.ellipse(n.position.x, n.position.y, 30, 30);
        }
    }

    public void drawUnsettled() {
        for (Node n : unsettled) {
            parent.fill(parent.color(255, 100, 0));
            parent.strokeWeight(1);
            parent.ellipse(n.position.x, n.position.y, 30, 30);
        }
    }

    public void drawRoute(){
        for (int i = 0; i < nodes.size() - 1; i++){
            parent.stroke(message.color);
            parent.strokeWeight(2);
            parent.line(nodes.get(i).getX(), nodes.get(i).getY(), nodes.get(i+1).getX(), nodes.get(i+1).getY());
        }
    }

    public void clearDraw(){
        settled.clear();
        nodes.clear();
    }
}
