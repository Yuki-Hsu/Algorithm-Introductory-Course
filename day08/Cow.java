/*
题目五
母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只 母牛，假设不会死。求N年后，母牛的数量。
进阶  
如果每只母牛只能活10年，求N年后，母牛的数量。
【思路】
Q1：F(N) = F(N-1) + F(N-3) 今年的牛 = 去年的牛 + 三年前的牛（可以生育了）
Q1：F(N) = F(N-1) + F(N-3) - F(N-10) 今年的牛 = 去年的牛 + 三年前的牛（可以生育了） - 十年前的牛（死了）
*/
public class Cow {
    //  暴力递归
    public static int cowNumber1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        return cowNumber1(n - 1) + cowNumber1(n - 3);
    }
    // 动态规划
    public static int cowNumber2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    // 测试
    public static void main(String[] args) {
        int n = 20;
        System.out.println(cowNumber1(n));
        System.out.println(cowNumber2(n));
    }
}