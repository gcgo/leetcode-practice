package javaoffer;

import org.junit.Test;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 *
 * 提示：
 * 0 <= n <= 100
 * 思路：
 *
 */
public class Easy10I {

	public int fib(int n) {
		int f0 = 0 % 1000000007;
		int f1 = 1 % 1000000007;
		if (n==0)return f0;
		if (n==1)return f1;
		int fi = 0;
		for (int i = 2; i <= n; i++) {
			fi = (f0 + f1) % 1000000007;
			f0 = f1;
			f1 = fi;
		}
		return fi;
	}

	@Test
	public void test1() {
	}
}
