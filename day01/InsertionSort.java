import java.util.Arrays;

/*
插入排序的细节讲解
时间复杂度O(N^2)，额外空间复杂度O(1)，实现可以做到稳定性
*/

public class InsertionSort {
    // 插入排序
    public static void insertionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j+1]; j--) {
                // 交换
                swap(arr, j, j+1);
            }
        }
    }
    // 交换函数
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {4,20,66,3,5,8,51};
        System.out.println(Arrays.toString(arr));
        InsertionSort.insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}