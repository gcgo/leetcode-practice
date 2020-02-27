package bytedance;

import org.junit.Test;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 * 说明:
 * <p>
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * 思路：动态规划，dp[i][j]表示A从i开始，b从j开始，最长子数组长度。
 * 所以适合从后向前考察，dp[i][j]=A[i]==B[j]?dp[i+1][j+1]+1:0;
 */
public class Medium718 {

    public int findLength(int[] A, int[] B) {
        int ans = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];//多申请一个，值为0，方便计算
        for (int i = A.length - 1; i >= 0; --i) {//从后往前
            for (int j = B.length - 1; j >= 0; --j) {//从后往前
                if (A[i] == B[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                    ans=Math.max(ans,dp[i][j]);
                }//不相等就是0，不用管了。
            }
        }
        return ans;
    }

    @Test
    public void test1() {
        int res = findLength(new int[]{1, 2, 3, 2, 1}, new int[]{3, 2, 1, 4, 7});
        System.out.println(res);
    }
}
