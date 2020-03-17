package crackingcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 例如，给出 n = 3，生成结果为：
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * 思路：
 */
public class Medium0809 {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<>();
		dfs(res, new StringBuilder(), 0, 0, n);
		return res;
	}

	private void dfs(List<String> res, StringBuilder sb, int left, int right, int n) {
		if (sb.length() == 2 * n) {
			res.add(sb.toString());
			return;
		}
		if (left < n) {
			sb.append("(");
			left++;
			dfs(res, sb, left, right, n);
			sb.deleteCharAt(sb.length() - 1);
			left--;
		}
		if (right < left) {
			sb.append(")");
			right++;
			dfs(res, sb, left, right, n);
			sb.deleteCharAt(sb.length() - 1);
			right--;
		}
	}

	@Test
	public void test1() {
		System.out.println(generateParenthesis(3));
	}
}
