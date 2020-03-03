package bytedance;

import org.junit.Test;

/**
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 *
 * 示例 1：
 * 输入：nums = [3,6,5,1,8]
 * 输出：18
 * 解释：选出数字 3, 6, 1 和 8，它们的和是 18（可被 3 整除的最大和）。
 *
 * 示例 2：
 * 输入：nums = [4]
 * 输出：0
 * 解释：4 不能被 3 整除，所以无法选出数字，返回 0。
 *
 * 示例 3：
 * 输入：nums = [1,2,3,4,4]
 * 输出：12
 * 解释：选出数字 1, 3, 4 以及 4，它们的和是 12（可被 3 整除的最大和）。
 *  
 * 提示：
 * 1 <= nums.length <= 4 * 10^4
 * 1 <= nums[i] <= 10^4
 * <p>
 * 思路：动态规划：设dp[i]表示除3余i的最大值，假如现在有dp[0]、dp[1]、dp[2],来了一个新数a,
 * 则先看它除3余几？假如a%3=1,那么dp[0]=max(dp[0],dp[2]+a)；以此类推每次都要更新所有dp,很好理解
 */
public class Medium1262 {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];
        for (int num : nums) {
            int mod = num % 3;
            //更新dp前先记录当前dp值,a,b,c是要和num[i]相加，和对应旧值比较的
            //a负责dp[0]、b负责dp[1]、c负责dp[2]
            int a = dp[(3 + 0 - mod) % 3];//mod=0时，a要等于dp[0];mod=1时，a要等于dp[2];mod=2时，a要等于dp[1]
            int b = dp[(3 + 1 - mod) % 3];//mod=0时，b要等于dp[1];mod=1时，b要等于dp[0];mod=2时，b要等于dp[2]
            int c = dp[(3 + 2 - mod) % 3];//mod=0时，c要等于dp[2];mod=1时，c要等于dp[1];mod=2时，c要等于dp[0]
            //因为一开始dp均为0，需要赋初始值，if保证了第一次直接赋值，不用比较
            if (a != 0 || mod == 0) dp[0] = Math.max(dp[0], a + num);
            if (b != 0 || mod == 1) dp[1] = Math.max(dp[1], b + num);
            if (c != 0 || mod == 2) dp[2] = Math.max(dp[2], c + num);
        }
        return dp[0];
    }

    @Test
    public void test() {
        int[] arr = new int[]{3, 6, 5, 1, 8};
        System.out.println(maxSumDivThree(arr));
    }
}
