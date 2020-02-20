package bytedance;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * <p>
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * <p>
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例:
 * <p>
 * 输入: 4
 * 输出: [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * <p>
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * <p>
 * 思路：皇后可以攻击同⼀⾏、 同⼀列、 左上左下右上右下四个⽅向的任意单位。
 * 所以从第一行开始放置Q，然后到下一行判断哪些位置能放置Q，以此类推。
 */
public class Hard51 {

    List<List<String>> res;//全局变量，也可以dfs参数传递

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return null;
        res = new LinkedList<>();
        char[][] board = new char[n][n];//棋盘
        for (char[] chars : board) Arrays.fill(chars, '.');//初始化棋盘
        dfs(board, 0);
        return res;
    }

    /**
     * 路径：board中小于row的那些行都已经成功放置了皇后
     * 可选择列表: 第row行的所有列都是放置Q的选择
     * 结束条件: row超过board的最后一行
     *
     * @param board
     * @param row
     */
    private void dfs(char[][] board, int row) {
        if (row == board.length) {//走完最后一行
            res.add(charToString(board));//添加结果
            return;
        }
        int n = board[row].length;
        for (int col = 0; col < n; col++) {//检查每一列
            if (!isValid(board, row, col)) continue;//剪枝
            board[row][col] = 'Q';//证明该位置可以放一个Q
            dfs(board, row + 1);//dfs下一行
            board[row][col] = '.';//回溯
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        int rows = board.length;
        // 检查该列
        for (char[] chars : board) if (chars[col] == 'Q') return false;
        // 检查右上
        for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        // 检查左上
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private static List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }


    //*********************************************************************************
    @Test
    public void test1() {

    }
}
