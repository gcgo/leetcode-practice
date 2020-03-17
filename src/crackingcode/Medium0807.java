package crackingcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
 *
 * 示例1:
 *  输入：S = "qwe"
 *  输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
 * 示例2:
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 * 提示:
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 *
 * 思路：全排列问题
 */
public class Medium0807 {
	public String[] permutation(String S) {
		List<String> res = new ArrayList<>();
		boolean[] visited = new boolean[S.length()];
		dfs(res, new StringBuilder(), visited, S);
		String[] strings = new String[res.size()];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = res.get(i);
		}
		return strings;
	}

	private void dfs(List<String> res, StringBuilder sb, boolean[] visited, String s) {
		if (sb.length() == s.length()) {
			res.add(sb.toString());
			return;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!visited[i]) {
				sb.append(s.charAt(i));
				visited[i] = true;
				dfs(res, sb, visited, s);
				visited[i] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	@Test
	public void test1() {
		System.out.println(Arrays.toString(permutation("abc")));
	}
}
