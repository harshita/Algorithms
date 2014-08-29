/**
 * @author: Harshita Karande
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
class MSTEdge {
    int startVertex;
    int endVertex;
    int weight;

    public MSTEdge(int startVertex, int endVertex, int weight)  {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }
}

class ConnectedComponent {
    Set<Integer> connectedVertices;
    ConnectedComponent(int vertex) {
        this.connectedVertices = new HashSet<Integer>();
        this.connectedVertices.add(vertex);
    }


    public void union(ConnectedComponent c) {
        this.connectedVertices.addAll(c.connectedVertices);
    }
}

public class KruskalMST {
    List<ConnectedComponent> connectedComponents;
    List<MSTEdge> edges;
    Set<Integer> vertices;
    List<MSTEdge> selectedEdgesInMST = new ArrayList<MSTEdge>();

    public KruskalMST(List<MSTEdge> edges, Set<Integer> vertices) {
        this.edges = edges;
        this.vertices = vertices;
        this.connectedComponents = new ArrayList<ConnectedComponent>();
    }


    public void makeSet(int vertex) {
        ConnectedComponent cc = new ConnectedComponent(vertex);
        connectedComponents.add(cc);
    }

    public void kruskal() {
        for (Integer u : vertices) {
             makeSet(u);
        }

        Collections.sort(edges, new Comparator<MSTEdge>() {
            @Override
            public int compare(MSTEdge mstEdge, MSTEdge mstEdge2) {
                return mstEdge.weight - mstEdge2.weight;
            }
        });

        for (MSTEdge edge: edges) {
            ConnectedComponent startVertexSet = findSet(edge.startVertex);
            ConnectedComponent endVertexSet = findSet(edge.endVertex);

            if (startVertexSet == endVertexSet)
                continue;
            else {
                startVertexSet.union(endVertexSet);
                this.connectedComponents.remove(endVertexSet);
                this.selectedEdgesInMST.add(edge);
            }
        }
    }

    public ConnectedComponent findSet(int vertex) {
        ConnectedComponent vertexSet = null;

        for (ConnectedComponent c : connectedComponents) {
            if (c.connectedVertices.contains(vertex) && vertexSet == null) {
                vertexSet = c;
            }

            if (vertexSet != null)
                break;

        }
        return vertexSet;

    }

    public static void main(String args[]) {
        List<MSTEdge> inputegdes = new ArrayList<MSTEdge>();
        Set<Integer> inputVertices = new HashSet<Integer>();

        inputegdes.add(new MSTEdge(1,2,10));
        inputegdes.add(new MSTEdge(1,3,12));
        inputegdes.add(new MSTEdge(2,3,9));
        inputegdes.add(new MSTEdge(2,4,8));
        inputegdes.add(new MSTEdge(3,5,3));
        inputegdes.add(new MSTEdge(3,6,1));
        inputegdes.add(new MSTEdge(4,2,8));
        inputegdes.add(new MSTEdge(4,5,7));
        inputegdes.add(new MSTEdge(4,7,8));
        inputegdes.add(new MSTEdge(4,8,5));
        inputegdes.add(new MSTEdge(5,6,3));
        inputegdes.add(new MSTEdge(6,8,6));
        inputegdes.add(new MSTEdge(7,8,9));
        inputegdes.add(new MSTEdge(7,9,2));
        inputegdes.add(new MSTEdge(8,9,11));

        for (int i = 1; i <= 9; i++)
            inputVertices.add(i);


        KruskalMST kruskalMST = new KruskalMST(inputegdes, inputVertices);
        kruskalMST.kruskal();
        System.out.println("The MST constructed with Kruskal algorithm will contain the following edges:");
        for (MSTEdge selectedge: kruskalMST.selectedEdgesInMST) {
            System.out.println(selectedge.startVertex+ "-" + selectedge.endVertex);
        }
    }


}
