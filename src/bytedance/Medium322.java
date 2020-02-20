package bytedance;

import org.junit.Test;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的 最少 的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 * <p>
 * 思路：动态规划：自顶向下思考，自底向上实现！！！！
 */
public class Medium322 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];//表示凑出i最少需要多少硬币
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {//自底向上，从凑1开始
            int cost = Integer.MAX_VALUE;//凑不出来都是integer_Max
            for (int coin : coins) {
                if (i - coin >= 0) {//证明i可以试着用coin凑凑
                    if (dp[i - coin] != Integer.MAX_VALUE)
                        cost = Math.min(cost, dp[i - coin] + 1);//转移方程，凑i-coin需要多少硬币+1（当前这枚coin）
                }
            }
            dp[i] = cost;
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    //**************************************************************************
    @Test
    public void test1() {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
    }

}
