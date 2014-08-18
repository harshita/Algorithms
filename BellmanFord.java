/**
 * User: Harshita Karande
 */

/**
 * This algorithm allows negative weight edges in a graph and
 * finds the shortest path from a source vertex to all other vertices.
 * If the path from the source contains a negative weight cycle, it returns false
 * else returns true. This algorithm finds the shortest path to each vertex in the graph from the source vertex
 * The algorithm computes the shortest path from source in O(VE) time.
 */

import java.util.ArrayList;
class Edge {
    private int startVertex;
    private int endVertex;
    private int edgeCost;
    public Edge(int startVertex, int endVertex, int edgeCost) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.edgeCost = edgeCost;
    }
    public int getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(int startVertex) {
        this.startVertex = startVertex;
    }

    public int getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(int endVertex) {
        this.endVertex = endVertex;
    }

    public int getEdgeCost() {
        return edgeCost;
    }

    public void setEdgeCost(int edgeCost) {
        this.edgeCost = edgeCost;
    }

}
public class BellmanFord {
    int noedge = Integer.MIN_VALUE;
    int infinity = Integer.MAX_VALUE;
    int[][] graph = new int[][]{
        {noedge,-1,4,noedge, noedge, noedge},
        {noedge, noedge, 3, 2, 2},
        {noedge, noedge, noedge, noedge, noedge},
        {noedge, 1, 5, noedge, noedge},
        {noedge, noedge, noedge, -3, noedge}

    };
    int[] relaxedEdgeWeights = new int[5];
    int[] predecessors = new int[5];
    public static void main(String args[]) {
        BellmanFord shortestPath =  new BellmanFord();
        int source = 0;
        if(shortestPath.bellman_ford(source)) {
            System.out.println("Shortest path costs have been calculated");
            for (int i = 0; i < 5; i++) {
                System.out.println("Cost to reach vertex " + i + ": "+shortestPath.relaxedEdgeWeights[i]);
                shortestPath.print_PathFromSourceToVertex(source, i);
                System.out.println(i);
            }
        }
        else {
            throw new IllegalArgumentException("The shortest path in the graph contains negative egde weight cycle");
        }


    }

    public void print_PathFromSourceToVertex(int source, int vertex) {
         if (vertex == source) {
             System.out.println(source);
             return;
         }
         else {
             print_PathFromSourceToVertex(source, predecessors[vertex]);
             if (predecessors[vertex] != source)
                System.out.println(predecessors[vertex]);
         }
    }


    public boolean bellman_ford(int source) {
        ArrayList<Edge> edges = new ArrayList<Edge>();

        //Initialize distances of vertices.
        //Source initialized to 0, all others initialized to infinity
        for (int v=0; v < 5; v++) {
           relaxedEdgeWeights[v] = v == source? 0 : infinity;

        }
        for (int v = 0; v < graph.length; v++) {
            for (int e = 0; e < graph.length; e++) {
                if (graph[v][e] != noedge)
                 edges.add(new Edge(v, e, graph[v][e]));
            }
        }

        for (int v = 0; v < graph.length - 1; v++) {
           for (Edge edge: edges) {
               relax(edge);
           }

        }

        for (Edge edge: edges) {
            if (relaxedEdgeWeights[edge.getEndVertex()] > relaxedEdgeWeights[edge.getStartVertex()] + edge.getEdgeCost())
                 return false;
            return true;
        }
        throw new IllegalArgumentException("Something went wrong in this program. Check if the graph is configured properly");
    }

    public void relax(Edge uv) {
        if (relaxedEdgeWeights[uv.getEndVertex()] > relaxedEdgeWeights[uv.getStartVertex()] + uv.getEdgeCost()) {
            //reset relaxed edge weights to the new minimum value and also set the predecessor for backtracking
            relaxedEdgeWeights[uv.getEndVertex()] = relaxedEdgeWeights[uv.getStartVertex()] + uv.getEdgeCost();
            predecessors[uv.getEndVertex()] = uv.getStartVertex();
        }
    }

}
