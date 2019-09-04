import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * 图的遍历
 * 宽度优先遍历 BFS
 * 1，利用队列实现
 * 2，从源节点开始依次按照宽度进队列，然后弹出
 * 3，每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
 * 4，直到队列变空
 * 
 * 深度优先遍历 DFS
 * 1，利用栈实现
 * 2，从源节点开始把节点按照深度放入栈，然后弹出
 * 3，每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 * 4，直到栈变空
 */
public class TraverseGraph {
    // Q1
    public static void bfs(Graph.Node node) {
        if (node == null) {
            return;
        }
        Queue<Graph.Node> queue = new LinkedList<>();
        HashSet<Graph.Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Graph.Node cur = queue.poll();
            System.out.println(cur.value);
            for (Graph.Node next : cur.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }
    }
    // Q2
    public static void dfs(Graph.Node node) {
        if (node == null) {
            return;
        }
        Stack<Graph.Node> stack = new Stack<>();
        HashSet<Graph.Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        // 入栈就打印且进 set 集合
        System.out.println(node.value);
        while(!stack.isEmpty()) {
            Graph.Node cur = stack.pop();
            for (Graph.Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        Integer[][] matrix = { {1, 2, 7}, {1, 3, 4}, {1, 4, 2}, {2, 5, 1}, {2, 3, 1}, {4, 6, 1}, {6, 7, 1} };
        Graph grap = GraphGenerator.createGraph(matrix);
        bfs(grap.nodes.get(1));
        System.out.println("================");
        dfs(grap.nodes.get(1));
    }
}