package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 * <p>
 * 输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2:
 * <p>
 * 输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 * 思路：dfs模板题：
 */
public class Medium39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, new ArrayList<Integer>(), candidates, target, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(cur));
        }
        for (int i = start; i < candidates.length; i++) {
            if (target < candidates[i]) continue;//如果target<下一个数，则跳过该数
            cur.add(candidates[i]);
            dfs(result, cur, candidates, target - candidates[i], i);
            cur.remove(cur.size() - 1);
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> combinationSum = combinationSum(new int[]{8, 7, 4, 3}, 11);
        System.out.println(combinationSum);
    }
}
