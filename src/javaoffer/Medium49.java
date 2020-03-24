package javaoffer;

import org.junit.Test;

/**
 * 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 *
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 说明:  
 * 1 是丑数。
 * n 不超过1690。
 * 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
 *
 *思路：第一个丑数是1，以后的丑数都是基于前面的小丑数分别乘2，3，5构成的。
 *
 */
public class Medium49 {
	public int nthUglyNumber(int n) {
		int p2 = 0, p3 = 0, p5 = 0;
		int[] dp = new int[n];
		dp[0] = 1;
		for (int i = 1; i < n; i++) {
			dp[i] = Math.min(dp[p2] * 2, Math.min(dp[p3] * 3, dp[p5] * 5));
			if (dp[i] == dp[p2] * 2) p2++;
			if (dp[i] == dp[p3] * 3) p3++;
			if (dp[i] == dp[p5] * 5) p5++;
		}
		return dp[n - 1];
	}

	@Test
	public void test1() {
		System.out.println(nthUglyNumber(20));
	}

}
