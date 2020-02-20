package bytedance;

import org.junit.Test;

/**
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * <p>
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 * <p>
 * 思路：dfs回溯
 */
public class Easy79 {
    public boolean exist(char[][] board, String word) {
        int row = board.length, col = board[0].length;
        boolean[][] visited = new boolean[row][col];//状态记录容器
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {//从所有元素开始一边查找
                if (dfs(board, i, j, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board   字母表
     * @param i       起始位置
     * @param j       起始位置
     * @param word    要查找的单词
     * @param index   当前所找的单词字母位置
     * @param visited 状态记录
     * @return 表中是否存在单词
     */
    public boolean dfs(char[][] board, int i, int j, String word, int index, boolean[][] visited) {

        if (index == word.length()) {//dfs跳出条件
            return true;
        }
        int row = board.length, col = board[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] != word.charAt(index) || visited[i][j]) {
            return false;//↑↑↑↑↑↑↑↑↑↑↑↑↑↑越界条件↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
        }
        visited[i][j] = true;

        boolean temp =
                dfs(board, i + 1, j, word, index + 1, visited) ||//往下找
                        dfs(board, i - 1, j, word, index + 1, visited) ||//往上找
                        dfs(board, i, j + 1, word, index + 1, visited) ||//往右找
                        dfs(board, i, j - 1, word, index + 1, visited);//往左找，这些顺序无所谓
        visited[i][j] = false;//都没找到则状态退回，状态恢复，即回溯
        return temp;
    }

    @Test
    public void test1() {
        boolean exist = exist(new char[][]{
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}},
                "ABCCED");
        System.out.println(exist);
    }
}
