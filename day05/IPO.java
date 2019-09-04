/*
题目三
输入： 参数1，正数数组costs 参数2，正数数组profits 参数3，正数k 参数4，正数m
costs[i]表示i号项目的花费 profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
k表示你不能并行、只能串行的最多做k个项目 m表示你初始的资金
说明：你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
输出：你最后获得的最大钱数。
【思路】
贪心策略：每次从可以做的项目中选择收益最大的项目，项目做完后成本增加，循环前面的步骤
可以根据成本创建小根堆，小根堆出队列，相应的收益进大根堆，大根堆做收益最大的项目（poll）
成本增加，继续前面的步骤
*/
import java.util.Comparator;
import java.util.PriorityQueue;
public class IPO {
    public static class Node {
		public int p;
		public int c;
		public Node(int p, int c) {
			this.p = p;
			this.c = c;
		}
    }
    // 比较器
    public static class MinCostComparator implements Comparator<Node> {
		@Override
		public int compare(Node o1, Node o2) {
			return o1.c - o2.c;
		}
    }
    public static class MaxProfitComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return o2.p - o1.p;
		}

    }
    public static int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		Node[] nodes = new Node[Profits.length];
		for (int i = 0; i < Profits.length; i++) {
			nodes[i] = new Node(Profits[i], Capital[i]);
		}
		PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
		PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
		for (int i = 0; i < nodes.length; i++) {
			minCostQ.add(nodes[i]);
		}
		for (int i = 0; i < k; i++) {
			while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
				maxProfitQ.add(minCostQ.poll());
			}
			if (maxProfitQ.isEmpty()) {
				return W;
			}
			W += maxProfitQ.poll().p;
		}
		return W;
    }
    
    // 测试
    public static void main(String[] args) {
        int k = 3;
        int W = 5;
        int[] Profits = { 2, 5, 6, 1};
        int[] Capital = { 3, 4, 7, 2};
        System.out.println(IPO.findMaximizedCapital(k, W, Profits, Capital));
    }
}