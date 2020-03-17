package crackingcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 * 回文串不一定是字典当中的单词。
 *
 * 示例1：
 *
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 *
 * 思路：回文串至多有一个字母只出现一次，其余字母必须均出现偶数次
 */
public class Easy0104 {
	public boolean canPermutePalindrome(String s) {
		Map<Character, Integer> map = new HashMap<>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int count = 0;//记录有几个出现一次的数，大于1就返回false；
		for (Character c : map.keySet()) {
			if ((map.get(c) & 1) == 1) count++;//判断c如果出现基数词，那么c只能是中间那个字符，有且只有一个
			if (count > 1) return false;
		}
		return true;
	}

	@Test
	public void test1() {
		System.out.println(canPermutePalindrome("as"));
	}
}
