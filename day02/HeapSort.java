/*
数组 <--> 堆结构转换（下标变换）
左孩子：2*i+1
右孩子：2*i+2
父节点：(i-1)/2
*/
/*
时间复杂度O(N*logN)，额外空间复杂度O(1)，实现不能做到稳定性
关键步骤：heapInsert, heapify，堆的扩大和缩小操作
注意：
1，堆排序中，建立堆的操作O(N)
2，堆排序的核心数据结构：堆，也可以说是优先级队列
堆建立：O(N)，堆调整：N*O(logN)
*/
import java.util.Arrays;
public class HeapSort {
    // 堆排序
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 建立大根堆
            heapInsert(arr, i);
        }
        int size = arr.length;
        // 将堆中最大的值交换到末尾
        swap(arr, 0, --size);
        while (size > 0) {
            // 调整大根堆，堆范围 [0,size)，排好范围[size,arr.length-1]
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    // 建立堆
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
    // 删除堆顶元素，并调整堆结构
    public static void heapify(int[] arr, int index, int size) {
        // 堆顶左孩子
        int left = index * 2 + 1;
        while (left < size) {
            // 得到左右孩子中较大值的下标
            int largest = (left + 1 < size && arr[left + 1] > arr[left]) ? left + 1 : left;
            // 和父节点比较获得最大值下标
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            // 往下一层调整
            left = index * 2 +1;
        }
    }
    // 交换函数
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {1,2,2,6,5,4,3,3,55,9,36,42};
        System.out.println(Arrays.toString(arr));
        HeapSort.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}