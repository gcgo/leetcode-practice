package javaoffer;

import org.junit.Test;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 思路：与154题相同，直接便利或者二分查找
 *
 */
public class Easy11 {

	public int minArray(int[] numbers) {
		int i = 0, j = numbers.length - 1;
		while (i < j) {
			int m = (i + j) >>> 1;//因为m是向下取整，所以i<=m<j
			if (numbers[m] > numbers[j]) i = m + 1;
			else if (numbers[m] < numbers[j]) j = m;
			else j--;
		}
		return numbers[i];
	}

	@Test
	public void test1() {
	}
}
