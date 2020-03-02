package bytedance;

import org.junit.Test;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。
 * 这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。
 * 同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <p>
 * 思路：特殊情况是首位相连，所以可以分两种情况讨论
 * 1偷第一间房子，意味着不偷最后一间
 * 2不偷第一间，意味着偷最后一间
 *
 * 而偷房子的方法和198题一样，dp问题，dp[i]表示
 */
public class Medium213 {
    public int rob(int[] nums) {
        if (nums == null) return 0;
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        return Math.max(robDP(nums, 0, n - 2), robDP(nums, 1, n - 1));

    }

    private int robDP(int[] nums, int first, int last) {
        int n = last - first + 1;
        if (n == 0)return 0;
        if (n == 1)return nums[first];
        int[] dp = new int[n];
        dp[0] = nums[first];
        // 注意下标
        dp[1] = Math.max(nums[first], nums[first + 1]);
        for (int i = 2; i < n; i++)
            dp[i] = Math.max(dp[i - 2] + nums[first + i], dp[i - 1]);
        return dp[n - 1];
    }

    @Test
    public void test() {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{1, 2, 3, 4, 5}));
        System.out.println(rob(new int[]{1, 3, 1, 3, 100}));
    }
}
