package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。 
 * 示例 1:
 *
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 *
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * 思路：dfs,模板题
 *
 */
public class Medium216 {

	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> ans = new ArrayList<>();
		dfs(ans, new ArrayList<>(), k, 1, n);
		return ans;
	}

	private void dfs(List<List<Integer>> ans, List<Integer> comb, int k, int start, int n) {
		if (comb.size() > k) return;
		if (comb.size() == k && n == 0) {
			ans.add(new ArrayList<>(comb));//添加结果集
			return;
		}
		for (int i = start; i <= 9; i++) {//选择列表
			if (n > 0) {
				comb.add(i);//做选择
				dfs(ans, comb, k, i + 1, n - i);
				comb.remove(comb.size() - 1);//回溯
			}
		}
	}

	@Test
	public void test1() {
		List<List<Integer>> res = combinationSum3(3, 9);
				System.out.print(res);
		}
	}
