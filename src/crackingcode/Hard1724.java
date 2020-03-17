package crackingcode;

import org.junit.Test;

/**
 * 给定一个正整数和负整数组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 *
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。
 * 若有多个满足条件的子矩阵，返回任意一个均可。
 *
 * 注意：本题相对书上原题稍作改动
 *
 * 示例:
 *
 * 输入:
 * [
 *    [-1,0],
 *    [0,-1]
 * ]
 * 输出: [0,1,0,1]
 * 解释: 输入中标粗的元素即为输出所表示的矩阵
 * 说明：
 *
 * 1 <= matrix.length, matrix[0].length <= 200
 *
 *
 * 思路：将一个二维数组的纵向元素相加，变成一个横向上的一维数组，就能使用一维的方法来解决这个问题了
 * 一位数组状态转移方程：dp[n] = max(dp[n-1]+a[n],a[n])
 *
 */
public class Hard1724 {
	private int max = Integer.MIN_VALUE;
	private int[] res = new int[4];
	private int[][] pre_sum;

	public int[] getMaxMatrix(int[][] matrix) {
		int row = matrix.length;
		int matrix_col = matrix[0].length;
		pre_sum = new int[row + 1][matrix_col];
		/*计算列的累加和，结果存在新数组里
		 * pre_sum第一行是0*/
		for (int j = 0; j < matrix_col; ++j)
			for (int i = 1; i <= row; ++i)
				pre_sum[i][j] = matrix[i - 1][j] + pre_sum[i - 1][j];
		/*先以第一行为顶，依次考察下面的行
		 * 再以第二行为顶。。。*/
		for (int i = 0; i < row; i++) {
			for (int j = i + 1; j <= row; j++) {
				/*compute函数是在列上扩展*/
				compute(i, j);
			}
		}
		return res;
	}

	private void compute(int i, int j) {
		int sum = 0, c1 = 0, c2 = 0;
		for (; c2 < pre_sum[0].length; c2++) {
			//从第一列向右扩展累加
			sum += pre_sum[j][c2] - pre_sum[i][c2];

			if (sum > max) {
				res[0] = i;
				res[1] = c1;
				res[2] = j - 1;
				res[3] = c2;
				max = sum;
			}
			if (sum < 0) {//重新计数，更新左边界
				sum = 0;
				c1 = c2 + 1;
			}
		}
	}

	@Test
	public void test1() {

	}
}
