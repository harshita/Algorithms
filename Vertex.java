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
    private int distance;
    private String color;
    private int discoveryTime;
    private int finishTime;


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

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getDiscoveryTime() {
        return discoveryTime;
    }

    public void setDiscoveryTime(int discoveryTime) {
        this.discoveryTime = discoveryTime;
    }

}
