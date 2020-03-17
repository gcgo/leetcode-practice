package crackingcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
 *
 * 示例 1：
 * 输入：
 * [
 *   [1,1,1],
 *   [1,0,1],
 *   [1,1,1]
 * ]
 * 输出：
 * [
 *   [1,0,1],
 *   [0,0,0],
 *   [1,0,1]
 * ]
 *
 * 示例 2：
 * 输入：
 * [
 *   [0,1,2,0],
 *   [3,4,5,2],
 *   [1,3,1,5]
 * ]
 * 输出：
 * [
 *   [0,0,0,0],
 *   [0,4,5,0],
 *   [0,3,1,0]
 * ]
 *
 * 思路：先遍历一遍记录所有0的位置，然后对每一个零所在行列非零位置清零。
 */
public class Medium0108 {
	public void setZeroes(int[][] matrix) {
		Queue<int[]> queue = new LinkedList<>();//存零点坐标
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) queue.offer(new int[]{i, j});
			}
		}
		while (!queue.isEmpty()) {
			int[] zero = queue.poll();
			/*行清零*/
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i][zero[1]] != 0) matrix[i][zero[1]] = 0;
			}
			/*列清零*/
			for (int i = 0; i < matrix[0].length; i++) {
				if (matrix[zero[0]][i] != 0) matrix[zero[0]][i] = 0;
			}
		}
	}

	@Test
	public void test1() {
		int[][] matrix = new int[][]{
				{0, 1, 2, 0},
				{3, 4, 5, 2},
				{1, 3, 1, 5}
		};
		setZeroes(matrix);
		System.out.println(Arrays.deepToString(matrix));

	}
}
