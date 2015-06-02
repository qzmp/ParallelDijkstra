import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by qzmp on 2015-06-02.
 */
public class DijkstraThread extends Thread{

    private Edge start;
    private boolean forward;
    private Edge metEdge;

    PriorityQueue<Edge> visitedEdges;

    public DijkstraThread(Edge start, boolean forward) {
        this.start = start;
        this.forward = forward;
    }

    @Override
    public void run() {
        visitedEdges = new PriorityQueue<>();
        visitedEdges.add(start);

        Edge processedEdge;
        Map<Edge, Integer> processedVertices;
        int newDistance;

        boolean met = false;

        while(!visitedEdges.isEmpty() && !met) {
            processedEdge = visitedEdges.poll();

            processedVertices = processedEdge.getVertices();
            for(Edge e : processedVertices.keySet()) {

                newDistance = processedEdge.getDistance() + processedVertices.get(e);

                if(newDistance < e.getDistance()) {
                    met = visitEdge(e, newDistance);
                }
            }
            visitedEdges.remove(processedEdge);
            if(visitedEdges.isEmpty()) {
                System.out.println("not met");
            }
        }
    }

    private boolean visitEdge(Edge e, int distance) {

        boolean met = false;
        if(forward) {
            e.setVisitedByForward(true);
            if(e.isVisitedByBackward()) {
                met = true;
                metEdge = e;
                e.setMeetDistance(distance);
            }
            else {
                e.setDistance(distance);
                visitedEdges.add(e);
            }
        } else {
            e.setVisitedByBackward(true);
            if (e.isVisitedByForward()) {
                met = true;
                metEdge = e;
                e.setMeetDistance(distance);
            } else {
                e.setDistance(distance);
                visitedEdges.add(e);
            }
        }

        return met;
    }


    public Edge getMetEdge() {
        return metEdge;
    }
}
