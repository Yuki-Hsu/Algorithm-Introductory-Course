/*
题目一
随时找到数据流的中位数
【题目】 有一个源源不断地吐出整数的数据流，假设你有足够的空间来 保存吐出的数。
请设计一个名叫MedianHolder的结构， MedianHolder可以随时取得之前吐出所有数的中位数。
【要求】 1．如果MedianHolder已经保存了吐出的N个数，那么任意时刻
将一个新数加入到MedianHolder的过程，其时间复杂度是 O(logN)。
2．取得已经吐出的N个数整体的中位数的过程，时间复杂度为 O(1)。
【思路】
使用堆结构，准备一个大根堆和一个小根堆，元素大于大根堆堆顶则如小根堆，否则入大根堆，然后调整两个堆的大小
*/
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.lang.model.util.ElementScanner6;
public class MadianQuick {
    public static class MedianHolder {
        private PriorityQueue<Integer> maxHeap;
        private PriorityQueue<Integer> minHeap;
        // 初始化
        public MedianHolder() {
            maxHeap = new PriorityQueue<Integer>(new MaxHeapComparator());
            minHeap = new PriorityQueue<Integer>(new MinHeapComparator());
        }
        // 调整堆大小
        private void modifyTwoHeapsSize() {
            if (this.maxHeap.size() == this.minHeap.size() + 2) {
                this.minHeap.add(this.maxHeap.poll());
            }
            if (this.minHeap.size() == this.maxHeap.size() + 2) {
                this.maxHeap.add(this.minHeap.poll());
            }
        }
        // 添加元素
        public void addNumber(int num) {
            if (this.maxHeap.isEmpty()) {
                this.maxHeap.add(num);
                return;
            }
            // 入大根堆
            if (this.maxHeap.peek() >= num) {
                this.maxHeap.add(num);
            // 入小根堆
            } else if (this.minHeap.isEmpty()) {
                this.minHeap.add(num);
                return;
            } else {
                this.minHeap.add(num);
            }
            modifyTwoHeapsSize();
        }
        // 获取中位数
        public Integer getMedian() {
            int maxHeapSize = this.maxHeap.size();
            int minHeapSize = this.minHeap.size();
            if (maxHeapSize + minHeapSize == 0) {
                return null;
            }
            Integer maxHeapHead = this.maxHeap.peek();
            Integer minHeapHead = this.minHeap.peek();
            // 判断奇偶性
            if (((maxHeapSize + minHeapSize) & 1) == 0) {
                return (maxHeapHead + minHeapHead) / 2;
            }
            return maxHeapSize > minHeapSize ? maxHeapHead : minHeapHead;
        }
    }
    // 比较器
    public static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o2 > o1) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    public static class MinHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o2 < o1) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    // 测试
    public static void main(String[] args) {
        MadianQuick.MedianHolder medianHold = new MadianQuick.MedianHolder();
        medianHold.addNumber(6);
        medianHold.addNumber(2);
        medianHold.addNumber(1);
        medianHold.addNumber(4);
        System.out.println(medianHold.getMedian());
        medianHold.addNumber(4);
        System.out.println(medianHold.getMedian());
    }
}