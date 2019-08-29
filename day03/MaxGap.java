/*
桶排序应用例子
返回无序数组排序后相邻差值的最大值
input: 1 5 9 3 6 6 2
--> 1 3 5 5 6 6 9 -->2 2 0 1 0 3
output:3
*/
import java.util.Arrays;
public class MaxGap {
    public static int maxGap(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        //分析数据状况
        int len = arr.length;
        // 数据最小值
        int min = Integer.MAX_VALUE;
        // 数据最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        if (min == max) {
            return 0;
        }
        // 建立桶，hasNum：数据是否入桶，maxs：该桶最大值，mins：该桶最小值，len + 1:互斥原理
        boolean[] hasNum = new boolean[len + 1];
        int[] maxs = new int[len + 1];
        int[] mins = new int[len + 1];
        int bid = 0;
        for (int i = 0; i < len; i++) {
            // 计算几号桶的位置
            bid = (int)((arr[i] - min) * len / (max - min));
            mins[bid] = hasNum[bid] ? Math.min(mins[bid], arr[i]) : arr[i];
            maxs[bid] = hasNum[bid] ? Math.max(maxs[bid], arr[i]) : arr[i];
            hasNum[bid] = true;
        }
        int res = 0;
        int lastMax = maxs[0];
        for (int i = 0; i <= len; i++) {
            if (hasNum[i]) {
                res = Math.max(res, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return res;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {1,5,9,3,6,6,2};
        System.out.println(Arrays.toString(arr));
        System.out.println(MaxGap.maxGap(arr));
        System.out.println(Arrays.toString(arr));
    }
}