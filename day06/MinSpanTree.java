/*
最小生成树
kruskal算法 适用范围：要求无向图
prim算法 适用范围：要求无向图
【思路】
Q1：每次从权重最小的边开始考察，并加入，使其边的两端节点入集合，并且添加边时确保不会形成环路
使用并查集
Q2：从点开始考察，每加入一个点，就解锁新的边，从新的边集合选择权重最小的边并加入新节点，重复前面的步骤
*/
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
// Usage：编译时依赖文件 ./Graph.java ./GraphGenerator.java
public class MinSpanTree {
    // Q1
    public static class Kruskal {
        // 并查集
        public static class UnionFindSet {
            // （key，value）key 节点的父节点为 value 节点
            public HashMap<Graph.Node, Graph.Node> fatherMap;
            // value 表示 key 节点作为代表元素所在的集合的大小
            public HashMap<Graph.Node, Integer> sizeMap;
            public UnionFindSet() {
                fatherMap = new HashMap<Graph.Node, Graph.Node>();
                sizeMap = new HashMap<Graph.Node, Integer>();
            }
            // 创建并查集，初始化添加节点，每个节点自成一个集合，并作为集合代表元素
            public void makeSet(Collection<Graph.Node> nodes) {
                fatherMap.clear();
                sizeMap.clear();
                for (Graph.Node node : nodes) {
                    fatherMap.put(node, node);
                    sizeMap.put(node, 1);
                }
            }
            // 查找代表元素，即定位属于哪个集合
            public Graph.Node findFather(Graph.Node n) {
                Graph.Node father = fatherMap.get(n);
                if (father != n) {
                    // 递归查询，并修改沿路节点，使其父节点为当前集合的代表元素
                    father = findFather(father);
                }
                fatherMap.put(n, father);
                return father;
            }
            // 合并集合
            public void union(Graph.Node a, Graph.Node b) {
                if (a == null || b == null) {
                    return;
                }
                Graph.Node aFather = findFather(a);
                Graph.Node bFather = findFather(b);
                if (aFather != bFather) {
                    int aFsize = sizeMap.get(aFather);
                    int bFsize = sizeMap.get(bFather);
                    if (aFsize <= bFsize) {
                        // a 集合接到 b 集合上
                        fatherMap.put(aFather, bFather);
                        sizeMap.put(bFather, bFsize + aFsize);
                    } else {
                        // b 集合接到 a 集合上
                        fatherMap.put(bFather, aFather);
                        sizeMap.put(aFather, bFsize + aFsize);
                    }
                }
            }
            // 判断是否属于同一个集合（是否形成环路）
            public boolean isSameSet(Graph.Node a, Graph.Node b) {
                return findFather(a) == findFather(b);
            }
        }
        public static Set<Graph.Edge> kruskalMST(Graph graph) {
            UnionFindSet unionFind = new UnionFindSet();
            unionFind.makeSet(graph.nodes.values());
            // 使用小根堆存边集合，可以确保每次取出最小权重的边
            PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
            for (Graph.Edge edge : graph.edges) {
                priorityQueue.add(edge);
            }
            Set<Graph.Edge> result = new HashSet<>();
            while (!priorityQueue.isEmpty()) {
                Graph.Edge edge = priorityQueue.poll();
                if (!unionFind.isSameSet(edge.from, edge.to)) {
                    result.add(edge);
                    unionFind.union(edge.from, edge.to);
                }
            }
            return result;
        }
    }
    // Q2 （该实现其实为，有向图的最小生成树算法）
    public static class Prim {
        public static Set<Graph.Edge> primMST(Graph graph) {
            // 使用小根堆存边集合，可以确保每次取出最小权重的边
            PriorityQueue<Graph.Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
            Set<Graph.Node> set = new HashSet<>();
            Set<Graph.Edge> result = new HashSet<>();
            // 可能是非连通图，所以需要一层 for 循环
            for (Graph.Node node : graph.nodes.values()) {
                if (!set.contains(node)) {
                    set.add(node);
                    // 添加新的边
                    for (Graph.Edge var : node.edges) {
                        priorityQueue.add(var);
                    }
                    while (!priorityQueue.isEmpty()) {
                        Graph.Edge edge = priorityQueue.poll();
                        Graph.Node toNode = edge.to;
                        if (!set.contains(toNode)) {
                            set.add(toNode);
                            result.add(edge);
                            // 添加新的边
                            for (Graph.Edge var : toNode.edges) {
                                priorityQueue.add(var);
                            }
                        }
                    }
                }
            }
            return result;
        }
    }
    // 比较器
    public static class EdgeComparator implements Comparator<Graph.Edge> {
        @Override
        public int compare(Graph.Edge o1, Graph.Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    // 测试
    public static void main(String[] args) {
        // 使用 {2, 3, 1}, {3, 2, 1} 一对儿，将有向图转为无向图，来确保2种方法正确执行
        Integer[][] matrix = { {1, 2, 7}, {1, 3, 4}, {1, 4, 2}, {2, 5, 1}, {2, 3, 1}, {3, 2, 1}, {4, 6, 1}, {6, 7, 1} };
        Graph graph = GraphGenerator.createGraph(matrix);
        Set<Graph.Edge> result = Kruskal.kruskalMST(graph);
        for (Graph.Edge var : result) {
            System.out.println(var.weight);
        }
        System.out.println("=============");
        result = Prim.primMST(graph);
        for (Graph.Edge var : result) {
            System.out.println(var.weight + "\tfrom:" + var.from.value + "\tto:" + var.to.value);
        }
    }
}