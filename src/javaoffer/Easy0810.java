package javaoffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 颜色填充。编写函数，实现许多图片编辑软件都支持的“颜色填充”功能。
 * 给定一个屏幕（以二维数组表示，元素为颜色值）、一个点和一个新的颜色值，将新颜色值填入这个点的周围区域，直到原来的颜色值全都改变。
 *
 * 示例1:
 *
 *  输入：
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 *  输出：[[2,2,2],[2,2,0],[2,0,1]]
 *  解释:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 * 说明：
 *
 * image 和 image[0] 的长度在范围 [1, 50] 内。
 * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
 * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
 *
 *
 * 思路：带传染的一般都用bfs,这个题目要求叙述的真沙雕，他意思是，和[sr][sc]颜色一样的才变成新颜色，和01无关
 */
public class Easy0810 {
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[image.length][image[0].length];
		int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int ref = image[sr][sc];
		image[sr][sc] = newColor;
		isVisited[sr][sc] = true;
		queue.offer(new int[]{sr, sc});
		while (!queue.isEmpty()) {
			int[] pix = queue.poll();
			for (int[] dir : direction) {
				int row = pix[0] + dir[0];
				int col = pix[1] + dir[1];
				if (row == image.length || row < 0 || col == image[0].length || col < 0 || isVisited[row][col]
						|| image[row][col] != ref)
					continue;
				image[row][col] = newColor;
				isVisited[row][col] = true;
				queue.offer(new int[]{row, col});
			}
		}
		return image;
	}

	@Test
	public void test1() {
		int[][] m = new int[][]{
				{1, 1, 1},
				{1, 0, 0},
				{1, 0, 1}
		};

		floodFill(m, 1, 1, 2);
		System.out.println(Arrays.deepToString(m));
	}
}
