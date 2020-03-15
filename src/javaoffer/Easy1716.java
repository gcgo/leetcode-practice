package javaoffer;

import org.junit.Test;

/**
 * 一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。
 * 在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。
 * 给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例 1：
 * 输入： [1,2,3,1]
 * 输出： 4
 * 解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
 * 示例 2：
 * 输入： [2,7,9,3,1]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
 * 示例 3：
 * 输入： [2,1,4,5,3,1,1,3]
 * 输出： 12
 * 解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
 *
 * 思路：动态规划
 * dp[i]表示截止第i天接单的总时间
 * dp[i]=要么第i天接单，=dp[i-2]+nums[i],要么今天不接单，=dp[i-1],选最大的那个
 *
 */
public class Easy1716 {

	public int massage(int[] nums) {
		if (nums.length == 0) return 0;
		int[] dp = new int[nums.length + 1];
		dp[0] = 0;//为了方便计算dp[2]而已
		dp[1] = nums[0];
		for (int i = 2; i < dp.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
		}
		return dp[nums.length];
	}

	@Test
	public void test() {
		System.out.println(massage(new int[]{2, 7, 9, 3, 1}));
	}

}

