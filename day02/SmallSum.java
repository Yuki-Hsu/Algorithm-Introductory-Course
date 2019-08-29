/*
归并排序应用例子
返回数组中比当前元素小的元素的总和
input： 4 20 66 3 5 8 51
output：0+4+24+0+7+12+40=87
*/
import java.util.Arrays;
public class SmallSum {
    // 归并排序
    public static int mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // 递归调用
        return sortProcess(arr, 0, arr.length - 1);
    }
    // 递归函数
    public static int sortProcess(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        // 获取中间点的位置
        int M = L + ((R - L) >> 1);
        return sortProcess(arr, L, M) + sortProcess(arr, M + 1, R) + merge(arr, L, M, R);
    }
    // Merge 融合
    public static int merge(int[] arr, int L, int M, int R) {
        // 辅助数组
        int[] help = new int[R - L + 1];
        int i = 0;
        // 左边数组指针
        int p1 = L;
        // 右边数组指针
        int p2 = M + 1;
        // 计算小和
        int res = 0;
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
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
        return res;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {4,20,66,3,5,8,51};
        System.out.println(Arrays.toString(arr));
        int res = SmallSum.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(res);
    }
}