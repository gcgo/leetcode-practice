package bytedance;

import org.junit.Test;

/**
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。
 * 现在你有两个符号 + 和 -。对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3。
 * 注意:
 * <p>
 * 数组非空，且长度不会超过20。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果能被32位整数存下。
 * <p>
 * 思路：动态规划，典型背包问题
 * dp[ i ][ j ]定义为从数组nums中 0 - i 的元素进行加减可以得到 j 的方法数量。
 * dp[ i ][ j ] = dp[ i - 1 ][ j - nums[ i ] ] + dp[ i - 1 ][ j + nums[ i ] ]即前一个元素是加还是减来得到当前元素
 */
public class Medium494 {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (S < -sum || S > sum) return 0;

        int[][] dp = new int[nums.length + 1][2 * sum + 1];
        dp[0][0 + sum] = 1; //将中间这个元素看做是dp[0][0],令它等于1
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                if (j + nums[i - 1] < 2 * sum + 1) dp[i][j] += dp[i - 1][j + nums[i - 1]];
                if (j - nums[i - 1] >= 0) dp[i][j] += dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[nums.length][sum + S];
    }

    @Test
    public void test1() {
    }
}
