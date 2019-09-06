/*
题目一
给定一个数组代表一个容器，比如[3,1,2,4]，代表0位置是一个宽度为1，高度为3的直方图。
代表1位置是一个宽度为1，高度为1的直方图。 代表2位置是一个宽度为1，高度为2的直方图。
代表3位置是一个宽度为1，高度为4的直方图。 所有直方图的底部都在一条水平线上，且紧靠着。
把这个图想象成一个容器，这个容器可以装3格的水。 给定一个没有负数的数组arr，返回能装几格水？
【思路】
方法一：从0开始遍历，每一个位置的水等于它左边位置的最大值和右边位置的最大值中较小的一个和该位置的差，然后依次累加
方法二：辅助数组法，leftMaxs数组第 i 位表示从 0~i-1 位置的最大值，rightMaxs数组第 i 位表示从 i+1~N-1 位置的最大值，
方法三：和方法二类似，省略一个leftMaxs数组，在遍历时边计算左边位置最大值，边计算累加值
方法四：准备一个左指针和右指针，相对应的记录左指针以左的最大值，和右指针以右的最大值，然后左右指针往中间移动并累加水
*/
public class WaterProblem {
    // 方法一
    public static int getWater1(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;
            // 寻找左边最大值
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(arr[l], leftMax);
            }
            // 寻找右边最大值
            for (int r = i + 1; r < arr.length; r++) {
                rightMax = Math.max(arr[r], rightMax);
            }
            // 计算该位置的水
            value += Math.max(0, Math.min(leftMax, rightMax) - arr[i]);
        }
        return value;
    }
    // 方法二
    public static int getWater2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        int n = arr.length - 2;
        // 存储左边最大值
        int[] leftMaxs = new int[n];
        leftMaxs[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
        }
        // 存储右边最大值
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = arr[arr.length - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
        }
        // 计算该位置的水
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(leftMaxs[i - 1], rightMaxs[i - 1]) - arr[i]);
        }
        return value;
    }
    // 方法三
    public static int getWater3(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        int n = arr.length - 2;
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = arr[n + 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
        }
        int leftMax = arr[0];
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(leftMax, rightMaxs[i - 1]) - arr[i]);
            leftMax = Math.max(leftMax, arr[i]);
        }
        return value;
    }
    // 方法四
    public static int getWater4(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        int l = 1;
        int r = arr.length - 2;
        while (l <= r) {
            if (leftMax <= rightMax) {
                // 左边小于右边，则瓶颈在左边
                value += Math.max(0, leftMax - arr[l]);
                // 移动左指针并更新左边最大值
                leftMax = Math.max(leftMax, arr[l++]);
            } else {
                // 左边大于右边，则瓶颈在右边
                value += Math.max(0, rightMax - arr[r]);
                // 移动右指针并更新右边最大值
                leftMax = Math.max(rightMax, arr[r--]);
            }
        }
        return value;
    }

    // 测试
    public static void main(String[] args) {
        int[] arr = {7, 4, 5, 1, 3, 9};
        System.out.println(getWater1(arr));
        System.out.println(getWater2(arr));
        System.out.println(getWater3(arr));
        System.out.println(getWater4(arr));

    }
}