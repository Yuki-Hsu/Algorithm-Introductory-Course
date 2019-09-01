/*
题目一
用数组结构实现大小固定的队列和栈
*/
public class ArrayImpStack_Queue {
    // 数组实现栈结构
    public static class ArrayImpStack {
        private Integer[] arr;
        private Integer index;
        // 栈初始化
        public ArrayImpStack(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("栈初始化大小参数错误");
            }
            arr = new Integer[initSize];
            index = -1;
        }
        // 查看栈顶元素
        public Integer peek() {
            if (index == -1) {
                return null;
            }
            return arr[index];
        }
        // 压栈
        public boolean push(int obj) {
            if (index == arr.length - 1) {
                throw new ArrayIndexOutOfBoundsException("栈满了，添加失败");
            }
            arr[++index] = obj;
            return true;
        }
        // 出栈
        public Integer pop() {
            if (index == -1) {
                throw new ArrayIndexOutOfBoundsException("栈为空，出栈失败");
            }
            return arr[index--];
        }
    }
    // 数组实现队列结构
    public class ArrayImpQueue {
        private Integer[] arr;
        // 多用一个变量表示队列长度，避免 first 和 last 进行判断
        private Integer size;
        private Integer first;
        private Integer last;
        // 队列初始化
        public ArrayImpQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("队列初始化大小参数错误");
            }
            arr = new Integer[initSize];
            size = 0;
            first = -1;
            last = -1;
        }
        // 查看队头元素
        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[first];
        }
        // 入队
        public boolean push(int obj) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("队列满了，添加失败");
            }
            size++;
            last = last == arr.length - 1 ? 0 : last + 1;
            arr[last] = obj;
            if (first == -1) {
                first = 0;
            }
            return true;
        }
        // 出队
        public Integer poll() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("队列为空，出队列失败");
            }
            size--;
            int tmp = first;
            first = first == arr.length - 1 ? 0 : first + 1;
            return arr[tmp];
        }
    }

    // 测试
    public static void main(String[] args) {
        // 创建静态内部类
        ArrayImpStack_Queue.ArrayImpStack a1 = new ArrayImpStack_Queue.ArrayImpStack(5);
        a1.push(1);a1.push(2);a1.push(3);
        System.out.println(a1.peek());
        System.out.println(a1.pop());
        System.out.println(a1.peek());
        System.out.println(a1.pop());
        System.out.println("================");

        // 创建成员内部类
        ArrayImpStack_Queue.ArrayImpQueue a2 = new ArrayImpStack_Queue().new ArrayImpQueue(5);
        a2.push(1);a2.push(2);a2.push(3);
        System.out.println(a2.peek());
        System.out.println(a2.poll());
        System.out.println(a2.peek());
    }
}