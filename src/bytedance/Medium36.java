package bytedance;

import org.junit.Test;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 示例 1:
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 输入:
 * [
 *   ["5","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 *
 * 思路：可以使用 box_index = (row / 3) * 3 + columns / 3，来区分九宫格
 * 创建一个内部类表示元素，重写hashcode和equals方法
 * set在添加对象时，先判断有无hash冲突，有的话调用equals方法判断对象是否相等
 * 所以就让hashcode只关联数字即可，equals判断是否在同一行同一列同一box，是就重复。
 *
 */
public class Medium36 {

    //创建一个静态内部类
    static class Point {
        int row;
        int column;
        char s;

        private Point(int row, int column, char s) {
            this.row = row;
            this.s = s;
            this.column = column;
        }


        @Override
        public boolean equals(Object o) {
            if (getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return row == point.row ||
                    column == point.column ||
                    (point.row / 3 == row / 3 && point.column / 3 == column / 3);
        }

        @Override
        public int hashCode() {
            return Objects.hash(s);
        }
    }
    public boolean isValidSudoku(char[][] board) {
        Set<Point> set = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '.') continue;
                Point point = new Point(i, j, board[i][j]);
                if (set.contains(point)) return false;
                else set.add(point);
            }
        }
        return true;
    }

    @Test
    public void test1() {

    }
}
