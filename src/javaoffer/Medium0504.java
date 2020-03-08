package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 *下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
 * 示例1:
 *  输入：num = 2（或者0b10）
 *  输出：[4, 1] 或者（[0b100, 0b1]）
 *
 * 示例2:
 *  输入：num = 1
 *  输出：[2, -1]
 * 提示:
 * num的范围在[1, 2147483647]之间；
 * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
 *
 * 思路：
 *
 * 暴 力 搜 索！！！！
 */
public class Medium0504 {
	/*暴力搜索就完了*/
	public int[] findClosedNumbers(int num) {
		int[] a = new int[]{-1, -1};
		int b1 = Integer.bitCount(num);//num有几个1
		/*找大数*/
		for (int i = num + 1; i <= Integer.MAX_VALUE; i++) {
			if (Integer.bitCount(i) == b1) {
				a[0] = i;
				break;
			}
		}
		/*找小数*/
		for (int i = num - 1; i >= 1; i--) {
			if (Integer.bitCount(i) == b1) {
				a[1] = i;
				break;
			}
		}
		return a;
	}



	@Test
	public void test1() {
		System.out.println(Arrays.toString(findClosedNumbers(1837591841)));
		System.out.println(Arrays.toString(findClosedNumbers(3)));
	}
}
