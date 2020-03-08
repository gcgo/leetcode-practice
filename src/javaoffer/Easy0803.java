package javaoffer;

import org.junit.Test;

/**
 * 魔术索引。在数组A[0...n-1]中，有所谓的魔术索引，满足条件A[i] = i。
 * 给定一个  有序  整数数组，编写一种方法找出魔术索引，若有的话，在数组A中找出一个魔术索引，如果没有，则返回-1。
 * 若有多个魔术索引，返回索引值最小的一个。
 *
 * 示例1:
 *  输入：nums = [0, 2, 3, 4, 5]
 *  输出：0
 *  说明: 0下标的元素为0
 *
 * 示例2:
 *  输入：nums = [1, 1, 1]
 *  输出：1
 * 提示:
 *
 * nums长度在[1, 1000000]之间
 *思路：二分查找不会
 *
 */
public class Easy0803 {
	public int findMagicIndex(int[] nums) {
		return 0;
	}

	public int findMagicIndex2(int[] nums) {//暴力法
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == i) {
				return i;
			}
		}
		return -1;
	}

	@Test
	public void test1() {
	}
}
