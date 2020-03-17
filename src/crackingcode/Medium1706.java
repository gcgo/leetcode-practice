package crackingcode;

import org.junit.Test;

/**
 *编写一个方法，计算从 0 到 n (含 n) 中数字 2 出现的次数。
 *
 * 示例:
 *
 * 输入: 25
 * 输出: 9
 * 解释: (2, 12, 20, 21, 22, 23, 24, 25)(注意 22 应该算作两次)
 * 提示：
 *
 * n <= 10^9
 *思路：
 *主要思路是数位dp：
 * 以dp[i]表示n的1~i位组成的数字所包含2的个数，关键点在于推导出dp[i]与dp[i-1]的关系
 * 例如：n = 3478
 *
 * dp[1] == numberOf2sInRange(8)
 * dp[2] == numberOf2sInRange(78)
 * dp[3] == numberOf2sInRange(478)
 * dp[4] == numberOf2sInRange(3478)
 *
 * p[i] = f(dp[i-1]) ?
 *
 *
 */
public class Medium1706 {

    public int numberOf2sInRange(int n) {
        if (n == 0) {
            return 0;
        }
        int digit = (int) Math.log10(n) + 1;//计算共有几位数
        int[][] dp = new int[digit + 1][2];
        // dp[i][0] = numberOf2sInRange(n % pow(10, i)) 保存0~n的1-i位组成的数包含2的个数
        // dp[i][1] = numberOf2sInRange(99..9) 保存i位均为9包含2的个数
        dp[1][0] = n % 10 >= 2 ? 1 : 0;
        dp[1][1] = 1;
        for (int i = 2; i <= digit; i++) {
            int k = n / ((int) Math.pow(10, i - 1)) % 10;
            dp[i][0] = k * dp[i - 1][1] + dp[i - 1][0];
            if (k == 2) {
                dp[i][0] += n % (int) Math.pow(10, i - 1) + 1;
            } else if (k > 2) {
                dp[i][0] += (int) Math.pow(10, i - 1);
            }
            dp[i][1] = 10 * dp[i - 1][1] + (int) Math.pow(10, i - 1); //计算1-i位均为9的值包含2的个数
        }
        return dp[digit][0];
    }

    //*********************************************************************************
    @Test
    public void test1() {
        System.out.println(numberOf2sInRange(25));
    }
}
