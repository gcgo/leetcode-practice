package javaoffer;

import org.junit.Test;

import java.util.*;

/**
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 *
 * 0 <= len(s) <= 100
 * 如果你不使用额外的数据结构，会很加分。
 *
 * 思路：
 * 方法1 set查重
 * 方法2 不适用额外的数据结构。。。数组，map都不能用
 * 由于ASCII码字符个数为128个，而且题目说了如果你不使用额外的数据结构，会很加分。
 * 因此可以使用两个64位的long变量来存储是否出现某个字符，二进制位1表示出现过， 0表示未出现过。
 */
public class Easy0101 {
	public boolean isUnique(String astr) {
		Set<Character> set = new HashSet<>();
		for (Character c : astr.toCharArray()) {
			if (set.contains(c)) return false;
			set.add(c);
		}
		return true;
	}

	/*方法2，位运算*/
	public boolean isUnique2(String astr) {
		long low64 = 0;
		long high64 = 0;
		for (Character c : astr.toCharArray()) {
			if (c >= 64) {//在高位标记
				long bitIndex = 1 << c - 64;
				if ((high64 & bitIndex) != 0) return false;
				high64 |= bitIndex;
			} else if (c < 64) {//在低位标记
				long bitIndex = 1 << c;
				if ((low64 & bitIndex) != 0) return false;
				low64 |= bitIndex;
			}
		}
		return true;
	}

	@Test
	public void test1() {
		System.out.println(isUnique("leetcode"));
	}
}
