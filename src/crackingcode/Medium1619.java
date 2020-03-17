package crackingcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。
 * 若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。
 * 池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 *
 * 示例：
 * 输入：
 * [
 *   [0,2,1,0],
 *   [0,1,0,1],
 *   [1,1,0,1],
 *   [0,1,0,1]
 * ]
 * 输出： [1,2,4]
 * 提示：
 * 0 < len(land) <= 1000
 * 0 < len(land[i]) <= 1000
 *
 * 思路：dfs
 *
 */
public class Medium1619 {
	public int[] pondSizes(int[][] land) {
		List<Integer> list = new ArrayList<>();
		boolean[][] flag = new boolean[land.length][land[0].length];
		for (int i = 0; i < land.length; i++)
			for (int j = 0; j < land[0].length; j++)
				if (land[i][j] == 0 && !flag[i][j])
					list.add(dfs(land, flag, i, j));
		Collections.sort(list);
		return list.stream().mapToInt(Integer::valueOf).toArray();
	}

	private int dfs(int[][] land, boolean[][] flag, int x, int y) {
		if (x < 0 || y < 0 || x >= land.length || y >= land[0].length || flag[x][y] || land[x][y] != 0)
			return 0;
		flag[x][y] = true;
		return 1 + dfs(land, flag, x + 1, y)
				+ dfs(land, flag, x - 1, y)
				+ dfs(land, flag, x, y + 1)
				+ dfs(land, flag, x, y - 1)
				+ dfs(land, flag, x + 1, y + 1)
				+ dfs(land, flag, x + 1, y - 1)
				+ dfs(land, flag, x - 1, y + 1)
				+ dfs(land, flag, x - 1, y - 1);
	}

	@Test
	public void test1() {

	}
}
