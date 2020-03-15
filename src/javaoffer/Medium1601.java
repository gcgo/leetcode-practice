package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
 *
 * 示例：
 *
 * 输入: numbers = [1,2]
 * 输出: [2,1]
 * 提示：
 *
 * numbers.length == 2
 *
 * 思路：异或实现交换
 *
 */
public class Medium1601 {
	public int[] swapNumbers(int[] numbers) {
		numbers[0] = numbers[1] ^ numbers[0];
		numbers[1] = numbers[1] ^ numbers[0];
		numbers[0] = numbers[1] ^ numbers[0];
		return numbers;
	}

	@Test
	public void test1() {
		System.out.println(Arrays.toString(swapNumbers(new int[]{1, 2})));
	}
}
