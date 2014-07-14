/**
 * Created with IntelliJ IDEA.
 * User: Harshita Karande
 */
import java.util.List;
public class Vertex {
    private int inDegree;
    private List<Vertex> adjacencyList;
    private String vertexName;
    private int topNum;

    public Vertex(String vertexName) {
        this.vertexName = vertexName;
    }

    public void setInDegree(int inDegree) {
        this.inDegree = inDegree;
    }

    public int getInDegree() {
        return this.inDegree;
    }

    public String getVertexName() {
        return this.vertexName;
    }

    public int getTopNum() {
        return this.topNum;
    }

    public void setTopNum(int topNum) {
        this.topNum = topNum;
    }

    public void setAdjacencyList(List<Vertex> adjacencyList) {
        this.adjacencyList = adjacencyList;
    }

    public List<Vertex> getAdjacencyList() {
        return this.adjacencyList;
    }

}
