package javaoffer;

import org.junit.Test;

/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 *思路：动态规划，dp[i][j]表示到达i,j的最大和。
 * dp[i][j]等于要么是从上边加过来，要么是从左边加过来
 * 注意处理边界条件
 *
 */
public class Medium47 {
	public int maxValue(int[][] grid) {
		int r = grid.length;
		int c = grid[0].length;
		int[][] dp = new int[r][c];
		dp[0][0] = grid[0][0];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0) continue;
				if (i == 0) dp[i][j] = dp[i][j - 1] + grid[i][j];
				else if (j == 0) dp[i][j] = dp[i - 1][j] + grid[i][j];
				else dp[i][j] = grid[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[r - 1][c - 1];
	}

	@Test
	public void test1() {

	}

}
