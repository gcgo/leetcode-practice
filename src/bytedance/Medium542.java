package bytedance;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 *
 * 两个相邻元素间的距离为 1 。
 *
 * 示例 1:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * 示例 2:
 * 输入:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * 注意:
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 *
 * <p>
 * 思路：bfs，先将矩阵中为0的位置都加入队列（x，y坐标，以长度为2的数组形式），
 * 并设置一个dir={{-1, 0}, {1, 0}, {0, -1}, {0, 1}}的数组用于方便求当前点的上下左右点的坐标。
 * 当队列不为空时，拿出每个当前点A，遍历他的左右上下四个点，
 * 若遍历过程中出现越过矩阵边界则直接跳过，
 * 或者出现该点（A点上下左右四个点中的一个）的值小于A+1，表明该点的最近0的路径非此条路径，也无需更新，直接跳过；
 * 否则即该点大于A+1，证明原来的路径不如当前这条，更新该点的值为A点的值+1，并将该点也加入队列。
 * 一直迭代到队列为空即可
 */
public class Medium542 {
    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        //灵活应对四个方向的变化
        int[][] vector = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};//右左下上
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    // 将所有 0 元素作为 BFS 第一层
                    queue.add(new int[]{i, j});
                } else {
                    //设一个最大值
                    matrix[i][j] = row + col;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] s = queue.poll();
            // 搜索上下左右四个方向
            for (int[] v : vector) {
                int r = s[0] + v[0];
                int c = s[1] + v[1];
                if (r >= 0 && r < row && c >= 0 && c < col) {
                    if (matrix[r][c] >= matrix[s[0]][s[1]] + 1) {
                        matrix[r][c] = matrix[s[0]][s[1]] + 1;
                        queue.add(new int[]{r, c});
                    }
                }
            }
        }
        return matrix;
    }

    @Test
    public void test1() {
        int[][] m = new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {1, 1, 1}
        };
        System.out.println(Arrays.deepToString(updateMatrix(m)));
    }
}
