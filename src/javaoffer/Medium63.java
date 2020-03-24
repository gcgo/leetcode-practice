package javaoffer;

import org.junit.Test;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意你不能在买入股票前卖出股票。
 *
 * 思路：
 * 记录最小买入值，遍历价格，比买入值小则更新买入值，否则计算利润，更新最大利润
 *
 */
public class Medium63 {

	public int maxProfit(int[] prices) {
		int minbuy = Integer.MAX_VALUE;
		int profit = 0;
		for (int price : prices) {
			if (price < minbuy) {
				minbuy = price;
			} else {
				profit = Math.max(profit, price - minbuy);
			}
		}
		return profit;
	}

	@Test
	public void test1() {
		System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
		System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
	}
}
