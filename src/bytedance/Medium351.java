package bytedance;

import org.junit.Test;

/**
 * 我们都知道安卓有个手势解锁的界面，是一个 3 x 3 的点所绘制出来的网格。
 * <p>
 * 给你两个整数，分别为 ​m 和 n，其中 1 ≤ m ≤ n ≤ 9，
 * 那么请你统计一下有多少种解锁手势，是至少需要经过 m 个点，但是最多经过不超过 n 个点的。
 * <p>
 * 先来了解下什么是一个有效的安卓解锁手势:
 * <p>
 * 每一个解锁手势必须至少经过 m 个点、最多经过 n 个点。
 * 解锁手势里不能设置经过重复的点。
 * 假如手势中有两个点是顺序经过的，那么这两个点的手势轨迹之间是绝对不能跨过任何未被经过的点。
 * 经过点的顺序不同则表示为不同的解锁手势。
 * <p>
 * 思路：题目是分别统计过m个点有几种手势，过m+1有几种，过m+2有几种。。。。。过n个点有几种，最后加起来
 * dfs,9个数字其中1, 3, 7, 9处在角上, 是对称的。
 * 而2, 4, 6, 8处在边中间, 也是对称的.
 * 5处在最中间, 只有这一个.
 * 也就是说9个数字只要求出三个位置的数量就行了.
 * 其中还有一个条件就是如果两个数字经过了一个数字, 那么这个数字必须之前访问过, 因此我们可以设置一个hash表来存储两个数字的中间数字.
 * 从一个数字去访问下一个数字的时候还要看中间数字是不是被访问过了.
 */
public class Medium351 {

    public int numberOfPatterns(int m, int n) {
        if (m < 1 || n < 1) return 0;
        boolean[] visited = new boolean[10];//记录1-9哪个访问过,多申请一个为了下标和实际拨号盘对应
        visited[0] = true;//0没用
        int[][] hash = new int[10][10];//记录两个数之间隔着的数
        hash[1][3] = hash[3][1] = 2;
        hash[1][7] = hash[7][1] = 4;
        hash[3][9] = hash[9][3] = 6;
        hash[7][9] = hash[9][7] = 8;
        hash[2][8] = hash[8][2] = hash[4][6] = hash[6][4] = 5;
        hash[1][9] = hash[9][1] = hash[3][7] = hash[7][3] = 5;
        return dfs(m, n, 1, 1, visited, hash) * 4 +
                dfs(m, n, 1, 2, visited, hash) * 4 +
                dfs(m, n, 1, 5, visited, hash);
    }

    private int dfs(int m, int n, int len, int num, boolean[] visited, int[][] hash) {
        int cnt = 0;//统计个数
        if (len >= m) cnt++;
        if (++len > n) return cnt;
        visited[num] = true;//标记访问过了
        for (int i = 1; i <= 9; i++)
            if (!visited[i] && visited[hash[num][i]]) //num是当前的数，i是下一个数
                cnt += dfs(m, n, len, i, visited, hash);
        visited[num] = false;//回溯
        return cnt;
    }

    @Test
    public void test1() {
        System.out.println(numberOfPatterns(1, 2));//65
        System.out.println(numberOfPatterns(1, 3));//385
    }

}
