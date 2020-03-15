package javaoffer;

import org.junit.Test;

/**
 *设计一个算法，算出 n 阶乘有多少个尾随零。
 *
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 * 思路：哪些数能计算出0呢？2*5,2的话可以由偶数拆出来，所以就看有多少个5。
 * 1-10，有5、2*5
 * 11-20，有3*5、4*5
 * 21-30，有5*5、6*5
 * 所以先看有几个5,5的阶乘里有1个5,10的阶乘里有2个5.。。
 * 再看有几个25，有一个25，就在刚才5的基础上多一个5.。。
 * 以此类推。。。
 *
 */
public class Easy1605 {
	public int trailingZeroes(int n) {
		int ans = 0;
		while (n > 0) {
			ans += n / 5;
			n /= 5;//即下一次的n/5相当于n/25,再下次相当于n/125
		}
		return ans;
	}

	@Test
	public void test1() {
		System.out.println(trailingZeroes(5));
	}
}
