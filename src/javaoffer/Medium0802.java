package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 设想有个机器人坐在一个网格的左上角，网格 r 行 c 列。机器人只能向下或向右移动，
 * 但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为 0 行 0 列。
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释:
 * 输入中标粗的位置即为输出表示的路径，即
 * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
 * 说明：r 和 c 的值均不超过 100。
 *
 * 思路：dfs
 */
public class Medium0802 {
	public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		List<List<Integer>> ansList = new ArrayList<>();
		dfs(obstacleGrid, 0, 0, new boolean[m][n], ansList);
		return ansList;
	}

	private boolean dfs(int[][] grid, int row, int col, boolean[][] visited, List<List<Integer>> pathList) {
		if (row >= grid.length || col >= grid[0].length || grid[row][col] == 1 || visited[row][col]) return false;
		/*添加路径*/
		pathList.add(Arrays.asList(row, col));
		if (row == grid.length - 1 && col == grid[0].length - 1) return true;
		visited[row][col] = true;
		/*向下或向右找，能找到就继续*/
		if (dfs(grid, row + 1, col, visited, pathList) || dfs(grid, row, col + 1, visited, pathList)) {
			return true;
		}
		//否则此路不通，回溯
		pathList.remove(pathList.size() - 1);
		return false;
	}

	@Test
	public void test1() {
		int[][] m = new int[][]{
				{0, 0, 1},
				{0, 1, 0},
				{0, 0, 0}
		};
		int[][] m2 = new int[][]{
				{1}
		};
		System.out.println(pathWithObstacles(m));
		System.out.println(pathWithObstacles(m2));
	}
}
