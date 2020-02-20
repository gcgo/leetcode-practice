package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * <p>
 * 示例:
 * <p>
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * <p>
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?.............不能
 * <p>
 * 思路：动态规划
 * dp[i]表示以i结尾的数组的最长上升子序列长度
 * 初始值就是以自己为单位的子串，所以为1
 * 变量i遍历数组元素
 * 变量j检查0到i-1的元素，每一个都去和nums【i】比较，两种情况：
 * 第一种情况，nums[j]<nums[i],证明j和i可以组成一个递增子串，所以dp[i]=dp[j] + 1,而对于所有j，会有一个j使得dp[j] + 1最大，
 * 所以dp[i] = Math.max(dp[i], dp[j] + 1);
 * 第二种情况，nums[j]>nums[i],说明j和i不能组成递增子串，nums[i]从自己开始，所以dp[i]=1；
 * 遍历完以后，我们取dp数组的最大值，即最大递增子串的长度
 */
public class Medium300 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        for (int n : dp) res = Math.max(n, res);
        return res;
    }

    //**************************************************************************
    @Test
    public void test1() {
//        System.out.println(lengthOfLIS(new int[]{10, 9, 2, 5, 1, 3, 7, 21, 18}));
        System.out.println(lengthOfLIS(new int[]{1, 3, 6, 7, 9, 4, 10, 5, 6}));
    }

}
