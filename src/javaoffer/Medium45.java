package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 提示:
 * 0 < nums.length <= 100
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 *思路：先转换成字符串数组，然后自定义排序
 * 排序规则，s1+s2组合大还是s2+s1组合大
 *
 */
public class Medium45 {
	public String minNumber(int[] nums) {
		String[] strings = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			strings[i] = String.valueOf(nums[i]);
		}
		Arrays.sort(strings, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));
		return String.join("", strings);
	}

	@Test
	public void test1() {
		System.out.println(minNumber(new int[]{3,30,34,5,9}));
	}

}
