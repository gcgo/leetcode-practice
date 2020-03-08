package javaoffer;

import org.junit.Test;

/**
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
 * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 * 示例1:
 *  输入："Mr John Smith    ", 13
 *  输出："Mr%20John%20Smith"
 *
 * 示例2:
 *  输入："               ", 5
 *  输出："%20%20%20%20%20"
 *
 * 提示：
 * 字符串长度在[0, 500000]范围内。
 *
 * 思路：双指针思路，实际上但指针就可以。
 * 遍历length位，是空格就替换，不是就添加字符，使用stringbuilder记录
 */
public class Easy0103 {
	public String replaceSpaces(String S, int length) {
		char[] chars = S.substring(0, length).toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char c : chars) {
			if (c == ' ') sb.append("%20");
			else sb.append(c);
		}
		return sb.toString();
	}

	/*网络版，
	但是实际上char[]遍历要比str.charAt()要快*/
	public String replaceSpaces2(String S, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			if (S.charAt(i) == ' ') {
				sb.append("%20");
			} else {
				sb.append(S.charAt(i));
			}
		}
		return sb.toString();
	}

	@Test
	public void test1() {
		System.out.println(replaceSpaces("Mr John Smith    ", 13));
		System.out.println(replaceSpaces("               ", 5));
	}
}
