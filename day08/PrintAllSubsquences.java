/*
题目三
打印一个字符串的全部子序列，包括空字符串
*/
public class PrintAllSubsquences {
    public static void printAllSubsquences(String str) {
        char[] chs = str.toCharArray();
        process1(chs, 0);
        System.out.println("=============");
        process2(chs, 0, "");
        System.out.println("=============");
    }
    // 方法一
    public static void process1(char[] chs, int i) {
        if (i == chs.length) {
            System.out.println(String.valueOf(chs));
            return;
        }
        process1(chs, i + 1);
        char tmp = chs[i];
        chs[i] = 0;
        process1(chs, i + 1);
        chs[i] = tmp;
    }
    // 方法二
    public static void process2(char[] chs, int i, String pre) {
        if (i == chs.length) {
            System.out.println(pre);
            return;
        }
        process2(chs, i + 1, pre + String.valueOf(chs[i]));
        process2(chs, i + 1, pre);
    }

    //测试
    public static void main(String[] args) {
        String test = "abc";
        printAllSubsquences(test);
    }
}