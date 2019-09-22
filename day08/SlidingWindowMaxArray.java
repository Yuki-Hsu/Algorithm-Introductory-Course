/*
题目三
生成窗口最大值数组
【题目】有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个 位置。
例如，数组为[4,3,5,4,3,3,6,7]，窗口大小为3时：
[4  3  5] 4  3  3  6  7               窗口中最大值为5
4 [3  5  4] 3  3  6  7                窗口中最大值为5
4  3 [5  4  3] 3  6  7                窗口中最大值为5 
4  3  5 [4  3  3] 6  7                窗口中最大值为4
4  3  5  4 [3  3  6] 7                窗口中最大值为6
4  3  5  4  3 [3  6  7]               窗口中最大值为7
如果数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值。
请实现一个函数。输入：整型数组arr，窗口大小为w。
输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值。
以本题为例，结果应该返回{5,5,5,4,6,7}。
【思路】
准备一个双端队列，队列大小不超过窗口值，遍历数组，如果比队列尾部大，就弹出并在尾部加入新值，确保队列是由大到小排列，
当窗口右滑时，划出的左边值如果等于双端队列头部，则弹出头部
*/
import java.util.Arrays;
import java.util.LinkedList;
public class SlidingWindowMaxArray {
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        // 双端队列
        LinkedList<Integer> qmax = new LinkedList<>();
        // 结果集
        int[] res = new int[arr.length - w +1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 新加入的如果比队列尾部大，就弹出并在尾部加入新值
            while (!qmax.isEmpty() && qmax.peekLast() <= arr[i]) {
                qmax.pollLast();
            }
            // 新元素加入队列尾部，此队列为由大到小排列
            qmax.addLast(arr[i]);
            // 当窗口右滑时，划出的左边值如果等于双端队列头部（即最大）值，则弹出头部
            if(i >= w) {
                if (qmax.peekFirst() == arr[i - w]) {
                    // 此最大值已经失效
                    qmax.pollFirst();
                }
            }
            // 统计结果集
            if (i >= w - 1) {
                res[index++] = qmax.peekFirst();
            }
        }
        return res;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = { 4, 3, 5, 4, 3, 3, 6, 7 };
        int w = 3;
        System.out.println(Arrays.toString(getMaxWindow(arr, w)));
    }
}