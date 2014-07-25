/**
 * User: Harshita Karande
 */
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void main (String args[]) {
        HashMap<String, Vertex> graph = new HashMap<String, Vertex>();
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");

        // set adjacency list for v1
        List<Vertex> adjacency = new ArrayList<Vertex>();
        adjacency.add(v2);
        adjacency.add(v3);
        adjacency.add(v4);
        v1.setAdjacencyList(adjacency);

        // set adjacency list for v2
        adjacency = new ArrayList<Vertex>();
        adjacency.add(v4);
        adjacency.add(v5);
        v2.setAdjacencyList(adjacency);

        // set adjacency list for v3
        adjacency = new ArrayList<Vertex>();
        adjacency.add(v6);
        v3.setAdjacencyList(adjacency);

        // set adjacency list for v4
        adjacency = new ArrayList<Vertex>();
        adjacency.add(v3);
        adjacency.add(v6);
        adjacency.add(v7);
        v4.setAdjacencyList(adjacency);

        // set adjacency list for v5
        adjacency = new ArrayList<Vertex>();
        adjacency.add(v4);
        adjacency.add(v7);
        v5.setAdjacencyList(adjacency);

        // set adjacency list for v6
        adjacency = new ArrayList<Vertex>();
        v6.setAdjacencyList(adjacency);

        // set adjacency list for v7
        adjacency = new ArrayList<Vertex>();
        adjacency.add(v6);
        v7.setAdjacencyList(adjacency);

        graph.put("v1", v1);
        graph.put("v2", v2);
        graph.put("v3", v3);
        graph.put("v4", v4);
        graph.put("v5", v5);
        graph.put("v6", v6);
        graph.put("v7", v7);

        BFS bfs = new BFS();
        bfs.BFS(graph, "v1");

        for(String key : graph.keySet()) {
            System.out.println(key + " " + graph.get(key).getDistance());

        }

    }

    //Given a input graph, computes d[v] for all v belonging to the set of the vertices such that d[v]= distance from s to v that that d[v] is the shortest from s to v.
    //Time and space complexity O(V+E)
    public void BFS(HashMap<String, Vertex> graph, String source) {
        Queue<Vertex> queue = new LinkedList<Vertex>();
        for (String vertexName : graph.keySet()) {
            Vertex v = graph.get(vertexName);
            //setting distance to max value
            v.setDistance(Integer.MAX_VALUE);
        }

        //set source distance to 0
        Vertex v = graph.get(source);
        v.setDistance(0);
        queue.offer(v);
        while(!queue.isEmpty()) {
            Vertex topVertex = queue.poll();
            for(Vertex adjVertex : topVertex.getAdjacencyList()) {
                if(adjVertex.getDistance() == Integer.MAX_VALUE) {
                    adjVertex.setDistance(topVertex.getDistance() + 1);
                    queue.offer(adjVertex);
                }
            }
        }

    }

}
