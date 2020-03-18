package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 *
 * 示例:
 *
 * 输入: [4, 6, 7, 7]
 * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 * 说明:
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 *
 * 思路：dfs回溯
 *
 */
public class Medium491 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0)return new ArrayList<>();
        Set<List<Integer>> ans = new HashSet<>();
        List<Integer> temp = new ArrayList<>();
        dfs(ans, temp, nums, 0);
        return new ArrayList<>(ans);
    }

    private void dfs(Set<List<Integer>> ans, List<Integer> temp, int[] nums, int start) {
        List<Integer> tm = new ArrayList<>(temp);
        if (tm.size() >= 2) ans.add(tm);
        for (int i = start; i < nums.length; i++) {
            if (!temp.isEmpty() && temp.get(temp.size() - 1) > nums[i])//如果加入nums[i],会导致temp里面无序,所以抛弃nums[i]
                continue;
            temp.add(nums[i]);
            dfs(ans, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    @Test
    public void test1() {
        System.out.println(findSubsequences(new int[]{4, 6, 7, 7}));
    }
}
