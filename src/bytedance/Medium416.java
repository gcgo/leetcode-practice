package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 思路：01背包问题：问题转化
 * 是否有两个和相等的子集，就是说是否有一个集合和为target=sum/2。
 * 令dp[i]表示有集合能凑齐i。
 * 则dp[i]=dp[target-nums[i]]
 *
 */
public class Medium416 {
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        if ((sum & 1) == 1) return false;
        int target = sum >>> 1;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = target; i >= num; i--) {
                if (dp[i - num])//如果能凑齐i-num，那么i-num+num=i,即可以凑齐i
                    dp[i] = true;
            }
        }
        return dp[target];
    }

    //**************************************************************************
    @Test
    public void test1() {
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }

}
