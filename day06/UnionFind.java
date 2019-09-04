/*
并查集
*/
import java.util.HashMap;
import java.util.List;
public class UnionFind {
    // 并查集所存元素节点
    public static class Node {
        // ...
    }
    // 并查集
    public static class UnionFindSet {
        // （key，value）key 节点的父节点为 value 节点
        public HashMap<Node, Node> fatherMap;
        // value 表示 key 节点作为代表元素所在的集合的大小
        public HashMap<Node, Integer> sizeMap;
        public UnionFindSet() {
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }
        // 创建并查集，初始化添加节点，每个节点自成一个集合，并作为集合代表元素
        public void makeSet(List<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }
        // 查找代表元素，即定位属于哪个集合
        public Node findFather(Node n) {
            Node father = fatherMap.get(n);
            if (father != n) {
                // 递归查询，并修改沿路节点，使其父节点为当前集合的代表元素
                father = findFather(father);
            }
            fatherMap.put(n, father);
            return father;
        }
        // 合并集合
        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }
            Node aFather = findFather(a);
            Node bFather = findFather(b);
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
    }
}