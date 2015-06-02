import java.util.*;

/**
 * Created by qzmp on 2015-06-01.
 */
public class Dijkstra {

    public static Edge[] findShortestPaths(Graph graph, int start) {
        Edge[] edges = convertGraph(graph);

        PriorityQueue<Edge> visitedEdges = new PriorityQueue<>();
        visitedEdges.add(edges[start]);
        edges[start].setDistance(0);

        Edge processedEdge;
        Map<Edge, Integer> processedVertices;
        int newDistance;

        while(!visitedEdges.isEmpty()) {
            processedEdge = visitedEdges.poll();

            processedVertices = processedEdge.getVertices();
            for(Edge e : processedVertices.keySet()) {

                newDistance = processedEdge.getDistance() + processedVertices.get(e);

                if(newDistance < e.getDistance()) {
                    e.setDistance(newDistance);
                    e.setPrevious(processedEdge);
                    visitedEdges.add(e);
                }
            }
            visitedEdges.remove(processedEdge);
        }

        return edges;
    }

    public static Edge[] findShortestPathsParallel(Graph graph, int start) {
        Edge[] edges = convertGraph(graph);

        edges[start].setDistance(0);
        ParallelDijkstra newThread = new ParallelDijkstra(edges[start]);
        newThread.start();

        return edges;
    }


    private static int[] getArrayOfInfinities(int size) {
        int[] result = new int[size];
        for(int i = 0; i < size; i++) {
            result[i] = Integer.MAX_VALUE;
        }

        return result;
    }

    public static Edge[] convertGraph(Graph graph) {
        Edge[] edges = new Edge[graph.getSize()];

        for(int i = 0; i < edges.length; i++) {
            edges[i] = new Edge(i);
        }

        Map<Edge, Integer> convertedVertices;
        int[] vertices;
        for(int i = 0; i < edges.length; i++) {
            convertedVertices = new HashMap<>();
            vertices = graph.getVertices(i);
            for(int j = 0; j < edges.length; j++) {
                if(vertices[j] != 0) {
                    convertedVertices.put(edges[j], vertices[j]);
                }
            }
            edges[i].addVertices(convertedVertices);
        }

        return edges;
    }

}
