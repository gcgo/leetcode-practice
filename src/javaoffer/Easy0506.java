package javaoffer;

import org.junit.Test;

/**
 *整数转换。编写一个函数，确定需要改变几个位才能将整数A转成整数B。
 *
 * 示例1:
 *  输入：A = 29 （或者0b11101）, B = 15（或者0b01111）
 *  输出：2
 * 示例2:
 *  输入：A = 1，B = 2
 *  输出：2
 * 提示:
 *
 * A，B范围在[-2147483648, 2147483647]之间
 *
 * 思路：AB异或，再统计有几个1
 *
 */
public class Easy0506 {
	public int convertInteger(int A, int B) {
		return Integer.bitCount(A^B);
	}

	@Test
	public void test1() {
		System.out.println(convertInteger(29,15));
	}
}
