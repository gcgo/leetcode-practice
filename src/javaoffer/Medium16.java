package javaoffer;

import org.junit.Test;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 *
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/
 *
 * 思路：
 */
public class Medium16 {

	public double myPow(double x, int n) {
		long N = n;
		if (n == 0) return 1.0;
		if (n > 0) return helper(x, N);
		else return helper(1 / x, -N);//大坑！，这块n如果等于-integet.MAX，则-n就越界了，所以应该先把n存为long型
	}

	private double helper(double x, long n) {
		if (n == 1) return x;
		double tmp = helper(x, n >> 1);
		if (n % 2 != 0) {
			return tmp * tmp * x;//n是奇数情况
		} else {
			return tmp * tmp;//n是偶数情况
		}
	}

	@Test
	public void test1() {
		System.out.println(myPow(2.1, 3));
	}

}
