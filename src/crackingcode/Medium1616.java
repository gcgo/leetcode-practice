package crackingcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。
 * 注意：n-m尽量最小，也就是说，找出符合条件的最短序列。
 * 函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * 示例：
 * 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
 * 输出： [3,9]
 * 提示：
 * 0 <= len(array) <= 1000000
 *
 * 思路：先从左向右找，找到第一个下降的数a，再从右向左找，找到第一个上升的数b
 * 找ab之间的最大值最小值，包含ab
 * a再往回找第一个小于它的数，确定左边界
 * b再往回找第一个大于它的数，确定右边界
 *
 */
public class Medium1616 {
	public int[] subSort(int[] array) {
		if (array == null || array.length < 2) return new int[]{-1, -1};
		int l = 0, r = array.length - 1;
		//从左向右找，找到第一个下降的数a
		while (l < array.length - 1 && array[l] <= array[l + 1]) l++;
		//从右向左找，找到第一个上升的数b
		while (r > 0 && array[r] >= array[r - 1]) r--;
		//如果他俩中间没有数，证明数组有序
		if (l >= r) return new int[]{-1, -1};
		//否则
		//找[l,r]最值
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int k = l; k <= r; k++) {
			if (min > array[k]) {
				min = array[k];
			}
			if (max < array[k]) {
				max = array[k];
			}
		}

		while (l >= 0 && array[l] > min) l--;
		while (r <= array.length - 1 && array[r] < max) r++;

		return new int[]{l + 1, r - 1};//结果需要往中间收缩一位
	}

	@Test
	public void test1() {
		Arrays.stream(subSort(new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19}))
				.forEach(System.out::println);
	}
}
