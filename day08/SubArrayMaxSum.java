/*
题目二
给定一个数组arr，返回所有子数组的累加和中，最大的累加和
【思路】
使用一个指针 i 从左向右依次遍历，并累加到指针位置处的值，当累加值 cur 小于零时，累加值归零，同时用一个值 max 记录期间出现的最大累加值
*/
public class SubArrayMaxSum {
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(cur, max);
            cur = cur > 0 ? cur : 0;
        }
        return max;
    }

    //测试
    public static void main(String[] args) {
        int[] arr1 = { -2, -3, -5, 40, -10, -10, 100, 1 };
        System.out.println(maxSum(arr1));

        int[] arr2 = { -2, -3, -5, 0, 1, 2, -1 };
        System.out.println(maxSum(arr2));

        int[] arr3 = { -2, -3, -5, -1 };
        System.out.println(maxSum(arr3));

    }
}