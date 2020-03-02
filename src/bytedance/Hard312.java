package bytedance;

import org.junit.Test;

/**
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 
 * 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 说明:
 * <p>
 * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * <p>
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * <p>
 * 思路：回溯，备忘录剪枝
 * [1...3,1,5,8...1]
 *首先我们选一个气球，最后扎爆它。比如我们选5，那么问题化为三个子问题，即：1,3,1 和  1,5,1 和  8,1
 * 假设f(start, end)表示从第start到end个气球的最大得分，nums[i]表示气球上的值，nums[start-1]和nums[end+1]是“虚拟气球”则有：
 * f(start, end) = max{f(start,i-1) + nums[start-1]*nums[i]*nums[end+1] + f(i+1,end) ,其中i取值为[start,end]}
 * 递归结束条件是，start>end。
 * 递归时，我们可以将f[start,end]的值通过二维数组缓存起来。
 *
 */
public class Hard312 {
    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        nums[0] = nums[n++] = 1;//补全数组

        int[][] memo = new int[n][n];//备忘录
        return burst(memo, nums, 0, n - 1);
    }

    public int burst(int[][] memo, int[] nums, int left, int right) {
        if (left + 1 == right) return 0;
        if (memo[left][right] > 0) return memo[left][right];
        int ans = 0;
        for (int i = left + 1; i < right; ++i)
            ans = Math.max(ans, nums[left] * nums[i] * nums[right]//注意num[i]代表保留的气球
                    + burst(memo, nums, left, i) + burst(memo, nums, i, right));
        memo[left][right] = ans;
        return ans;
    }

    @Test
    public void test1() {
        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));
    }
}
