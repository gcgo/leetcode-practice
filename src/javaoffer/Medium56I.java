package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 * 示例 1：
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * 限制：
 * 2 <= nums <= 10000
 *
 *思路：异或
 *
 */
public class Medium56I {
	public int[] singleNumbers(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum ^= num;
		}
		//加入那两个数是a和b,此时sum就是a^b
		//因为不同异或才为1，所以可以根据sum为1的那一位分隔数组来找a和b，这里就取sum最低位1
		int flag = 1;
		while ((flag & sum) != flag)flag <<= 1;
		int[] res = new int[2];
		for (int num : nums) {
			if ((flag & num) == flag) res[0] ^= num;
			else  res[1] ^= num;
		}
		return res;
	}

	@Test
	public void test1() {
		System.out.println(Arrays.toString(singleNumbers(new int[]{1, 2, 5, 2})));
	}

}
