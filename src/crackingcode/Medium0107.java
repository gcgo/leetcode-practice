package crackingcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一幅由N × N矩阵表示的图像，其中每个像素的大小为4字节，编写一种方法，将图像旋转90度。
 * 不占用额外内存空间能否做到？
 *
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 * 思路：数学算法，顺时针旋转90度：先转置，再每一行逆序。
 */
public class Medium0107 {
	public void rotate(int[][] matrix) {
		/*先转置，*/
		int i = 0, j;
		while (i < matrix.length - 1) {//行是从0到倒数第二行
			j = i + 1;
			while (j < matrix[0].length) {//列是从i+1到最后一列
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
				j++;
			}
			i++;
		}
		/*再倒序*/
		i = 0;
		int col = matrix[0].length;
		while (i < matrix.length) {
			j = 0;
			while (j < col - j - 1) {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][col - j - 1];
				matrix[i][col - j - 1] = tmp;
				j++;
			}
			i++;
		}
	}

	@Test
	public void test1() {
		int[][] matrix = new int[][]{
				{15, 13, 2, 5},
				{14, 3, 4, 1},
				{12, 6, 8, 9},
				{16, 7, 10, 11}
		};
		rotate(matrix);
		System.out.println(Arrays.deepToString(matrix));

	}
}
