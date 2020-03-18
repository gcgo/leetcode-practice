package bytedance;

import org.junit.Test;

/**
 * 给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，
 * 你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过网格边界。
 * 但是，你最多可以移动 N 次。找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 10^9 + 7 的值。
 *
 * 示例 1：
 * 输入: m = 2, n = 2, N = 2, i = 0, j = 0
 * 输出: 6
 *
 * 示例 2：
 * 输入: m = 1, n = 3, N = 3, i = 0, j = 1
 * 输出: 12
 *
 *思路：dfs
 */
public class Medium576 {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private int mod = 1000000000 + 7;

    public int findPaths(int m, int n, int N, int i, int j) {
        // m * n grid
        //数组表示位置（i,j）最多走N步有几种走法出界？？
        Long[][][] memo = new Long[m][n][N + 1];
        return (int) (dfs(m, n, N, i, j, memo) % mod);
    }

    public long dfs(int m, int n, int N, int i, int j, Long[][][] memo) {
        //check if out of boundary, if out could not move back
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return 1;
        }
        if (N == 0) return 0;
        if (memo[i][j][N] != null) return memo[i][j][N];
        memo[i][j][N] = 0L;
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;
            //动态规划：当前位置可以走N步出界的走法数等于：四面八方可以走N-1步出界的数量之和。
            memo[i][j][N] = (memo[i][j][N] + dfs(m, n, N - 1, x, y, memo) % mod) % mod;
        }
        return memo[i][j][N];
    }

    @Test
    public void test1() {
        System.out.println(findPaths(1, 3, 3, 0, 1));
    }
}
