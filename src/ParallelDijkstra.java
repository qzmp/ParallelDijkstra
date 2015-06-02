/**
 * Created by qzmp on 2015-06-02.
 */
public class ParallelDijkstra {


    public Edge[] findShortestPathsParallel(Graph graph, int start, int end) {
        Edge[] edges = Dijkstra.convertGraph(graph);

        edges[start].setDistance(0);
        edges[end].setDistance(0);

        DijkstraThread forward = new DijkstraThread(edges[start], true);
        DijkstraThread backward = new DijkstraThread(edges[end], false);

        forward.start();
        backward.start();

        forward.getMetEdge();
        backward.getMetEdge();

        System.out.println("returning");
        return edges;
    }

}
