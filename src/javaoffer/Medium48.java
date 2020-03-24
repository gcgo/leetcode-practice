package javaoffer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 * 提示：
 * s.length <= 40000
 * 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 *思路：滑动窗口，优化
 *
 */
public class Medium48 {
	public int lengthOfLongestSubstring(String s) {
		int n = s.length(), res = 0;
		//map存的是某字符上一次出现的位置
		Map<Character, Integer> map = new HashMap<>();
		//i为左区间，j为右区间，右边界移动
		for (int j = 0, i = 0; j < n; j++) {
			// 如果map中包含当前字符
			if (map.containsKey(s.charAt(j))) {
				//更新左边界，左边界应该移动到“map中记录的该元素上一次出现的位置+1”和“当前i”更大的那个位置
				i = Math.max(map.get(s.charAt(j)) + 1, i);
			}
			//更新当前字符位置与结果
			map.put(s.charAt(j), j);
			res = Math.max(res, j - i + 1);
		}
		return res;
	}

	@Test
	public void test1() {

	}

}
