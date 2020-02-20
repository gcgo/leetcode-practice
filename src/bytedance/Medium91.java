package bytedance;

import org.junit.Test;

/**
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 * <p>
 * 思路：动态规划
 */
public class Medium91 {
    public int numDecodings(String s) {
        if (s.isEmpty() || s.charAt(0) == '0') return 0;
        int len = s.length();
        int[] dp = new int[len + 1];//dp[i]表示前i个字符串能有多少个解码，多申请一个是为了不用单独处理边界情况。
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            if (s.charAt(i - 1) != '0')
                dp[i] = dp[i - 1];
            if (i > 1 && (s.charAt(i - 2) == '1' || s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7'))
                dp[i] += dp[i - 2];
        }
        return dp[len];
    }

    @Test
    public void test1() {
    }

}
