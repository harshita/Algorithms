/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class TopologicalSort {
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

        TopologicalSort ts = new TopologicalSort();
        ts.TopologicalSort(graph);

        for(String key : graph.keySet()) {
            System.out.println(key + " " + graph.get(key).getTopNum());

        }

    }

    public boolean TopologicalSort(HashMap<String, Vertex> graph) {
        //compute the in degree of all the vertices
        computeInDegree(graph);

        Queue<Vertex> queue = new LinkedList<Vertex>();

        //store the vertexes of indegree 0 in the queue to begin with
        for (String key : graph.keySet()) {
            Vertex v = graph.get(key);
            if (v.getInDegree() == 0)
                queue.offer(v);
        }

        int counter = 0;


        //keep adding/removing remaining vertices in the queue
        while (!queue.isEmpty()) {
            Vertex q = queue.poll();
            counter++;
            q.setTopNum(counter);
            for (Vertex v : q.getAdjacencyList()) {
                v.setInDegree(v.getInDegree() - 1);
                if (v.getInDegree() == 0)
                    queue.offer(v);
            }
        }

        if (counter != graph.size())
            return false;
        return true;




    }

    public void computeInDegree(HashMap<String, Vertex> graph) {
        for (String key : graph.keySet()) {
            Vertex v = graph.get(key);
            List<Vertex> adjacencyList = v.getAdjacencyList();
            for(Vertex ver : adjacencyList) {
                ver.setInDegree(ver.getInDegree() + 1);
            }
        }

    }


}
