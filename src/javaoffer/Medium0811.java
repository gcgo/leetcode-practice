package javaoffer;

import org.junit.Test;

/**
 * 硬币。给定数量不限的硬币，
 * 币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 *
 * 示例1:
 *  输入: n = 5
 *  输出：2
 *  解释: 有两种方式可以凑成总金额:
 * 5=5
 * 5=1+1+1+1+1
 *
 * 示例2:
 *  输入: n = 10
 *  输出：4
 *  解释: 有四种方式可以凑成总金额:
 * 10=10
 * 10=5+5
 * 10=5+1+1+1+1+1
 * 10=1+1+1+1+1+1+1+1+1+1
 * 说明：
 * 注意:
 * 你可以假设：
 * 0 <= n (总金额) <= 1000000
 *
 * 思路：动态规划：dp[i]表示凑i有几种组合，其实对于[1,n]每一个数有几种组合都需要通过减去所有coin最后求和
 */
public class Medium0811 {
	public int waysToChange(int n) {
		int[] dp = new int[n + 1];
		dp[0] = 1;//因为凑1只有一种可能，且dp[1]=dp[0]，所以dp[0]=1;
		int[] coins = new int[]{25, 10, 5, 1};
		for (int coin : coins) {
			/*i应该从1开始，但是i<coin这部分都没意义，所以直接从coin开始*/
			for (int i = coin; i <= n; i++) {
				/*此处是针对一种coin计算，dp需要累加所有coin组合*/
				dp[i] = (dp[i] + dp[i - coin]) % 1000000007;
			}
		}
		return dp[n];
	}

	@Test
	public void test1() {
		System.out.println(waysToChange(10));
	}
}
