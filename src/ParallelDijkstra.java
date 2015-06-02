import java.util.Map;

/**
 * Created by qzmp on 2015-06-01.
 */
public class ParallelDijkstra implements Runnable {

    private Edge startEdge;

    private Thread t;

    public ParallelDijkstra(Edge start) {
        this.t = new Thread(this);
        this.startEdge = start;
    }

    @Override
    public void run() {

        int newDistance;
        Map<Edge, Integer> processedVertices = startEdge.getVertices();

        while(startEdge.hasChanged()) {
            startEdge.setChange(false);

            for(Edge e : processedVertices.keySet()) {
                newDistance = startEdge.getDistance() + processedVertices.get(e);

                if(e.offerDistance(newDistance, startEdge)) {

                    ParallelDijkstra newThread = new ParallelDijkstra(e);
                    newThread.start();
                }
            }
        }
    }

    public void start() {
        t.start();
    }
}
