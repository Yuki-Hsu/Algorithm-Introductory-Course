/*
归并排序
时间复杂度O(N*logN)，额外空间复杂度O(N)，实现可以做到稳定性
注意：
1，库函数中排序的实现是综合排序，比如插入+快速；比如为了稳定性，排序算法往往是快排+堆排序
2，归并排序和快速排序，都一定存在非递归的实现
3，归并排序，存在额外空间复杂度O(1)的实现，但是非常难，你不需要掌握
4，归并排序的扩展，小和问题，逆序对
*/
import java.util.Arrays;
public class MergeSort {
    // 归并排序
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 递归调用
        sortProcess(arr, 0, arr.length - 1);
    }
    // 递归函数
    public static void sortProcess(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        // 获取中间点的位置
        int M = L + ((R - L) >> 1);
        // 排好左边
        sortProcess(arr, L, M);
        // 排好右边
        sortProcess(arr, M + 1, R);
        // 调用 Merge 融合
        merge(arr, L, M, R);
    }
    // Merge 融合
    public static void merge(int[] arr, int L, int M, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        int i = 0;
        // 左边数组指针
        int p1 = L;
        // 右边数组指针
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        // 复制回去
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {4,20,66,3,5,8,51};
        System.out.println(Arrays.toString(arr));
        MergeSort.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}

/*
归并复杂度分析：T(N) = 2T(N/2) + N
Master公式：
T(N) = aT(N/b) + N^d
如果
1. log(b,a) > d
    T(N) = N^(log(b,a))
2. log(b,a) == d
    T(N) = N^d*logN
3. log(b,a) < d
    N^d
*/