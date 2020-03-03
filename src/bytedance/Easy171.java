package bytedance;

import org.junit.Test;

/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 *
 * 例如，
 *
 *     A -> 1
 *     B -> 2
 *     C -> 3
 *     ...
 *     Z -> 26
 *     AA -> 27
 *     AB -> 28
 *     ...
 * 示例 1:
 *
 * 输入: "A"
 * 输出: 1
 * 示例 2:
 *
 * 输入: "AB"
 * 输出: 28
 * 示例 3:
 *
 * 输入: "ZY"
 * 输出: 701
 *
 * 思路：和168题互为逆操作
 * 按照26进制计算即可
 *
 */
public class Easy171 {
	public int titleToNumber(String s) {
		int res = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			res += (s.charAt(i) - 'A' + 1) * Math.pow(26, s.length() - i - 1);
		}
		return res;
	}

	@Test
	public void test1() {
		System.out.println(titleToNumber("A"));
	}
}
