package javaoffer;

import org.junit.Test;

/**
 * 设计一个算法，判断玩家是否赢了井字游戏。输入是一个 N x N 的数组棋盘，由字符" "，"X"和"O"组成，其中字符" "代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符"O"，且第二个玩家总是放字符"X"。
 * "X"和"O"只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有N个相同（且非空）的字符填充任何行、列或对角线时，游戏结束，对应该字符的玩家获胜。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 如果游戏存在获胜者，就返回该游戏的获胜者使用的字符（"X"或"O"）；
 * 如果游戏以平局结束，则返回 "Draw"；如果仍会有行动（游戏未结束），则返回 "Pending"。
 *
 * 示例 1：
 * 输入： board = ["O X"," XO","X O"]
 * 输出： "X"
 *
 * 示例 2：
 * 输入： board = ["OOX","XXO","OXO"]
 * 输出： "Draw"
 * 解释： 没有玩家获胜且不存在空位
 *
 * 示例 3：
 * 输入： board = ["OOX","XXO","OX "]
 * 输出： "Pending"
 * 解释： 没有玩家获胜且仍存在空位
 * 提示：
 *
 * 1 <= board.length == board[i].length <= 100
 * 输入一定遵循井字棋规则
 *
 * 思路：就是五子棋规则
 *
 */
public class Medium1604 {
	public String tictactoe(String[] board) {
		int n = board.length;
		char[][] grid = new char[n][n];
		for (int i = 0; i < n; i++) {
			grid[i] = board[i].toCharArray();
		}
		int empty = 0;//统计空位数
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == ' ') empty++;
			}
		}
		//空位数大于零
		if (empty > 0) {
			if (check(grid, 'X')) return "X";
			if (check(grid, 'O')) return "O";
			return "Pending";
		}
		//空位数等于零
		if (check(grid, 'X')) return "X";
		if (check(grid, 'O')) return "O";
		return "Draw";
	}

	private boolean check(char[][] grid, char c) {
		int n = grid.length;
		// 检查行
		for (int row = 0; row < n; row++) {
			int col;
			for (col = 0; col < n; col++) {
				if (grid[row][col] != c) break;
			}
			if (col >= n) return true;
		}
		// 检查列
		for (int col = 0; col < n; col++) {
			int row;
			for (row = 0; row < n; row++) {
				if (grid[row][col] != c) break;
			}
			if (row >= n) return true;
		}
		// 主对角线
		int i;
		for (i = 0; i < n; i++) {
			if (grid[i][i] != c) break;
		}
		if (i >= n) return true;
		// 副对角线
		for (i = 0; i < n; i++) {
			if (grid[i][n - 1 - i] != c) break;
		}
		return i >= n;
	}

	@Test
	public void test1() {
	}
}
