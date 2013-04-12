package UI;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * @ author: Mark Hinshaw
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 *
 * This class is a message that travels from location to location.  This method will contain all the information needed for display within the message.
 * All calculations are done locally and can be called for use in other classes.
 *
 */

public class ShortestPath {

    public static final int INFINITY = Integer.MAX_VALUE;
    private static final int INITIAL_VALUE = 24;

    private final Graph map;

    private final Comparator<Node> shortestDistanceComparator = new Comparator<Node>()
    {
        public int compare(Node left, Node right)
        {
            int result = getShortestDistance(left) - getShortestDistance(right);

            return result; //(result == 0) ? left.compareTo(right) : result;
        }
    };

    private final PriorityQueue<Node> unsettledNodes = new PriorityQueue<Node>(INITIAL_VALUE, shortestDistanceComparator);

    private final Set<Node> settledNodes = new HashSet<Node>();

    private final Map<Node, Integer> shortestDistances = new HashMap<Node, Integer>();

    private final Map<Node, Node> predecessors = new HashMap<Node, Node>();

    /*
    Constructor
     */
    public ShortestPath(Graph graph){
        this.map = graph;
    }

    private void initialize(Node start){
        unsettledNodes.clear();
        settledNodes.clear();

        shortestDistances.clear();
        predecessors.clear();

        setShortestDistance(start, 0);
        unsettledNodes.add(start);
    }

    public void execute(Node start, Node end){
        initialize(start);

        Node n;

        while((n = unsettledNodes.poll()) != null){
            assert !isSettled(n);
            if (n == end) break;

            settledNodes.add(n);

            relaxNeighbors(n);
        }
    }

    public void relaxNeighbors(Node n){
        for (Node m : map.getSuccessors(n)){
            if (isSettled(m)) continue;

            int shortDist = getShortestDistance(n) + map.getDistance(n, m);

            if (shortDist < getShortestDistance(m)){
                setShortestDistance(m, shortDist);
                setPredecessor(m, n);
            }
        }
    }

    private boolean isSettled(Node n){
        return settledNodes.contains(n);
    }

    private int getShortestDistance(Node n){
        Integer d = shortestDistances.get(n);
        return (d == null) ? INFINITY : d;
    }
    private void setShortestDistance(Node n, int dist){
        unsettledNodes.remove(n);
        shortestDistances.put(n, dist);
        unsettledNodes.add(n);
    }

    private Node getPredecessor(Node n){
        return predecessors.get(n);
    }

    private void setPredecessor(Node n, Node m){
        predecessors.put(n, m);
    }
}
