package bytedance;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 思路：
 * 动态规划：dp[i]表示以i结尾的连续子数组最大和
 * 转移方程为：dp[i]=dp[i-1]+nums[i]，前提dp[i-1]>0；否则dp[i]=nums[i]
 */
public class Easy53 {
    public int maxSubArray(int[] nums) {//方法1：
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {//方法2：对方法1的优化
        int dp = nums[0];
        int max = dp;

        for (int i = 1; i < nums.length; i++) {
            if (dp > 0) {
                dp = dp + nums[i];
            } else {
                dp = nums[i];
            }
            max = Math.max(dp, max);
        }
        return max;

    }

    @Test
    public void test1() {
        System.out.println(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }
}
