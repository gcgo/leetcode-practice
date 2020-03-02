package bytedance;

import org.junit.Test;

/**
 * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
 * <p>
 * 示例 1:
 * <p>
 * 输入: amount = 5, coins = [1, 2, 5]
 * 输出: 4
 * 解释: 有四种方式可以凑成总金额:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 示例 2:
 * <p>
 * 输入: amount = 3, coins = [2]
 * 输出: 0
 * 解释: 只用面额2的硬币不能凑成总金额3。
 * 示例 3:
 * <p>
 * 输入: amount = 10, coins = [10]
 * 输出: 1
 *  
 * <p>
 * 注意:
 * <p>
 * 你可以假设：
 * <p>
 * 0 <= amount (总金额) <= 5000
 * 1 <= coin (硬币面额) <= 5000
 * 硬币种类不超过 500 种
 * 结果符合 32 位符号整数
 * <p>
 * 思路：动态规划
 * 假设amount = 12, coins = [1, 2, 5]。
 * 即我们想凑12，假设我最后一枚选的1，问题就变成我要知道12-1=11有多少种凑法；
 * 如果我选的2，问题就是10有多少种凑法；选5，问题就是7有多少种凑法。把这三种凑法加起来，就是凑12有多少种凑法。
 * 显然现在由一个大问题变成三个同样的小问题了。所以设dp[i]表示凑i有几种凑法
 * 递推公式就是dp[i]=dp[i-coin1]+dp[i-coin2]+....+dp[i-coinN]；
 * 前提是当前要凑的面值需要大于coin。
 */
public class Medium518 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];//多申请一个方便计算
        //对于dp[1]来说，即凑1有几种凑法，只能选硬币1，问题变成dp[1-1],即dp[0],显然只有一种凑法。
        //所以为了方便计算，这里设dp[0]=1；
        dp[0] = 1;
        //下面开始计算dp,其实对于[1,amount]每一个数有几种组合都需要通过减去所有coin最后求和
        //所以写代码时就可以每一遍先都减去coin1，第二遍循环再减去coin2，最后减去coinN，把所有coin情况相加，就是dp
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {//i = coin，小于coin的数无法凑。
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    @Test
    public void test1() {
    }
}
