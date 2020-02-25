package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 * <p>
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 * <p>
 * 示例:
 * <p>
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * <p>
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * <p>
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 * <p>
 * 思路：dfs,从边界上的O开始考察，有相邻也是O的话，标记为isSafe
 * 最后再遍历一遍，把不安全的O改为X
 */
public class Medium130 {
    public void solve(char[][] board) {
        if (board.length == 0) return;
        //标记安全的0
        boolean[][] isSafe = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i != 0 && i != board.length - 1 && j != 0 & j != board[0].length - 1) continue;//剪枝
                if (board[i][j] == 'X') continue;//剪枝
                dfs(board, isSafe, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {//检查结果
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O' && !isSafe[i][j]) board[i][j] = 'X';
            }
        }
    }

    private void dfs(char[][] board, boolean[][] isSafe, int row, int col) {
        if (row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 ||
                isSafe[row][col] || board[row][col] == 'X') return;
        isSafe[row][col] = true;
        dfs(board, isSafe, row, col + 1);
        dfs(board, isSafe, row + 1, col);
        dfs(board, isSafe, row, col - 1);
        dfs(board, isSafe, row - 1, col);
    }

    @Test
    public void test1() {
        char[][] m = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        solve(m);
        System.out.println(Arrays.deepToString(m));
    }
}
