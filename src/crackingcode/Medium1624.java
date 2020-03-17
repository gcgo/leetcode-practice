package crackingcode;

import org.junit.Test;

import java.util.*;

/**
 * 设计一个算法，找出数组中两数之和为指定值的所有整数对。一个数只能属于一个数对。
 *
 * 示例 1:
 * 输入: nums = [5,6,5], target = 11
 * 输出: [[5,6]]
 * 示例 2:
 * 输入: nums = [5,6,5,6], target = 11
 * 输出: [[5,6],[5,6]]
 * 提示：
 *
 * nums.length <= 100000
 *
 * 思路：
 *
 */
public class Medium1624 {
	public List<List<Integer>> pairSums(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : nums) {
			int n = map.getOrDefault(target - num, 0);
			if (n > 0) {
				res.add(Arrays.asList(num, target - num));
				map.put(target - num, n - 1);
			} else {
				map.put(num, map.getOrDefault(num, 0) + 1);
			}
		}
		return res;
	}

	/*双指针*/
	public List<List<Integer>> pairSums2(int[] nums, int target) {
		List<List<Integer>> res = new LinkedList<>();
		Arrays.sort(nums);
		int start = 0;
		int end = nums.length - 1;
		while (start < end) {
			int sum = nums[start] + nums[end];
			if (sum < target) {
				start++;
			} else if (sum > target) {
				end--;
			} else {
				res.add(Arrays.asList(nums[start], nums[end]));
				start++;
				end--;
			}
		}
		return res;
	}

	@Test
	public void test1() {
		int[] nums = new int[]{5, 1, 8, 4, 8, 2, 0, 5, 0, 7};
		System.out.println(pairSums(nums, 7));
	}
}
