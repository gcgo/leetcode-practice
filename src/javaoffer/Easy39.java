package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 *
 * 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
 *
 */
public class Easy39 {

	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[(nums.length - 1) / 2];
	}

	/*投票算法*/
	public int majorityElement2(int[] nums) {
		int candidate = nums[0];
		int count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == candidate) count++;
			else count--;
			if (count == 0) {
				candidate = nums[i];
				count=1;
			}
		}
		return candidate;
	}

	@Test
	public void test1() {

	}

}
