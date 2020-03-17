package crackingcode;

import org.junit.Test;

/**
 *编写一个方法，找出两个数字a和b中最大的那一个。不得使用if-else或其他比较运算符。
 *
 * 示例：
 *
 * 输入： a = 1, b = 2
 * 输出： 2
 *
 * 思路：用位运算来替代条件运算符；令a-b=c，通过位运算判断c的符号scA，正为1，负为0,scB为scA取反；
 * 则较大的数为a*scA+b*scB。。。。。。。。。数学公式，不太懂
 * 强转long是防止溢出
 *
 */
public class Easy1607 {
	public int maximum(int a, int b) {
		int i = (int) (((long) a - (long) b) >>> 63);
		return (i ^ 1) * a + i * b;
	}

	@Test
	public void test1() {

	}
}
