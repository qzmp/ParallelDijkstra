/**
 * Created by qzmp on 2015-06-01.
 */
public class Presentation {
    public static Graph getSimpleGraph() {
        Graph graph = new Graph(false, 7);
        graph.addVertex(0, 2, 1);
        graph.addVertex(0, 3, 2);
        graph.addVertex(1, 2, 2);
        graph.addVertex(2, 3, 1);
        graph.addVertex(2, 4, 3);
        graph.addVertex(1, 5, 3);
        graph.addVertex(4, 5, 2);
        graph.addVertex(5, 6, 1);
        graph.addVertex(3, 6, 1);

        return graph;
    }

    public static void main(String[] args) {
        Graph graph = getSimpleGraph();
        Edge[] edges = Dijkstra.findShortestPaths(graph, 0);

        graph.generateRandom(7, 9, 20);

        edges = Dijkstra.findShortestPaths(graph, 0);

    }
}
