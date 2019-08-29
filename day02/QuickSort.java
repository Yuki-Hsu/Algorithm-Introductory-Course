/*
随机快速排序
时间复杂度O(N*logN)，额外空间复杂度O(logN)，常规实现做不到稳定性。
注意：
1，快速排序中，额外空间复杂度最低为O(logN)
2，快速排序，可以做到稳定性的实现，但是非常难，你不需要掌握
3，荷兰国旗问题的实现，和快速排序中的改进
*/
import java.util.Arrays;
public class QuickSort {
    // 快速排序
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 递归调用
        sortProcess(arr, 0, arr.length - 1);
    }
    // 递归函数
    public static void sortProcess(int[] arr, int L, int R) {
        if (L < R) {
            // 随机快速排序，选择随机的值为 partition 划分值
            swap(arr, L + (int)(Math.random() * (R - L + 1)), R);
            // partition 过程
            int[] p = partition(arr, L, R);
            // 递归处理小于划分值部分和大于划分值部分
            sortProcess(arr, L, p[0] - 1);
            sortProcess(arr, p[1] + 1, R);
        }
    }
    // 荷兰国旗问题，partition 划分
    public static int[] partition(int[] arr, int L, int R) {
        // 小于区边界
        int less = L - 1;
        // 大于区边界
        int more = R;
        while (L < more) {
            if (arr[L] < arr[R]) {
                swap(arr, ++less, L++);
            } else if (arr[L] > arr[R]) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        // 处理最后一个划分值和大于区的边界
        swap(arr, more, R);
        return new int[] {less + 1, more};
    }
    // 交换函数
    public static void swap(int[] arr, int i, int j) {
        /* 注意：不能使用，因为存在 i==j 的情况
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j]; */
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {1,2,2,6,5,4,3,3,55,9,36,42};
        System.out.println(Arrays.toString(arr));
        QuickSort.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}