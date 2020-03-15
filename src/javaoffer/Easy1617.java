package javaoffer;

import org.junit.Test;

/**
 *给定一个整数数组（有正数有负数），找出总和最大的连续数列，并返回总和。
 *
 * 示例：
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶：
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * 思路：动态规划，dp[i]以i结尾的最大连续和
 * dp[i]=要么接着加，有么重头来，看哪个大就进行哪个
 * dp[0]=nums[0];
 */
public class Easy1617 {
	public int maxSubArray(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int res = dp[0];
		for (int i = 1; i < dp.length; i++) {
			dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	/*dp用一个变量保存即可*/
	public int maxSubArray2(int[] nums) {
		int dp=nums[0];//用来计算累加和
		int max=dp;
		for (int i = 1; i < nums.length; i++) {
			dp=Math.max(nums[i],dp+nums[i]);
			max=Math.max(dp, max);
		}
		return max;
	}

	@Test
	public void test1() {
		System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
	}
}
