package bytedance;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 在给定的网格中，每个单元格可以有以下三个值之一：
 * <p>
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，任何与腐烂的橘子（在 4 个正方向上）相邻的新鲜橘子都会腐烂。
 * <p>
 * 返回直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1。
 * 输入：[[2,1,1],[1,1,0],[0,1,1]]
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：[[2,1,1],[0,1,1],[1,0,1]]
 * 输出：-1
 * 解释：左下角的橘子（第 2 行， 第 0 列）永远不会腐烂，因为腐烂只会发生在 4 个正向上。
 * 示例 3：
 * <p>
 * 输入：[[0,2]]
 * 输出：0
 * 解释：因为 0 分钟时已经没有新鲜橘子了，所以答案就是 0 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= grid.length <= 10
 * 1 <= grid[0].length <= 10
 * grid[i][j] 仅为 0、1 或 2
 * <p>
 * 思路：dfs遍历一遍，腐烂橘子能连接的就标记为腐烂。
 */
public class Easy994 {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int count_fresh = 0;
        //腐烂橙子坐标建立数组，入队
        //计算新鲜橙子个数
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    count_fresh++;
                }
            }
        }
        //if count of fresh oranges is zero --> return 0
        if (count_fresh == 0) return 0;
        int count = 0;//记录感染的步数
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //bfs广度优先搜索
        while (!queue.isEmpty() && count_fresh > 0) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();//烂橘子坐标
                for (int[] dir : dirs) {
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    //如果越界了、没有路或者旁边本来就是烂橘子，就跳过
                    if (x < 0 || y < 0 || x >= rows || y >= cols || grid[x][y] == 0 || grid[x][y] == 2) continue;
                    //否则证明是新鲜橘子，变烂
                    grid[x][y] = 2;
                    //把新变烂的橘子坐标入队
                    queue.offer(new int[]{x, y});
                    //新鲜橘子数目减1
                    count_fresh--;
                }
            }
        }
        return count_fresh == 0 ? count : -1;
    }

    @Test
    public void test1() {
        int[][] grid = new int[][]{
                {1, 1, 2, 0, 2}
        };
        System.out.println(orangesRotting(grid));
    }

}
