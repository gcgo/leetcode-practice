package bytedance;

import org.junit.Test;

import java.util.HashSet;

/**
 * 给定一个非空01二维数组表示的网格，一个岛屿由四连通（上、下、左、右四个方向）的 1 组成，你可以认为网格的四周被海水包围。
 *
 * 请你计算这个网格中共有多少个形状不同的岛屿。两个岛屿被认为是相同的，
 * 当且仅当一个岛屿可以通过平移变换（不可以旋转、翻转）和另一个岛屿重合。
 *
 * 样例 1:
 *
 * 11000
 * 11000
 * 00011
 * 00011
 * 给定上图，返回结果 1。
 *
 * 样例 2:
 * 11011
 * 10000
 * 00001
 * 11011
 * 给定上图，返回结果3
 * 注意:
 * 11
 * 1
 * 和
 *  1
 * 11
 * 是不同的岛屿，因为我们不考虑旋转、翻转操作。
 *
 * 思路：dfs找岛屿，然后上色，都存在set里，过滤重复岛屿
 */
public class Medium694 {
    public int numDistinctIslands(int[][] grid) {
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0)
                    continue;
                StringBuilder sb = new StringBuilder();
                dfs(grid, i, j, sb);
                set.add(sb.toString());
            }
        }
        return set.size();
    }

    public void dfs(int[][] grid, int i, int j, StringBuilder sb) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != 1)
            return;
        grid[i][j] = 0;
        dfs(grid, i + 1, j, sb.append("u"));
        dfs(grid, i, j + 1, sb.append("r"));
        dfs(grid, i - 1, j, sb.append("d"));
        dfs(grid, i, j - 1, sb.append("l"));
    }

    @Test
    public void test1() {

    }
}
