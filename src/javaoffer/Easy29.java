package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 *
 * 思路：上下左右四个边界逐渐往里收缩
 *
 */
public class Easy29 {

	public int[] spiralOrder(int[][] matrix) {
		if(matrix.length == 0) return new int[0];
		int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1, ind = 0;
		int[] res = new int[(r + 1) * (b + 1)];
		while(true) {
			for(int i = l; i <= r; i++) res[ind++] = matrix[t][i]; // left to right.
			if(++t > b) break;
			for(int i = t; i <= b; i++) res[ind++] = matrix[i][r]; // top to bottom.
			if(l > --r) break;
			for(int i = r; i >= l; i--) res[ind++] = matrix[b][i]; // right to left.
			if(t > --b) break;
			for(int i = b; i >= t; i--) res[ind++] = matrix[i][l]; // bottom to top.
			if(++l > r) break;
		}
		return res;
	}

	@Test
	public void test1() {
		int[][] matrix = new int[][]{
				{1, 2, 3, 4},
				{5, 6, 7, 8},
				{9, 10, 11, 12}
		};
		System.out.println(Arrays.toString(spiralOrder(matrix)));
	}
}
