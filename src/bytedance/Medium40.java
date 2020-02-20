package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 * <p>
 * 思路：
 */
public class Medium40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);//这种一般都得排序！！！不排序无法去重
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, res, new ArrayList<>(), target, 0);
        return res;
    }

    private void dfs(int[] candidates, List<List<Integer>> res,
                     ArrayList<Integer> tmp, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) continue;//剪枝
            if (i > start && candidates[i] == candidates[i - 1]) continue;//去重
            tmp.add(candidates[i]);
            dfs(candidates, res, tmp, target - candidates[i], i + 1);
            tmp.remove(tmp.size() - 1);//回溯
        }
    }

    @Test
    public void test1() {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }
}
