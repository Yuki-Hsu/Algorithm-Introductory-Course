/*
题目七
给你一个二维数组，二维数组中的每个数都是正数，要求从左上 角走到右下角，每一步只能向右或者向下。沿途经过的数字要累 加起来。返回最小的路径和。
【思路】
方法一：暴力递归
方法二：傻缓存
方法三：动态规划
*/
import java.util.HashMap;
public class MinPath {
    // 暴力递归
    public static int minPath1(int[][] matrix) {
        return process1(matrix, matrix.length - 1, matrix[0].length - 1);
    }
    public static int process1(int[][] matrix, int i, int j) {
        // 终止条件
        if (i == 0 && j == 0) {
            return matrix[i][j];
        }
        if (i == 0 && j != 0) {
            return matrix[i][j] + process1(matrix, i, j - 1);
        }
        if (i != 0 && j == 0) {
            return matrix[i][j] + process1(matrix, i - 1, j);
        }
        // 返回当前元素值 + 两种子程序中数值较小的子程序
        return matrix[i][j] + Math.min(process1(matrix, i, j - 1), process1(matrix, i - 1, j));
    }

    // 递归中增加缓存，减小计算量
    public static int minPath2(int[][] matrix) {
        // 缓存表，存储中间计算过的缓存值
        HashMap<String, Integer> cache = new HashMap<>();
        return process2(matrix, cache, matrix.length - 1, matrix[0].length - 1);
    }
    public static int process2(int[][] matrix, HashMap<String, Integer> cache, int i, int j) {
        // 终止条件
        if (i == 0 && j == 0) {
            return matrix[i][j];
        }
        if (i == 0 && j != 0) {
            // 先查询，不存在则递归
            String nextKey = String.valueOf(i) + "_" + String.valueOf(j - 1);
            if(cache.containsKey(nextKey)) {
                return matrix[i][j] + cache.get(nextKey);
            }
            return matrix[i][j] + process2(matrix, cache, i, j - 1);
        }
        if (i != 0 && j == 0) {
            // 先查询，不存在则递归
            String nextKey = String.valueOf(i - 1) + "_" + String.valueOf(j);
            if(cache.containsKey(nextKey)) {
                return matrix[i][j] + cache.get(nextKey);
            }
            return matrix[i][j] + process2(matrix, cache, i - 1, j);
        }
        String key = String.valueOf(i) + "_" + String.valueOf(j);
        // 返回前，先存储
        cache.put(key, matrix[i][j] + Math.min(process2(matrix, cache, i, j - 1), process2(matrix, cache, i - 1, j)));
        // 返回当前元素值 + 两种子程序中数值较小的子程序
        return matrix[i][j] + Math.min(process2(matrix, cache, i, j - 1), process2(matrix, cache, i - 1, j));
    }

    // 动态规划：由base case状态（终止条件），往上递推计算
    public static int minPath3(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        // 目标解空间
        int[][] dp = new int[matrix.length][matrix[0].length];
        dp[0][0] = matrix[0][0];
        for (int i = 1; i < matrix.length; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        for (int j = 1; j < matrix[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        // 中间解，使用公式递推
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + matrix[i][j];
            }
        }
        return dp[matrix.length - 1][matrix[0].length - 1];
    }

    // 测试
    public static void main(String[] args) {
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
        System.out.println(minPath1(m));
        System.out.println(minPath2(m));
        System.out.println(minPath3(m));
    }
}