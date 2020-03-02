package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * <p>
 * 示例:
 * <p>
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * <p>
 * 思路：dfs
 */
public class Medium77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    public static void dfs(List<List<Integer>> res, List<Integer> tmp, int start, int n, int k) {
        if (k==0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = start; i <= n-k+1; i++) {//i<=n-k+1剪枝，因为组合长度为k，那从倒数第K个数往后就不用考虑了
            tmp.add(i);
            dfs(res, tmp, i + 1, n, k-1);
            tmp.remove(tmp.size() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(combine(4, 2));
    }

}
