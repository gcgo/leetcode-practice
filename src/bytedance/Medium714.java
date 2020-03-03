package bytedance;

import org.junit.Test;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每次交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 示例 1:
 *
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 *
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * 思路：动态规划
 * 每一天有两种选择：卖或者不卖，对应两种状态sold[i]第i天卖出后的最大利润、hold[i]第i天持有股票的最大利润（负利润）
 * 对于卖或者不卖，也有两种状态：手上有没有股票
 * 所以状态转移方程为：
 * sold[i] = Math.max(前一天卖出后的利润, 前一天持有的利润加上今天股票的钱减去交易费);
 * hold[i] = Math.max(前一天持有股票的利润, 前一天卖出后手里的钱减去今天的股票价钱);
 */
public class Medium714 {

	public int maxProfit(int[] prices, int fee) {
		int l = prices.length;
		int[] sold = new int[l], hold = new int[l];
		hold[0] = -prices[0];//一上来相当于手里持有的利润
		for (int i = 1; i < prices.length; i++) {
			sold[i] = Math.max(sold[i - 1], hold[i - 1] + prices[i] - fee);
			hold[i] = Math.max(hold[i - 1], sold[i - 1] - prices[i]);
		}
		return sold[l - 1];//返回的是最后一天卖出股票后的钱
	}

	@Test
	public void test1() {
		int res = maxProfit(new int[]{1, 3, 2, 8, 4, 9}, 2);
		System.out.println(res);
	}
}
