package javaoffer;

import org.junit.Test;

import java.util.*;

/**
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 * 2 <= n <= 100000
 *
 * 思路：
 */
public class Easy03 {
	public int findRepeatNumber(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num)) return num;
			set.add(num);
		}
		return -1;
	}

	/*充分利用在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。这个条件。
	 * 如果都不重复，那么n个数正常排序后，数字i应该在下标为i的位置
	 * 所以思路就是，遇到一个数i,如果它不在i位置上，就把它和i位置上的数交换。
	 * 就这样一直遍历数组，直到我们某一次交换时发现数字相等，就返回该数。*/
	public int findRepeatNumber2(int[] nums) {
		int temp;
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != i) {//一直交换，直到i位置为nums[i]为止
				if (nums[i] == nums[nums[i]]) {
					return nums[i];
				}
				temp = nums[i];
				nums[i] = nums[temp];
				nums[temp] = temp;
			}
		}
		return -1;
	}

	@Test
	public void test1() {

	}
}
