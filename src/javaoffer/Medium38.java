package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 * 限制：
 *
 * 1 <= s 的长度 <= 8
 * 思路：全排列有重复元素问题
 *
 */
public class Medium38 {

	public String[] permutation(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		List<String> res = new ArrayList<>();
		boolean[] isVisited = new boolean[s.length()];
		dfs(res, isVisited, new StringBuilder(), chars);
		return res.toArray(new String[0]);
	}

	private void dfs(List<String> res, boolean[] isVisited, StringBuilder sb, char[] chars) {
		if (sb.length() == chars.length) {
			res.add(sb.toString());
			return;
		}
		for (int i = 0; i < chars.length; i++) {
			if (isVisited[i]) continue;
			if (i > 0 && chars[i] == chars[i - 1] && !isVisited[i - 1]) continue;
			sb.append(chars[i]);
			isVisited[i] = true;
			dfs(res, isVisited, sb, chars);
			isVisited[i] = false;
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	@Test
	public void test1() {
		System.out.println(Arrays.toString(permutation("abc")));
	}

}
