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
    private List<Node> nodes = new ArrayList<Node>();
    private List<Node> successorNodes = new ArrayList<Node>();

    private List<Node> settled = new ArrayList<Node>();
    private List<Node> unsettled = new ArrayList<Node>();

    private int successorLength;
    private int currentSuccessor;

    Shortest route = new Shortest(this);

    Node current, start, end;

    public DrawDijkstra(PApplet p, Message m) {
        super(p, m);

        currentNode = 0;
        nodeCount = 0;


    }

    public boolean travel() {
        //boolean test = false;

        //System.out.println(successorLength);
        System.out.println(current.name);

        if (!this.current.name.equals(this.end.name)) {

            //drawSettled();
            //drawUnsettled();


            if (currentSuccessor < successorLength) {
                // send message to each successor
                message.setStart(this.current.position);
                message.setEnd(successorNodes.get(currentSuccessor).position);
                message.display();
                drive();
            }
            if (currentSuccessor == successorLength) {
                //get the next to check
                //set the current node to the node with the shortest distance
                this.current = NextNeighbor(this.current);
                successorNodes = getSuccessors(this.current);
                successorLength = successorNodes.size();
                message.setPosition(current.position);
                //set the currentSuccessor to 0
                currentSuccessor = 0;

            }

            return true;
        }else return false;


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
        route.execute(s, e);
        this.current = s;
        this.start = s;
        this.end = e;

        this.currentSuccessor = 0;
        successorNodes = getSuccessors(this.current);
        successorLength = successorNodes.size();


        /*
        route.execute(start, end);

        for (Node n = end; n != null; n = route.getPredecessor(n)){
            Node m = route.getPredecessor(n);
            if (m.equals(null)) break;
            travel(n, m);

        }
        */
    }

    public Node NextNeighbor(Node m) {
        Node next = null;
        int smallestWeight = Integer.MAX_VALUE;
        for (Node n : this.getSuccessors(m)) {
            if (m.getEdge(n).getWeight() < smallestWeight) {
                smallestWeight = m.getEdge(n).getWeight();
            }
        }
        for (Node n : this.getSuccessors(m)) {
            if (smallestWeight == m.getEdge(n).getWeight()){
                next = n;
                break;
            }
        }

        return next;
    }

    public void drawSettled() {
        for (Node n : settled) {
            parent.fill(parent.color(255, 255, 0));
            parent.strokeWeight(1);
            parent.ellipse(n.position.x, n.position.y, 30, 30);
        }
    }

    public void drawUnsettled() {
        for (Node n : unsettled) {
            parent.fill(parent.color(255, 255, 0));
            parent.strokeWeight(1);
            parent.ellipse(n.position.x, n.position.y, 30, 30);
        }
    }

    public class Shortest {
        public static final int HIGH_VALUE = Integer.MAX_VALUE;
        private static final int INITIAL_VALUE = 8;

        private final DrawDijkstra map;
        private final Comparator<Node> shortestDistanceComparator = new Comparator<Node>() {
            public int compare(Node left, Node right) {
                int result = getShortestDistance(left) - getShortestDistance(right);
                return (result == 0) ? left.compareTo(right) : result;
            }
        };
        private final PriorityQueue<Node> unsettledNodes = new PriorityQueue<Node>(INITIAL_VALUE, shortestDistanceComparator);
        private final Set<Node> settledNodes = new HashSet<Node>();
        private final Map<Node, Integer> shortestDistances = new HashMap<Node, Integer>();
        private final Map<Node, Node> predecessors = new HashMap<Node, Node>();

        PApplet parent;

        public Shortest(DrawDijkstra g) {
            this.map = g;
            this.parent = map.parent;
        }

        private void initialize(Node start) {
            unsettledNodes.clear();
            settledNodes.clear();

            shortestDistances.clear();
            predecessors.clear();

            setShortestDistance(start, 0);
            unsettledNodes.add(start);
        }

        public void execute(Node start, Node end) {
            initialize(start);


            Node n;

            while ((n = unsettledNodes.poll()) != null) {
                if (n == end) break;
                settledNodes.add(n);
                relaxNeighbors(n);

            }
        }

        public void relaxNeighbors(Node m) {
            for (Node n : map.getSuccessors(m)) {
                if (isSettled(n)) continue;
                //map.travel(m, n);
                int shortDist = getShortestDistance(m) + map.getDistance(m, n);
                if (shortDist < getShortestDistance(n)) {
                    setShortestDistance(n, shortDist);
                    setPredecessor(n, m);
                    current = n;
                }
            }
        }

        private boolean isSettled(Node n) {
            return settledNodes.contains(n);
        }

        private int getShortestDistance(Node n) {
            Integer d = shortestDistances.get(n);
            return (d == null) ? HIGH_VALUE : d;
        }

        private void setShortestDistance(Node n, int dist) {
            unsettledNodes.remove(n);
            shortestDistances.put(n, dist);
            unsettledNodes.add(n);
        }

        public Node getPredecessor(Node n) {
            return predecessors.get(n);
        }

        private void setPredecessor(Node n, Node m) {
            predecessors.put(n, m);
        }
    }
}
