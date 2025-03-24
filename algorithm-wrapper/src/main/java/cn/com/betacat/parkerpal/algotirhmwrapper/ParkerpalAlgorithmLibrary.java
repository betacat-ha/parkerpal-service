package cn.com.betacat.parkerpal.algotirhmwrapper;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import java.util.List;

public interface ParkerpalAlgorithmLibrary extends Library{

    // 第一个参数为 dll 的名字，在 Linux 上是 so
    ParkerpalAlgorithmLibrary INSTANCE = Native.load("parkerpal_algorithm", ParkerpalAlgorithmLibrary.class);

    interface Graph extends Library {
        Pointer createGraph(List<int[]> edges);
        void addEdge(Pointer graph, int from, int to, double weight);
        double getEdgeWeight(Pointer graph, int from, int to);
        List<int[]> getNeighbors(Pointer graph, int node);
        List<Integer> getAllNodes(Pointer graph);
        int getNodeCount(Pointer graph);
        int getEdgeCount(Pointer graph);
        void printGraph(Pointer graph);
    }

    interface AStar extends Library {
        Pointer createAStar(Pointer graph, Callback heuristic);
        List<Integer> findShortestPath(Pointer aStar, int start, int goal);
    }

    interface Dijkstra extends Library {
        Pointer createDijkstra(Pointer graph);
        List<Integer> findShortestPath(Pointer dijkstra, int start, int goal);
    }

    interface Trilateration extends Library {
        double calculateDistanceFromRssi(double rssi);
        void updateDistances(Pointer aps);
        double[] simpleTrilateration(Pointer aps);
        double[] leastSquaresTrilateration(Pointer aps);
    }

    // 获取实例
    default Graph getGraph() {
        return (Graph) this;
    }

    default AStar getAStar() {
        return (AStar) this;
    }

    default Dijkstra getDijkstra() {
        return (Dijkstra) this;
    }

    default Trilateration getTrilateration() {
        return (Trilateration) this;
    }

}
