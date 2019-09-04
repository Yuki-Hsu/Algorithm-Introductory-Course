/*
图的存储方式
1）邻接表
2）邻接矩阵
如何表达图？生成图？
*/
public class GraphGenerator {
    // 根据输入的矩阵信息生成一个图并返回
    public static Graph createGraph(Integer[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            // 解析数组每一行
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer weight = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Graph.Node(from));
            }
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Graph.Node(to));
            }
            Graph.Node fromNode = graph.nodes.get(from);
            Graph.Node toNode = graph.nodes.get(to);
            Graph.Edge newEdge = new Graph.Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;
    }
}