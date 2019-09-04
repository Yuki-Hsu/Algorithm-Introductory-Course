/*
图的定义：包括节点和边
*/
import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
// 图
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edges;
    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
    // 节点
    public static class Node {
        public int value;
        public int in;
        public int out;
        public ArrayList<Node> nexts;
        public ArrayList<Edge> edges;
        public Node(int value) {
            this.value = value;
            in = 0;
            out = 0;
            nexts = new ArrayList<>();
            edges = new ArrayList<>();
        }
    }
    // 边
    public static class Edge {
        public int weight;
        public Node from;
        public Node to;
        public Edge(int weight, Node from, Node to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }
}