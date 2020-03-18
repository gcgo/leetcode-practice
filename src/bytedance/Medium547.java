package bytedance;

import org.junit.Test;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
 * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * <p>
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。
 * 如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
 * 你必须输出所有学生中的已知的朋友圈总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 * <p>
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 * <p>
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 * <p>
 * 思路：给定的矩阵可以看成图的邻接矩阵。这样我们的问题可以变成无向图连通块的个数。
 * 注意和第200题区分
 */
public class Medium547 {
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {//从第一个人开始，如果他没有被考察过，dfs
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {//遍历i的朋友圈
            if (M[i][j] == 1 && visited[j] == 0) {//如果j是i的朋友，且没有被考察过
                visited[j] = 1;//再去深究j的朋友圈
                dfs(M, visited, j);
            }
        }
    }

    //*********************无visited[]版本*************************
    public int findCircleNum2(int[][] M) {
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (M[i][i] == 1) {
                dfs2(M, i);
                count++;
            }
        }
        return count;
    }

    public void dfs2(int[][] M, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1) {
                M[i][j] = M[j][i] = 0;
                dfs2(M, j);
            }
        }
    }

    @Test
    public void test1() {
        int[][] m = new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 0, 1, 1}
        };
        System.out.println(findCircleNum(m));
    }
}
