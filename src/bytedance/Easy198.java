package bytedance;

import org.junit.Test;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 * <p>
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * 思路：动态规划，dp[i]表示到第i家最多可抢多少钱？
 */
public class Easy198 {
    public int rob(int[] nums) {
		int len = nums.length;
		if(len == 0)
			return 0;
		int[] dp = new int[len + 1];//为了满足语义，多申请一个元素，同时不用单独考虑只有2家的情况。
		dp[0] = 0;
		dp[1] = nums[0];
		for(int i = 2; i <= len; i++) {
			dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
		}
		return dp[len];
	}

    @Test
    public void test1() {
		System.out.println(rob(new int[]{2,1,1,2}));
    }

}
