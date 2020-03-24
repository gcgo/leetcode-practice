package javaoffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 * 示例 1：
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *  
 *
 * 限制：
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 *
 *
 */
public class Medium40 {
	/*最大堆*/
	public int[] getLeastNumbers(int[] arr, int k) {
		if (k == 0) return new int[0];
		PriorityQueue<Integer> queue = new PriorityQueue<>(k, (n1, n2) -> n2 - n1);
		for (int n : arr) {
			if (queue.size() == k) {
				if (n > queue.peek()) continue;
				else queue.poll();
			}
			queue.add(n);
		}
		return queue.stream().mapToInt(Integer::intValue).toArray();
	}

	/*快排*/
	public int[] getLeastNumbers2(int[] arr, int k) {
		if (k == 0 || arr.length == 0) {
			return new int[0];
		}
		// 最后一个参数表示我们要找的是下标为k-1的数
		return quickSearch(arr, 0, arr.length - 1, k - 1);
	}

	private int[] quickSearch(int[] nums, int lo, int hi, int k) {
		// 每快排切分1次，找到排序后下标为j的元素，如果j恰好等于k就返回j以及j左边所有的数；
		int j = partition(nums, lo, hi);
		if (j == k) {
			return Arrays.copyOf(nums, j + 1);
		}
		// 否则根据下标j与k的大小关系来决定继续切分左段还是右段。k为下标，不是个数
		return j > k? quickSearch(nums, lo, j - 1, k): quickSearch(nums, j + 1, hi, k);
	}

	// 快排切分，返回下标j，使得比nums[j]小的数都在j的左边，比nums[j]大的数都在j的右边。
	private int partition(int[] nums, int lo, int hi) {
		int v = nums[lo];
		int i = lo, j = hi + 1;
		while (true) {
			while (++i <= hi && nums[i] < v) ;
			while (--j >= lo && nums[j] > v) ;
			if (i >= j) {
				break;
			}
			int t = nums[j];
			nums[j] = nums[i];
			nums[i] = t;
		}
		nums[lo] = nums[j];
		nums[j] = v;
		return j;
	}

	@Test
	public void test1() {
		System.out.println(Arrays.toString(getLeastNumbers(new int[]{0, 0, 0, 2, 0, 5}, 0)));
	}

}
