package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 *
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * 思路：动态规划
 * dp[i]表示：数字 i 拆分为至少两个正整数之和的最大乘积。为了方便计算，dp 的长度是 n + 1，值初始化为 1。
 * 要想计算dp[i]，把i拆成j,i-j两个数，计算j*i-j，而i-j还可以继续拆分，它所能获得的最大乘积就是dp[i-j]，
 * 所以对于每个j都取这两种情况最大值。
 *
 *i从3开始遍历，因为dp[2]=1，显而易见
 */
public class Medium343 {
	public int integerBreak(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, 1);
		for (int i = 3; i <=n; i++) {
			for (int j = 1; j < i; j++) {
				dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
			}
		}
		return dp[n];
	}

	@Test
	public void test1() {

	}

}
