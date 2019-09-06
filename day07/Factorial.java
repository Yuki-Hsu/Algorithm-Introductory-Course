/*
介绍递归和动态规划
暴力递归：
1，把问题转化为规模缩小了的同类问题的子问题
2，有明确的不需要继续进行递归的条件(base case)
3，有当得到了子问题的结果之后的决策过程
4，不记录每一个子问题的解
动态规划
1，从暴力递归中来
2，将每一个子问题的解记录下来，避免重复计算
3，把暴力递归的过程，抽象成了状态表达
4，并且存在化简状态表达，使其更加简洁的可能
*/
public class Factorial {
	public static long getFactorial1(int n) {
		if (n == 1) {
			return 1L;
		}
		return (long) n * getFactorial1(n - 1);
	}

	public static long getFactorial2(int n) {
		long result = 1L;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static void main(String[] args) {
		int n = 5;
		System.out.println(getFactorial1(n));
		System.out.println(getFactorial2(n));
	}

}