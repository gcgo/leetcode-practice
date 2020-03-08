package javaoffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 *
 * 示例 1：
 *
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 *
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 *
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * 思路：
 * 方法1：s1构造hashmap，遍历s2，在hashmap里找，若没有或value是0直接false，有的话，数目减1
 * 方法2：直接排序，比较是否相等
 */
public class Easy0102 {
	public boolean CheckPermutation(String s1, String s2) {
		if (s1.length() != s2.length()) return false;

		Map<Character, Integer> map = new HashMap<>();
		for (char c1 : s1.toCharArray()) {
			map.put(c1, map.getOrDefault(c1, 0) + 1);
		}
		for (char c2 : s2.toCharArray()) {
			if (!map.containsKey(c2) || map.get(c2) == 0) return false;
			map.put(c2, map.get(c2) - 1);
		}
		return true;
	}

	/*方法2：直接排序，比较是否相等
	* 效率更高*/
	public boolean CheckPermutation2(String s1, String s2) {
		char[] chars1 = s1.toCharArray();
		char[] chars2 = s2.toCharArray();
		Arrays.sort(chars1);
		Arrays.sort(chars2);
		return Arrays.equals(chars1, chars2);
	}

	@Test
	public void test1() {
		System.out.println(CheckPermutation2("abc","bad"));
	}
}
