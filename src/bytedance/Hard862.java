package bytedance;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 * <p>
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1], K = 1
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：A = [1,2], K = 4
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：A = [2,-1,2], K = 3
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 * <p>
 * 思路：单调栈存储累加和
 */
public class Hard862 {
    public int shortestSubarray(int[] A, int K) {
        int N = A.length;
        long[] dp = new long[N + 1];//dp[i]表示A[]中前i个元素累加。
        for (int i = 0; i < N; ++i) dp[i + 1] = dp[i] + (long) A[i];
        int ans = N + 1;//长度不可能为N+1；
        Deque<Integer> stack = new LinkedList<>(); //单调栈思想，实际是双端队列，栈中元素单调递增。
        for (int j = 0; j < dp.length; ++j) {
            while (!stack.isEmpty() && dp[j] <= dp[stack.getLast()])//从尾部开始比，队列中存的是下标，
                // 保证队列中对应的累加和的单调性
                stack.removeLast();
            while (!stack.isEmpty() && dp[j] >= dp[stack.getFirst()] + K)//从头开始比，即累加和最小的
                // 如果当前累加和与队列中最小的累加和相差至少为K，则更新长度
                ans = Math.min(ans, j - stack.removeFirst());//更新，注意这里比较和删除都是从头部开始了

            stack.addLast(j);
        }
        return ans < N + 1 ? ans : -1;
    }

    @Test
    public void test1() {

    }
}
