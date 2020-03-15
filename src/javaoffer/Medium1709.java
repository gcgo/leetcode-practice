package javaoffer;

import org.junit.Test;

/**
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。
 * 注意，不是必须有这些素因子，而是必须不包含其他的素因子。
 * 例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 * 输入: k = 5
 * 输出: 9
 *
 *
 * 思路：三指针。
 * 实际就是从第一个数开始，后面的数我们都用357从头去乘一遍，取最小的那个，记录这次是用了3、5还是7。
 * 令dp[i]表示第i个数
 *
 */
public class Medium1709 {

    public int getKthMagicNumber(int k) {
        int i1 = 0, i2 = 0, i3 = 0;
        int[] dp = new int[k];
        dp[0] = 1;
        for (int i = 1; i < k; i++) {
            dp[i] = Math.min(dp[i1] * 3, Math.min(dp[i2] * 5, dp[i3] * 7));
            if (dp[i] == dp[i1] * 3)
                i1++;
            if (dp[i] == dp[i2] * 5)
                i2++;
            if (dp[i] == dp[i3] * 7)
                i3++;
        }
        return dp[k - 1];
    }

    //*********************************************************************************
    @Test
    public void test1() {
        System.out.println(getKthMagicNumber(643));
    }
}
