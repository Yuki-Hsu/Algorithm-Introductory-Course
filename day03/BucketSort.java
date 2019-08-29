/*
桶排序和基数排序
时间复杂度O(N)，额外空间复杂度O(N)，实现做到稳定性
注意：
1，桶排序的扩展，排序后的最大相邻数差值问题
2，非基于比较的排序，对数据的位数和范围有限制。
*/
import java.util.Arrays;
public class BucketSort {
    // 桶排序，计数排序
    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 分析数据状况
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 设置桶大小
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {1,2,2,6,5,4,3,3,55,9,36,42};
        System.out.println(Arrays.toString(arr));
        BucketSort.bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}