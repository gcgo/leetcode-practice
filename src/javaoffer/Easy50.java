package javaoffer;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。
 *
 * 示例:
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *
 * 思路：
 */
public class Easy50 {
	public char firstUniqChar(String s) {
		if (s == null || s.length() == 0) return ' ';
		Map<Character, Integer> map = new LinkedHashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		for (Character c : map.keySet()) {
			if (map.get(c) == 1) return c;
		}
		return ' ';
	}

	/*更快的数组操作*/
	public char firstUniqChar2(String s) {
		if (s.equals("")) return ' ';
		//创建‘a'-'z'的字典
		int[] target = new int[26];
		//第一次遍历，将字符统计到字典数组
		for (int i = 0; i < s.length(); i++) {
			target[s.charAt(i) - 'a']++;
		}
		//第二次遍历，从字典数组获取次数
		for (int i = 0; i < s.length(); i++) {
			if (target[s.charAt(i) - 'a'] == 1) return s.charAt(i);
		}
		return ' ';
	}

	@Test
	public void test1() {
		System.out.println(firstUniqChar("abaccdeff"));
	}

}
