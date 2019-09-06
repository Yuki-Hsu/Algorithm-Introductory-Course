/*
题目八
给你一个数组arr，和一个整数aim。如果可以任意选择arr中的数字，能不能累加得到aim，返回true或者false
*/
public class MoneyProblem {
    // 暴力递归
    public static boolean money1(int[] arr, int aim) {
        return process1(arr, 0, 0, aim);
    }
    public static boolean process1(int[] arr, int i, int sum, int aim) {
        if (sum == aim) {
            return true;
        }
        if (i == arr.length) {
            return false;
        }
        // 递归子程序，不要 arr[i] ，和要 arr[i]
        return process1(arr, i + 1, sum, aim) || process1(arr, i + 1, sum + arr[i], aim);
    }

    // 动态规划
    public static boolean money2(int[] arr, int aim) {
        // 根据 base case 创建解空间二维表 i 的变化范围 0-arr.length sum 的变化范围 0-aim
        boolean[][] dp = new boolean[arr.length + 1][aim + 1];
        // 基础状态 sum==aim 时，为 true
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim] = true;
        }
        // 基础状态 i==arr.length 时，为 false
        for (int j = 0; j < dp[0].length - 1; j++) {
            dp[dp.length - 1][j] = false;
        }
        // 根据依赖关系，递推二维表中其余位置的值
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = aim - 1; j >= 0; j--) {
                if (j + arr[i] <= aim) {
                    // 递归子程序得到依赖关系
                    dp[i][j] = dp[i + 1][j] || dp[i + 1][j + arr[i]];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = { 1, 2, 41, 12, 5, 5, 3 };
        int aim = 29;
        System.out.println(money1(arr, aim));
        System.out.println(money2(arr, aim));
    }
}