/*
题目九
给定两个数组w和v，两个数组长度相等，w[i]表示第i件商品的重量，v[i]表示第i件商品的价值。
再给定一个整数bag，要求你挑选商品的重量加起来一定不能超过bag，返回满足这个条件下，你能获得的最大价值。
【思路】
方法一：暴力递归
方法二：改成动态规划
*/
public class Knapsack {
    // 暴力递归
    public static int maxValue1(int[] w, int[] v, int bag) {
        return process1(w, v, 0, 0, bag);
    }
    public static int process1(int[] w, int[] v, int i, int sum, int bag) {
        if (sum > bag) {
            // 超过背包数量时，返回最小值，并停止递归
            return Integer.MIN_VALUE;
        }
        if (i == w.length) {
            // 遍历到最后一个重量时，没有新的重量加入，所以返回 0
            return 0;
        }
        return Math.max(process1(w, v, i + 1, sum, bag), v[i] + process1(w, v, i + 1, sum + w[i], bag));
    }

    // 动态优化
    public static int maxValue2(int[] w, int[] v, int bag) {
        int[][] dp = new int[w.length + 1][bag + 1];
        // 根据 base case 设置二维解空间初始状态
        for (int j = 0; j < dp[0].length; j++) {
            dp[w.length][j] = 0;
        }
        // 根据依赖关系求解
        for (int i = w.length - 1; i >= 0; i--) {
            for (int j = bag; j >= 0; j--) {
                // 依赖关系
                dp[i][j] = dp[i + 1][j];
                // 如果不超过背包值，则根据依赖关系选择最大的
                if (j + w[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i + 1][j + w[i]]);
                }
            }
        }
        return dp[0][0];
    }

    // 测试
    public static void main(String[] args) {
        int[] w = { 3, 2, 4, 7 };
        int[] v = { 5, 6, 3, 19 };
        int bag = 11;
        System.out.println(maxValue1(w, v, bag));
        System.out.println(maxValue2(w, v, bag));
    }
}