package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * <p>
 * dfs:回溯+剪枝
 */
public class Medium47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 修改 1：排序（这里用升序），为了剪枝方便
        Arrays.sort(nums);

        boolean[] used = new boolean[len];
        // 使用 Deque 是 Java 官方 Stack 类的建议
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, boolean[] used, Deque<Integer> stack, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!used[i]) {

                // 剪枝：i>0，即在我们从下一个元素开始之前，先看一眼是否和上一个元素相等，且都未曾使用过
                //若如此，继续从该位置dfs则会重复，遂跳过
                if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
                    continue;
                }

                used[i] = true;
                stack.addLast(nums[i]);
                dfs(nums, len, depth + 1, used, stack, res);

                // 回溯，撤销选择
                stack.removeLast();
                used[i] = false;
            }
        }
    }

    @Test
    public void test1() {
        int[] m = new int[]{1, 1, 2};
        List<List<Integer>> lists = permuteUnique(m);
        System.out.println(lists);
    }
}
