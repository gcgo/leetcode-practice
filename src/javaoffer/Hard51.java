package javaoffer;

import org.junit.Test;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 示例 1:
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 * 思路：归并排序，过程中统计数量,思路对的，不知为啥超时
 */
public class Hard51 {
	// 用于统计逆序对的数量
	private static int res;

	public int reversePairs(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		mergeSort(nums, 0, nums.length - 1);
		return res;
	}

	// 归并排序整体逻辑
	public void mergeSort(int[] nums, int left, int right) {
		// 如果两个指针相遇，则说明已经排好序
		if (left == right) return;

		int middle = (right + left) >>> 1;
		// 对数组的左半部分进行归并
		mergeSort(nums, left, middle);
		// 对数组的右半部分进行归并
		mergeSort(nums, middle + 1, right);
		mergeHelper(nums, left, middle, right);
	}

	// 归并排序合并的过程
	public void mergeHelper(int[] nums, int left, int mid, int right) {
		int[] copy = nums.clone();
		int k = left, i = left, j = mid + 1;
		while (i <= mid && j <= right) {
			if (i > mid) nums[k++] = copy[j++];
			else if (j > right) nums[k++] = copy[i++];
			if (copy[i] <= copy[j]) nums[k++] = copy[i++];
			else {
				nums[k++] = copy[j++];
				// 本题核心：由于 nums[pos1] > nums[pos2]，
				// 则从 nums[pos1] 到 nums[middle] 必定都是大于 nums[pos2] 的，
				// 因为两部分的子数组已经是各自有序的
				res += mid - i + 1;
			}
		}
	}

	@Test

	public void test1() {
		System.out.println(reversePairs(new int[]{4, 5, 4, 7}));

	}

}
