package bytedance;

import org.junit.Test;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 *
 * 示例 1:
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 * 输入的字符串长度不会超过1000。
 *
 * 思路：中心扩展法，以i为中心，向两边扩展，如果相等就算一个
 *
 */
public class Medium647 {

	public int countSubstrings(String s) {
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			/*覆盖奇数偶数两种情况*/
			res += check(s, i, i);//以i为中心扩展
			res += check(s, i, i + 1);//以i和i+1为中心扩展
		}
		return res;
	}

	private int check(String s, int l, int r) {
		int count = 0;
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			count++;
			l--;
			r++;
		}
		return count;
	}

	@Test
	public void test1() {
		System.out.println(countSubstrings("aaa"));
	}
}
