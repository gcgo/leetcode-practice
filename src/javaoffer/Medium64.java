package javaoffer;

import org.junit.Test;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * 示例 1：
 * 输入: n = 3
 * 输出: 6
 * 示例 2：
 * 输入: n = 9
 * 输出: 45
 *
 * 思路：递归吧
 *
 */
public class Medium64 {

	public int sumNums(int n) {
		if (n==0)return 0;
		return n+sumNums(n-1);
	}

	@Test
	public void test1() {
		System.out.println(sumNums(9));
	}
}
