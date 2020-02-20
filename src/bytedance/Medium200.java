package bytedance;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * <p>
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * 输出: 1
 */
public class Medium200 {
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;//行数
        int nc = grid[0].length;//列数

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';//将当前节点标记查找过
        dfs(grid, r - 1, c);//找左边
        dfs(grid, r + 1, c);//找右边
        dfs(grid, r, c - 1);//找上边
        dfs(grid, r, c + 1);//找下边
    }

    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {//遍历所有元素
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);//从该节点开始dfs，并将四周相邻为1的数置0。
                }
            }
        }

        return num_islands;
    }


	public int numIslands2(char[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}

		int nr = grid.length;
		int nc = grid[0].length;
		int num_islands = 0;

		for (int r = 0; r < nr; ++r) {
			for (int c = 0; c < nc; ++c) {
				if (grid[r][c] == '1') {
					++num_islands;
					grid[r][c] = '0'; // 标记已读
					Queue<Integer> neighbors = new LinkedList<>();
					neighbors.add(r * nc + c);
					while (!neighbors.isEmpty()) {
						int id = neighbors.remove();
						int row = id / nc;
						int col = id % nc;
						if (row - 1 >= 0 && grid[row-1][col] == '1') {
							neighbors.add((row-1) * nc + col);
							grid[row-1][col] = '0';
						}
						if (row + 1 < nr && grid[row+1][col] == '1') {
							neighbors.add((row+1) * nc + col);
							grid[row+1][col] = '0';
						}
						if (col - 1 >= 0 && grid[row][col-1] == '1') {
							neighbors.add(row * nc + col-1);
							grid[row][col-1] = '0';
						}
						if (col + 1 < nc && grid[row][col+1] == '1') {
							neighbors.add(row * nc + col+1);
							grid[row][col+1] = '0';
						}
					}
				}
			}
		}

		return num_islands;
	}

    @Test
    public void test1() {
        char[][] island = new char[][]{
				{'1', '1', '1', '1', '0'},
				{'1', '1', '0', '1', '0'},
				{'1', '1', '0', '0', '0'},
				{'0', '0', '0', '0', '1'}
		};
        char[][] island2 = new char[][]{
				{'1','0','1','1','0','1','1'}
		};
        System.out.println(numIslands2(island2));
    }
}
