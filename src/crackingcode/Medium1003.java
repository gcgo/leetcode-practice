package crackingcode;

import org.junit.Test;

/**
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
 * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 *
 * 示例1:
 *  输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 *  输出: 8（元素5在该数组中的索引）
 *
 * 示例2:
 *  输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 *  输出：-1 （没有找到）
 * 提示:
 *
 * arr 长度范围在[1, 1000000]之间
 *
 * 思路：旋转1次和100次没有区别，二分查找法，
 * 网友实现
 */
public class Medium1003 {
	public int search(int[] arr, int target) {
		if (arr == null || arr.length == 0) return -1;
		int left = 0, right = arr.length - 1;
		while (left + 1 < right) {
			int mid = (left + right) >>> 1;
			if (arr[left] < arr[mid]) {//左边有序
				if (arr[left] <= target && target <= arr[mid]) {
					right = mid;
				} else {
					left = mid;
				}
			} else if (arr[mid] < arr[left]) {//右边有序
				if (target >= arr[mid] && target <= arr[right]) {
					left = mid;
				} else {
					right = mid;
				}
			} else left++;//????
		}
		if (arr[left] == target) return left;
		if (arr[right] == target) return right;
		return -1;
	}


	@Test
	public void test1() {
		System.out.println(search(new int[]{21, 21, -21, -20, -17, -8, -6, -2,
				-2, -1, 0, 2, 3, 4, 4, 6, 11, 13, 14, 16, 17, 18, 20 ,4}, 4));//13
		System.out.println(search(new int[]{2, 1, 2, 2, 2},1));
	}
}
