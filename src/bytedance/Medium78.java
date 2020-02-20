package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 思路：dfs模板题！！！！！！
 */
public class Medium78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, nums, new ArrayList<Integer>(), 0);
        res.add(new ArrayList<>());//按题目要求加入一个空集
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, int start) {
        if (start == nums.length) return;//返回

        for (int i = start; i < nums.length; i++) {//逐个开头找
            tmp.add(nums[i]);
            res.add(new ArrayList<>(tmp));
            dfs(res, nums, tmp, i + 1);
            tmp.remove(tmp.size() - 1);//回溯，状态撤销
        }
    }

    @Test
    public void test1() {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}
