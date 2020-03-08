package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 幂集。编写一种方法，返回某集合的所有子集。集合中不包含重复的元素。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 *  输入： nums = [1,2,3]
 *  输出：
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * 思路：dfs
 */
public class Medium0804 {
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length == 0) return res;
		dfs(res, new ArrayList<>(), nums, 0);
		res.add(new ArrayList<>());
		return res;
	}

	private void dfs(List<List<Integer>> res, ArrayList<Integer> tmp, int[] nums, int start) {
		if (start == nums.length) return;
		for (int i = start; i < nums.length; i++) {
			tmp.add(nums[i]);
			res.add(new ArrayList<>(tmp));
			dfs(res, tmp, nums, i + 1);
			tmp.remove(tmp.size() - 1);//回溯
		}
	}

	@Test
	public void test1() {
		int[] nums = new int[]{1, 2, 3};
		System.out.println(subsets(nums));
	}
}
