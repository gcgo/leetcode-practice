package javaoffer;

import org.junit.Test;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 *
 * 限制：
 *
 * 0 <= n < 2^31
 * 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
 *
 *
 *思路：先确定区间，再确定数，再确定位。
 *   区间： 1-9, 10-99, 100-999, 1000-9999
 *
 */
public class Medium44 {
	public int countDigitOne(int n) {
		int start = 1;
		int digit = 1;//这个区间数的位数
		long count = 9;//这个区间数的个数
		while (n > digit * count) {//他俩相乘就是字符个数
			n -= digit * count;
			start *= 10;
			digit++;
			count *= 10;
		}
		//至此找到了数字的区间，先确定是哪个数
		int num = start + (n - 1) / digit;
		//再找是这个数里的第几位
		String s = Integer.toString(num);
		char c = s.charAt((n - 1) % digit);
		return c - '0';
	}

	@Test
	public void test1() {
		System.out.println(countDigitOne(11));
	}

}
