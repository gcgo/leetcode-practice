package javaoffer;

import org.junit.Test;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 示例 1：
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * 思路：
 *
 */
public class Easy05 {
	public String replaceSpace(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c == ' ') sb.append("%20");
			else sb.append(c);
		}
		return sb.toString();
	}

	@Test
	public void test1() {
		System.out.println(replaceSpace("We are happy."));
	}
}
