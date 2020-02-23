package bytedance;

import org.junit.Test;

/**
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 * <p>
 * 思路：动态规划，dp[i]意思是数字i的最少完全平方数组合
 * 转移方程就是，dp[i]=min(dp[i],dp[i-完全平方数]+1)
 */
public class Medium279 {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = i;//默认全由1组成，所以就是i个
            for (int j = 1; i>=j*j; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j]+1);
            }
        }
        return dp[n];
    }

    @Test
    public void test1() {
        System.out.println(numSquares(12));
    }
}
