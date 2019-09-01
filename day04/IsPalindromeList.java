/*
题目十一
判断一个链表是否为回文结构
【题目】 给定一个链表的头节点head，请判断该链表是否为回文结构。
例如： 1->2->1，返回true。 1->2->2->1，返回true。 15->6->15，返回true。 1->2->3，返回false。
进阶： 如果链表长度为N，时间复杂度达到O(N)，额外空间复杂度达到O(1)。
【思路】
方法一：准备相同大小的栈，链表元素依次进栈，然后弹栈比较
方法二：用两个指针，快指针一次两步，慢指针一次一步，走完链表后，慢指针到达终点，然后准备n/2大小的栈，元素进栈，然后出栈对比
方法三：用两个指针，快指针一次两步，慢指针一次一步，走完链表后，慢指针到达终点，然后逆序后半部分的链表，接着同时从头和尾部向中间逼近并比较
*/
import java.util.Stack;
public class IsPalindromeList {
    // 链表节点
    public static class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }
    // 打印链表
    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
    // 方法一实现
    public static boolean isPalindrome_1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
    // 方法二实现
    public static boolean isPalindrome_2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<Node>();
        // 后半部分入栈
        while (slow.next != null) {
            stack.push(slow.next);
            slow = slow.next;
        }
        while (!stack.isEmpty()) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
     // 方法三实现
     public static boolean isPalindrome_3(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // n1 此时为中间节点
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        // 逆序
        while (n2 != null) {
            // 记录后继节点
            n3 = n2.next;
            n2.next = n1;
            // 交换n1 和 n2 引用
            n1 = n2;
            n2 = n3;
        }
        // n3 保存尾节点
        n3 = n1;
        n2 = head;
        boolean res = true;
        while (n1 != null && n2 != null) { // check palindrome
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next; // left to mid
            n2 = n2.next; // right to mid
        }
        // 恢复逆序
        n1 = n3.next;
        n3.next = null;
        while (n1 != null) { // recover list
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;
     }

     // 测试
     public static void main(String[] args) {
        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome_1(head) + " | ");
        System.out.print(isPalindrome_2(head) + " | ");
        System.out.println(isPalindrome_3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}