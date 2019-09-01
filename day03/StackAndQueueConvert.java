/*
题目三
如何仅用队列结构实现栈结构？
如何仅用栈结构实现队列结构？
【思路】
Q1：用两个队列来回倒，倒的时候保留最后一个元素不入辅助队列，将其弹出
Q2：用两个栈，一个push栈，一个pop栈，将push栈导入pop栈
    原则1：pop栈不为空时，push栈不能导数据进pop
    原则2：push栈导入pop栈时，必须一次性全导完
*/
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class StackAndQueueConvert {
    // Q1
    public static class TwoQueuesStack {
        private Queue<Integer> queue;
        private Queue<Integer> help;
        // 初始化
        public TwoQueuesStack() {
            queue = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }
        // 入栈
        public void push(int pushInt) {
            queue.add(pushInt);
        }
        // 查看栈顶元素
        public int peek() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            help.add(res);
            // 交换队列引用
            swap();
            return res;
        }
        // 出栈
        public int pop() {
            if (queue.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }
            while (queue.size() != 1) {
                help.add(queue.poll());
            }
            int res = queue.poll();
            // help.add(res);
            // 交换队列引用
            swap();
            return res;
        }
        // 引用交换
        private void swap() {
            Queue<Integer> tmp = help;
            help = queue;
            queue = tmp;
        }
    }

    // Q2
    public static class TwoStacksQueue {
        private Stack<Integer> pushStack;
        private Stack<Integer> popStack;
        // 初始化
        public TwoStacksQueue() {
            pushStack = new Stack<Integer>();
            popStack = new Stack<Integer>();
        }
        // 入队列
        public void push(int pushInt) {
            pushStack.push(pushInt);
        }
        // 查看队头元素
        public int peek() {
            if (popStack.empty() && pushStack.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (popStack.empty()) {
                while (!pushStack.empty()) {
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.peek();
        }
        // 队头元素出队列
        public int poll() {
            if (popStack.empty() && pushStack.empty()) {
                throw new RuntimeException("Queue is empty!");
            } else if (popStack.empty()) {
                while (!pushStack.empty()) {
                    popStack.push(pushStack.pop());
                }
            }
            return popStack.pop();
        }
    }

    public static void main(String[] args) {
        TwoQueuesStack stack1 = new TwoQueuesStack();
        stack1.push(3);
        System.out.println(stack1.peek());
        stack1.push(4);
        System.out.println(stack1.peek());
        stack1.push(1);
        System.out.println(stack1.peek());
        System.out.println(stack1.pop());
        System.out.println(stack1.peek());

        System.out.println("=============");

        TwoStacksQueue queue2 = new TwoStacksQueue();
        queue2.push(3);
        System.out.println(queue2.peek());
        queue2.push(4);
        System.out.println(queue2.peek());
        queue2.push(1);
        System.out.println(queue2.peek());
        System.out.println(queue2.poll());
        System.out.println(queue2.peek());
    }
}
