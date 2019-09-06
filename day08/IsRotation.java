/*
题目四
想想一个字符串其实是个循环数组，可以循环右移。比如”abc”，向右循环右移一位，得到”cab”，
向右循环右移两位，得到“bca”， 向右循环右移三位，得到“abc”，
给定两个字符串str1和str2，判断str2是不是由str1循环右移得到的。
【思路】
newstr = str1 + str1，然后判断 newstr 是否包含 str2
*/
public class IsRotation {
    public static boolean isRotation(String a, String b) {
		if (a == null || b == null || a.length() != b.length()) {
			return false;
		}
        String b2 = b + b;
        // 使用 KMP 算法查找字符串出现的索引位
		return getIndexOf(b2, a) != -1;
    }
    
    // KMP Algorithm
	public static int getIndexOf(String s, String m) {
		if (s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) {
				si++;
				mi++;
			} else if (next[mi] == -1) {
				si++;
			} else {
				mi = next[mi];
			}
		}
		return mi == ms.length ? si - mi : -1;
	}
	public static int[] getNextArray(char[] ms) {
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;
		while (pos < next.length) {
			if (ms[pos - 1] == ms[cn]) {
				next[pos++] = ++cn;
			} else if (cn > 0) {
				cn = next[cn];
			} else {
				next[pos++] = 0;
			}
		}
		return next;
    }
    
    //测试
    public static void main(String[] args) {
		String str1 = "123abc";
		String str2 = "bc123a";
		System.out.println(isRotation(str1, str2));

	}
}