import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by qzmp on 2015-06-01.
 */
public class Graph {
    
    int[][] adjacencyMatrix;
    boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public Graph(boolean directed, int numberOfEdges) {
        this.directed = directed;
        createMatrix(numberOfEdges);
    }

    public void generateRandom(int numberOfEdges, int numberOfVertices, int maxValue) {

        createMatrix(numberOfEdges);

        Random rand = new Random();

        int startEdge;
        int endEdge;
        int vertexValue;

        for(int i = 0; i < numberOfVertices; i++) {
            do {
                startEdge = rand.nextInt(numberOfEdges);
                endEdge = rand.nextInt(numberOfEdges);
            }while(startEdge == endEdge && adjacencyMatrix[startEdge][endEdge] == 0);

            vertexValue = rand.nextInt(maxValue) + 1;
            System.out.println(i);

            adjacencyMatrix[startEdge][endEdge] = vertexValue;
            if(!directed) {
                adjacencyMatrix[endEdge][startEdge] = vertexValue;
            }
        }
    }

    public void addVertex(int startEdge, int endEdge, int value) {
        adjacencyMatrix[startEdge][endEdge] = value;
        if(!directed) {
            adjacencyMatrix[endEdge][startEdge] = value;
        }
    }

    private void createMatrix(int size) {

        adjacencyMatrix = new int[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }
    }

    public int[] getVertices(int edge) {
        return adjacencyMatrix[edge];
    }

    public int getSize() {
        return adjacencyMatrix.length;
    }





}
