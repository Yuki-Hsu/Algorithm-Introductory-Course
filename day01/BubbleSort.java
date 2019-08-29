import java.util.Arrays;

/*
冒泡排序的细节讲解
时间复杂度O(N^2)，额外空间复杂度O(1)，实现可以做到稳定性
*/

public class BubbleSort{
    // 冒泡排序
    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for(int e = arr.length - 1; e > 0; e--){
            for(int i = 0; i < e; i++){
                if(arr[i] > arr[i+1]){
                    // 交换
                    arr[i]   = arr[i] ^ arr[i+1];
                    arr[i+1] = arr[i] ^ arr[i+1];
                    arr[i]   = arr[i] ^ arr[i+1];
                }
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {4,20,66,3,5,8,51};
        System.out.println(Arrays.toString(arr));
        BubbleSort.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}