/*
拓扑排序算法 适用范围：要求有向图，且有入度为0的节点，且没有环
【思路】
寻找入度为 0 的节点，将其输出并修改和它相邻节点的入度，重复寻找入度为 0 的节点，直至图为空
*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class  TopologySort {
    // 有向且无环
    public static List<Node> sortedTopology(Graph graph) {
        List<Node> result = new ArrayList<>();
        // 统计节点入度
        HashMap<Graph.Node, Integer> inMap = new HashMap<>();
        // 入度为 0 节点队列
        Queue<Graph.Node> zeroInqueue = new LinkedList<>();
        for (Graph.Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) {
                // 找到入度为 0 的节点就入队列
                zeroInqueue.add(node);
            }
        }
        while (!zeroInqueue.empty()) {
            Node cur = zeroInqueue.poll();
            result.add(cur);
            for (Graph.Node next : cur.nexts) {
                // 修改邻接点的入度
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    // 找到入度为 0 的节点就入队列
                    zeroInqueue.add(node);
                }
            }
        }
        return result;
    }
}