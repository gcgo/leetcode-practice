package bytedance;

import org.junit.Test;

/**
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * <p>
 * 将图像顺时针旋转 90 度。
 * <p>
 * 说明：
 * <p>
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * <p>
 * 示例 1:
 * <p>
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * <p>
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 *
 * 思路：
 * 顺时针90度方法：首先对原数组取其转置矩阵，然后把每行的数字翻转可得到结果
 */
public class Medium48 {
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {//先转置，就是以左对角线翻转
            for (int j = i; j < matrix[i].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
            int l = 0;
            int r = matrix[i].length - 1;
            while (l < r) {//再把改行元素左右翻转
                int tmp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = tmp;
                r--;
                l++;
            }
        }
    }

    @Test
    public void test1() {
        int[][] res = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(res);
        System.out.println("wdwd");
    }
}
