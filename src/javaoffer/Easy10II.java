package javaoffer;

import org.junit.Test;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 提示：
 *
 * 0 <= n <= 100
 * 思路：与70题相同
 *
 */
public class Easy10II {

	public int numWays(int n) {
		int f0 = 1 % 1000000007;
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
