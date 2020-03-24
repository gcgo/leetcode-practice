package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 * 示例 2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 * 限制：
 * 数组长度为 5 
 * 数组的数取值为 [0, 13] .
 *
 * 思路：
 *
 */
public class Easy61 {
	public boolean isStraight(int[] nums) {
		Arrays.sort(nums);
		int zero=0;
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 0) {
				zero++;
				continue;
			}
			if (nums[i] == nums[i + 1]) return false;//对子，直接false
			//若不连续，则用大小猫来替中间的牌
			if (nums[i] != nums[i + 1] + 1) zero -= nums[i + 1] - nums[i] - 1;
		}
		//大小猫最多有2张，所以最后顶多都用了，zero等于0，不会小于0
		return zero >= 0;
	}

	@Test
	public void test1() {
		System.out.println(isStraight(new int[]{0, 0, 1, 5}));
	}

}
