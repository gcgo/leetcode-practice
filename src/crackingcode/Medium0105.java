package crackingcode;

import org.junit.Test;

/**
 * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。
 * 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑就可以相等。
 *
 * 示例 1:
 * 输入:
 * first = "pale"
 * second = "ple"
 * 输出: True
 *  
 * 示例 2:
 * 输入:
 * first = "pales"
 * second = "pal"
 * 输出: False
 *
 * 思路：如果个数相差大于1个，就不能。
 * 如果个数相同，顺序有一个不同也不行。
 * 如果个数差一个，且一个是另外一个子串就可以。
 */
public class Medium0105 {
	public boolean oneEditAway(String first, String second) {
		int len1 = first.length();
		int len2 = second.length();
		if (len1 == 1 && len2 == 1) return true;
		/*接下来讨论一般情况：*/
		/*情况1:first和second长度差超过1，直接返回false*/
		if (Math.abs(len1 - len2) > 1) return false;
		/*情况2:长度相等，看看是否只有一个字母不相等，其余均相等包括顺序*/
		if (len1 == len2) return helper2(first, second);
		/*情况3，此时first和second长度相差1*/
		if (len1 > len2) return helper3(first, second);
		else if (len1 < len2) return helper3(second, first);
		return true;
	}

	/*用于处理情况2，两字符串长度相等，记录不同的位数，超过1就false*/
	private boolean helper2(String first, String second) {
		int diffCount = 0;
		for (int i = 0; i < first.length(); i++) {
			if (first.charAt(i) != second.charAt(i)) diffCount++;
			if (diffCount > 1) return false;
		}
		return true;
	}

	/*用于处理情况3，两字符串长度相差1，只有短的在长的中 连续 顺序出现，只允许至多一次不连续*/
	private boolean helper3(String first, String second) {
		int diffCount = 0;
		int i = 0, j = 0;
		while (i < first.length() && j < second.length()) {
			if (second.charAt(j) == first.charAt(i)) {
				i++;
				j++;
			} else if (second.charAt(j) != first.charAt(i)) {
				i++;
				diffCount++;
				if (diffCount > 1) return false;
			}
		}
		return true;
	}

	@Test
	public void test1() {
		System.out.println(oneEditAway("pale", "plk"));
	}
}
