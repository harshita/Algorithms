/**
 * User: Harshita Karande
 */

/**
 * This algorithm finds the shortest path in a DAG in Theta(V+E) time
 * by first topologically ordering the vertices and then relaxing the
 * edges out of each topologically sorted vertex in order
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
public class ShortestPathInDAG {
    static int noEdge = Integer.MIN_VALUE;
    static int infinity = Integer.MAX_VALUE;
    static final int NumberOfVertices = 5;

	private HashMap<Integer, ArrayList<Integer>> adjacency = new HashMap<Integer, ArrayList<Integer>>();
	private PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
	private ArrayList<Integer> topologicalList = new ArrayList<Integer>();
	private int[] vertexIndegree = new int[NumberOfVertices];
	private int[] relaxedEdgeWeights = new int[] {0, infinity, infinity, infinity, infinity};
	private int[] predecessor = new int[NumberOfVertices];
	private int[][] graph;
	
	
	
	public ShortestPathInDAG(int[][] graph) {
		this.graph = Arrays.copyOf(graph, graph.length);
	}
	
	public static void main(String args[]) {
		ShortestPathInDAG shortestPath = new ShortestPathInDAG(new int[][] {{noEdge, 2, 6, noEdge, noEdge},
																		{noEdge, noEdge, 7, 4, 2},
																		{noEdge, noEdge, noEdge, -1, 1},
																		{noEdge, noEdge, noEdge, noEdge, -2},
																		{noEdge, noEdge, noEdge, noEdge, noEdge}});
		int source = 0;
		shortestPath.findShortestPathInDAG(source);
		shortestPath.printResults(source);
		
	}
	
	public void printResults(int source) {
		for (int i : topologicalList) {
            System.out.println("Shortest path for "+ i + " has cost "+ relaxedEdgeWeights[i]);
            printTree(i, source);
		}

	}

    public void printTree(int i, int source) {
        if (i != source) {
            printTree(predecessor[i], source);
        }
        System.out.println(i);
    }

	public void findShortestPathInDAG(int s) {	
		for (int i  = 0; i < graph.length; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] != noEdge) {
					if (adjacency.containsKey(i)) {
						ArrayList<Integer> temp = adjacency.get(i);
						temp.add(j);
					}
					else {
						ArrayList<Integer> temp = new ArrayList<Integer>();
						temp.add(j);
						adjacency.put(i, temp);
					}
					
					vertexIndegree[j]++;
				}
				
			}
		}

		priorityQueue.offer(s);
		while(! priorityQueue.isEmpty()) {
			int t = priorityQueue.poll();
			topologicalList.add(t);
            if (adjacency.containsKey(t)) {
			    ArrayList<Integer> temp =  adjacency.get(t);
                for (int k : temp) {
                        vertexIndegree[k]--;
                        if(vertexIndegree[k] == 0)
                            priorityQueue.offer(k);

                }
            }
		}

        //Relax the edges out of each topologically sorted vertex in order
		for(Integer u : topologicalList) {
            if (adjacency.containsKey(u)) {
                ArrayList<Integer> temp =  adjacency.get(u);
                for (int v : temp) {
                    relax(u,v);
                }
            }
		}
	}

    //relax edge (u,v)
	public void relax(int source, int dest) {
		if (relaxedEdgeWeights[dest] > relaxedEdgeWeights[source] + this.graph[source][dest]) {
			relaxedEdgeWeights[dest] = relaxedEdgeWeights[source] + this.graph[source][dest];
			predecessor[dest] = source;
		}
	
	}
}