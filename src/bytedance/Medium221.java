package bytedance;

import org.junit.Test;

/**
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * <p>
 * 输出: 4
 * <p>
 * 思路：动态规划，不太好理解
 * dp[i][j]为矩阵中“以matrix[i,j]为右下角的正方形边长”，
 * 它受限于分别以“左边、左上、右边”元素为右下角的正方形边长，取他们三个的最小值。
 * 这么理解：以matrix[i,j]为右下角的话，相当于引入了两条边，现在就要看看这两条边能有多长，
 * 这就取决于沿着这两条边之前的正方形边长能有多长。同时还得看这两条边围成的面积，是否能填满。
 * 所以边长受三个因素制约，木桶原理，取最小值作为新正方形的边长。
 */
public class Medium221 {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];//多申请一行一列，不用单独考虑边界条件了
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);//记录最大边长
                }
            }
        }
        return maxsqlen * maxsqlen;//返回面积
    }

    @Test
    public void test1() {

    }
}
