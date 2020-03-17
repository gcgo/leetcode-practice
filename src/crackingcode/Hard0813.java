package crackingcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 堆箱子。给你n个箱子，箱子宽 wi、高hi、深di。
 * 箱子不能翻转，将箱子堆起来时，下面箱子的宽度、高度和深度必须大于上面的箱子。实现一种方法，搭出最高的一堆箱子。
 * 箱堆的高度为每个箱子高度的总和。
 *
 * 输入使用数组[wi, di, hi]表示每个箱子。
 *
 * 示例1:
 *  输入：box = [[1, 1, 1], [2, 2, 2], [3, 3, 3]]
 *  输出：6
 * 示例2:
 *  输入：box = [[1, 1, 1], [2, 3, 4], [2, 6, 7], [3, 4, 5]]
 *  输出：10
 * 提示:
 * 箱子的数目不大于3000个。
 *
 * 思路：上面箱子三个指标都得小于下面箱子才行
 * 可以dfs+回溯，把所有情况都列出来，记录最大值。
 * 箱子先排序，优先级宽高深
 */
public class Hard0813 {
	/*dfs穷举超时--------------------------------------------------------------------------------------------*/
	int max = 0;

	public int pileBox(int[][] box) {
		Arrays.sort(box, (n1, n2) -> {
			if (n1[0] != n2[0]) return n1[0] - n2[0];
			if (n1[1] != n2[1]) return n1[1] - n2[1];
			return n1[2] - n2[2];
		});
		for (int i = 0; i < box.length; i++) {
			dfs(box, i, 0);
		}
		return max;
	}

	private void dfs(int[][] box, int start, int tmp) {
		if (start == box.length - 1) {
			tmp += box[start][2];
			max = Math.max(max, tmp);
			return;
		}
		tmp += box[start][2];
		for (int i = start + 1; i < box.length; i++) {
			if (box[start][0] >= box[i][0] || box[start][1] >= box[i][1] || box[start][2] >= box[i][2]) {
				if (i == box.length - 1) max = Math.max(max, tmp);
				continue;
			}
			dfs(box, i, tmp);
		}
		tmp -= box[start][2];
	}

	/*--------------------------------------------dp解法---------------------------------------------------*/
	public static int pileBox2(int[][] box) {
		Arrays.sort(box, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : (a[1] != b[1] ? a[1] - b[1] : a[2] - b[2])));
		/*dp[i]表示以箱子i为最底下的箱子时能堆的最大高度。*/
		int[] dp = new int[box.length];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = box[i][2];
			/*每次都和前面的所有箱子比较，有能放在自己上面的，更新一下最大高度*/
			for (int j = 0; j < i; j++)
				if (box[i][0] > box[j][0] && box[i][1] > box[j][1] && box[i][2] > box[j][2])
					dp[i] = Math.max(dp[i], box[i][2] + dp[j]);
		}
		/*返回dp[]数组的最大值*/
		return Arrays.stream(dp).max().orElse(0);
	}

	@Test
	public void test1() {
		int[][] box = new int[][]{
				{3, 1, 4},
				{10, 16, 15},
				{9, 10, 20},
				{8, 9, 8}
		};
		System.out.println(pileBox(box));//32才对
	}
}
