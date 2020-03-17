package crackingcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 设计一种算法，打印 N 皇后在 N × N 棋盘上的各种摆法，
 * 其中每个皇后都不同行、不同列，也不在对角线上。这里的“对角线”指的是所有的对角线，不只是平分整个棋盘的那两条对角线。
 * 注意：本题相对原题做了扩展
 * 示例:
 *  输入：4
 *  输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *  解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 * 思路：皇后可以攻击同⼀⾏、 同⼀列、 左上左下右上右下四个⽅向的任意单位。
 *   所以从第一行开始放置Q，然后到下一行判断哪些位置能放置Q，以此类推。
 */
public class Hard0812 {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> res = new ArrayList<>();
		if (n == 0) return res;
		/*构造棋盘*/
		char[][] board = new char[n][n];
		for (char[] rows : board) {
			Arrays.fill(rows, '.');
		}
		/*dfs*/
		dfs(res, board, 0);
		return res;
	}

	private void dfs(List<List<String>> res, char[][] board, int row) {
		if (row == board.length) {
			res.add(charToString(board));
			return;
		}
		for (int i = 0; i < board[row].length; i++) {
			if (!isValid(board, row, i)) continue;
			board[row][i] = 'Q';
			dfs(res, board, row + 1);
			board[row][i] = '.';
		}
	}

	private List<String> charToString(char[][] board) {
		List<String> res = new ArrayList<>();
		for (char[] chars : board) {
			res.add(String.valueOf(chars));
		}
		return res;
	}

	private boolean isValid(char[][] board, int row, int col) {
		/*检查该列*/
		for (char[] oneRow : board) if (oneRow[col] == 'Q') return false;
		/*检查左上*/
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 'Q') return false;
		}
		/*检查右上*/
		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j] == 'Q') return false;
		}
		/*到这说明没问题*/
		return true;
	}

	@Test
	public void test1() {
		System.out.println(solveNQueens(4));
	}
}
