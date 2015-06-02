import java.util.HashMap;
import java.util.Map;

/**
 * Created by qzmp on 2015-06-01.
 */
public class Edge implements Comparable<Edge>{
    private int number;
    private int distance;
    private Edge previous;
    private Map<Edge, Integer> vertices;

    private boolean availableForThreads;

    private boolean changed;

    public Edge(int number) {
        this.number = number;
        this.distance = Integer.MAX_VALUE;
        this.vertices = new HashMap<>();
    }

    public void addVertices(Map<Edge, Integer> vertices) {
        this.vertices = vertices;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(distance, o.getDistance());
    }

    public synchronized boolean offerDistance(int distance, Edge edge) {
        if(this.distance > distance) {
            this.distance = distance;
            this.previous = edge;
            changed = true;
        }
        return this.distance > distance;
    }

    public synchronized void setChange(boolean change) {
        this.changed = change;
    }

    public synchronized boolean hasChanged() {
        return changed;
    }

    //Getters and Setters

    public synchronized int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Edge getPrevious() {
        return previous;
    }

    public void setPrevious(Edge previous) {
        this.previous = previous;
    }

    public int getNumber() {
        return number;
    }

    public Map<Edge, Integer> getVertices() {
        return vertices;
    }
}
