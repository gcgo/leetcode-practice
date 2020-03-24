package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * 示例 1：
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 *思路：先排序，然后遍历判断
 *
 */
public class Medium56II {
	public int singleNumbers(int[] nums) {
		Arrays.sort(nums);
		int i=0;
		while(i<nums.length-2) {
			if (nums[i]==nums[i+1]&&nums[i]==nums[i+2]) {
				i+=2;
			}else {
				return nums[i];
			}
			i++;
		}
		return nums[nums.length-1];
	}

	@Test
	public void test1() {
		System.out.println(singleNumbers(new int[]{1, 2, 5, 2}));
	}

}
