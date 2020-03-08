package javaoffer;

import org.junit.Test;

/**
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 * 示例1:
 *  输入：s1 = "waterbottle", s2 = "erbottlewat"
 *  输出：True
 *
 * 示例2:
 *  输入：s1 = "aa", "aba"
 *  输出：False
 *
 * 提示：
 * 字符串长度在[0, 100000]范围内。
 * 说明:
 * 你能只调用一次检查子串的方法吗？
 *
 * 思路：长度不等直接false。
 * 本质上，我们是在寻找是否有一种方式可以把第一个字符串分成两部分，即x和y，
 * 如此一来，第一个字符串就是xy，第二个字符串就是yx。
 * 例如，x = wat，y = erbottle。那么，第一个字符串xy = waterbottle，第二个字符串yx = erbottlewat。
 * 将erbottlewat与它本身连接会发生什么。你得到了erbottlewaterbottlewat。
 * 所以若S2是由S1旋转而来，则将两个S2连起来，S1必是S2的子串
 */
public class Easy0109 {
	public boolean isFlipedString(String s1, String s2) {
		if (s1.length() != s2.length()) return false;
		return (s2 + s2).contains(s1);
	}

	@Test
	public void test1() {
		System.out.println(isFlipedString("waterbottle", "erbottlewat"));
		System.out.println(isFlipedString("waterbottle", "erbottleaaa"));
	}
}
