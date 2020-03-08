package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 有重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合。
 * 示例1:
 *  输入：S = "qqe"
 *  输出：["eqq","qeq","qqe"]
 *
 * 示例2:
 *  输入：S = "ab"
 *  输出：["ab", "ba"]
 * 提示:
 *
 * 字符都是英文字母。
 * 字符串长度在[1, 9]之间。
 *
 * 思路：全排列问题,但是去重需要排序，一样的字母取一个开头进行考察即可
 */
public class Medium0808 {
	public String[] permutation(String S) {
		char[] chars = S.toCharArray();
		/*需要排序*/
		Arrays.sort(chars);
		List<String> res = new ArrayList<>();
		boolean[] visited = new boolean[chars.length];
		dfs(res, new StringBuilder(), visited, chars);
		String[] strings = new String[res.size()];
		for (int i = 0; i < strings.length; i++) {
			strings[i] = res.get(i);
		}
		return strings;
	}

	private void dfs(List<String> res, StringBuilder sb, boolean[] visited, char[] chars) {
		if (sb.length() == chars.length) {
			res.add(sb.toString());
			return;
		}
		for (int i = 0; i < chars.length; i++) {
			if (!visited[i]) {
				/*这是用于进行新一轮开头时的判断，此时visited都为false，我们来到下一个字符，打算新的一轮考察
				* 此时需要看看该元素和上一个是否相等，若相等，且都未被访问过，即初始状态，则跳过该字符，避免重复*/
				if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue;
				sb.append(chars[i]);
				visited[i] = true;
				dfs(res, sb, visited, chars);
				visited[i] = false;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	@Test
	public void test1() {
		System.out.println(Arrays.toString(permutation("qqe")));
	}
}
