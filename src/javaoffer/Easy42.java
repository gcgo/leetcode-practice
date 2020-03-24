package javaoffer;

import org.junit.Test;

/**
 * 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 *
 *思路：动态规划,dp[i]表示以i结尾的子数组的最大值，但是结果不一定是以最后一个元素结尾的子串，所以需要返回dp的最大值
 */
public class Easy42 {

	public int maxSubArray(int[] nums) {
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int res = dp[0];
		for (int i = 1; i < nums.length; i++) {
			dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	/*优化,使用一个变量保存子串最大和即可*/
	public int maxSubArray2(int[] nums) {
		int max = nums[0];
		int res = max;
		for (int i = 1; i < nums.length; i++) {
			max= Math.max(max + nums[i], nums[i]);
			res = Math.max(res, max);
		}
		return res;
	}

	@Test
	public void test1() {
		System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
	}

}
