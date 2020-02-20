package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * 思路：执行一次深度优先遍历，从树的根结点到叶子结点形成的路径就是一个全排列。
 *
 */
public class Medium46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    /**
     *
     * @param res 结果列表
     * @param temp 临时列表，用于存放当前计算结果
     * @param nums 给定的数组
     * @param used 记录某一位上数字是否使用过
     */
    public void dfs(List<List<Integer>> res, List<Integer> temp, int[] nums, boolean[] used) {
        if (temp.size() == nums.length) {//遍历完了，一组全排列出炉
            res.add(new ArrayList<>(temp)); //以一个集合或数组初始化ArrayList al = new ArrayList(a);//a为集合或数组
            return;
        }
        for (int i = 0; i < nums.length; i++) {//每层递归都会循环遍历所有元素
            if (!used[i]) {//记录某个下标的数是否被使用过
                temp.add(nums[i]);
                used[i] = true;//状态变更
                dfs(res, temp, nums, used);
                temp.remove(temp.size() - 1); //回溯，移除最后一个
                used[i] = false;//状态退回
            }
        }
    }

    @Test
    public void test1() {
        List<List<Integer>> permute = permute(new int[]{1, 2, 3});
        System.out.println(permute.toString());

    }
}
