package bytedance;

import org.junit.Test;

/**
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * <p>
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为 [1, 2, 6, 9]。
 * 示例 2:
 * <p>
 * 输入: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * <p>
 * 思路：dfs,用一个cache矩阵记录计算过的点的路径长度。
 */
public class Hard329 {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int res = 1;
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int n = dfs(matrix, i, j, cache);
                res = Math.max(res, n);
            }
        }
        return res;
    }

    private int dfs(int[][] matrix, int row, int col, int[][] cache) {
        if (row < 0 || row > matrix.length - 1 || col < 0 || col > matrix[0].length - 1) return 0;
        //如果之前这个位置已经计算过了,则直接返回
        if (cache[row][col] != 0) return cache[row][col];
        int up = 0, down = 0, left = 0, right = 0;
        if (col < matrix[0].length - 1 && matrix[row][col + 1] > 0 && matrix[row][col + 1] > matrix[row][col]) {//右
            right = dfs(matrix, row, col + 1, cache);
        }
        if (row < matrix.length - 1 && matrix[row + 1][col] > 0 && matrix[row + 1][col] > matrix[row][col]) {//下
            down = dfs(matrix, row + 1, col, cache);
        }
        if (col > 0 && matrix[row][col - 1] > 0 && matrix[row][col - 1] > matrix[row][col]) {//左
            left = dfs(matrix, row, col - 1, cache);
        }
        if (row > 0 && matrix[row - 1][col] > 0 && matrix[row - 1][col] > matrix[row][col]) {//上
            up = dfs(matrix, row - 1, col, cache);
        }
        int max = Math.max(Math.max(right, down), Math.max(left, up));
        cache[row][col] = max > 0 ? 1 + max : 1;
        return cache[row][col];
    }

    @Test
    public void test1() {
        int[][] matrix = new int[][]{
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };
        int[][] matrix2 = new int[][]{
                {9, 9, 4, 1},
        };

        System.out.println(longestIncreasingPath(matrix));
    }
}
