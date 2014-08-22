
/**
 * @author : Harshita Karande
 * Floyd Warshall's algorithm uses dynamic programming technique to solve all-pairs
 * shortest paths problem on a directed graph. The algorithm runs in O(V3) time
 */
import java.util.ArrayList;
import java.util.Arrays;

public class FloydWarshall {
    ArrayList<int[][]> D = new ArrayList<int[][]>();
    ArrayList<int[][]> Pred = new ArrayList<int[][]>();
    int[][] graphWeights;
    private static final int noEdge = Integer.MAX_VALUE;

    public FloydWarshall(int[][] graphWeights) {
        this.graphWeights = Arrays.copyOf(graphWeights, graphWeights.length);
    }

    public void computeDistancesUsingFW() {
        int N = graphWeights.length;
        //Adding D(0)
        D.add(graphWeights);
        int[][] predecessors = new int[graphWeights.length][graphWeights.length];
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (i == j)
                    predecessors[i][j] = noEdge;
                else if(graphWeights[i][j] != noEdge)
                    predecessors[i][j] = i;
            }
        }
        Pred.add(predecessors);
        for (int k = 1; k < N; k++) {
            int[][] newGraphWeightsForK = new int[graphWeights.length][graphWeights.length];
            int[][] newPredecessorForK = new int[graphWeights.length][graphWeights.length];
            for (int i = 1; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if (i==j) {
                        newGraphWeightsForK[i][j] = D.get(k-1)[i][j];
                        newPredecessorForK[i][j] =  Pred.get(k-1)[i][j];

                    }
                    else if (D.get(k-1)[i][j] <= ((D.get(k-1)[i][k] != noEdge && D.get(k-1)[k][j] != noEdge) ? D.get(k-1)[i][k] + D.get(k-1)[k][j] : noEdge)) {
                        newGraphWeightsForK[i][j] = D.get(k-1)[i][j];
                        newPredecessorForK[i][j] = Pred.get(k-1)[i][j];
                    }
                    else {
                        if(D.get(k-1)[i][k] == noEdge || D.get(k-1)[k][j]== noEdge) {
                            newGraphWeightsForK[i][j] = noEdge;
                            newPredecessorForK[i][j] = noEdge;
                        }
                        else {
                            newGraphWeightsForK[i][j] = D.get(k-1)[i][k] + D.get(k-1)[k][j];
                            newPredecessorForK[i][j] = Pred.get(k-1)[k][j];
                        }
                    }
                }
            }
            D.add(newGraphWeightsForK);
            Pred.add(newPredecessorForK);
        }

    }

    public void print_Weights() {
        System.out.println("D(n) matrix");
        int[][] finalWeights = D.get(D.size()-1);
        for (int i = 0; i < finalWeights.length; i++) {
            for (int j = 0; j < finalWeights.length; j++) {
                System.out.print(finalWeights[i][j] +" ");
            }
            System.out.println();
        }

        System.out.println();

        System.out.println("Predecessor(n) matrix");
        int[][] finalPredecessor = Pred.get(Pred.size()-1);
        for (int i = 0; i < finalPredecessor.length; i++) {
            for (int j = 0; j < finalPredecessor.length; j++) {
                System.out.print(finalPredecessor[i][j] +" ");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Shortest path from u to v");
        for (int i = 1; i < finalPredecessor.length; i++) {
            for (int j = 1; j < finalPredecessor.length; j++) {
                if (i != j) {
                    System.out.println(i + "->" +j);
                    printShortestPaths(i, j);
                    System.out.println("\n");
                }

            }

        }
    }

    public void printShortestPaths(int u, int v) {
        if (u != v) {
            printShortestPaths(u, Pred.get(Pred.size()-1)[u][v]);

        }
        System.out.print(v + "\t");


    }


    public static void main(String args[]) {
        FloydWarshall fw = new FloydWarshall(new int[][] {{0, 0, 0, 0, 0, 0},
                                                          {0, 0, 3, 8, noEdge, -4},
                                                          {0, noEdge, 0, noEdge, 1, 7},
                                                          {0, noEdge, 4, 0, noEdge, noEdge},
                                                          {0, 2, noEdge, -5, 0, noEdge},
                                                          {0, noEdge, noEdge, noEdge, 6, 0}});
        fw.computeDistancesUsingFW();
        fw.print_Weights();

    }

}
