package bytedance;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第k小的元素。
 * 请注意，它是排序后的第k小元素，而不是第k个元素。
 *
 * 示例:
 *
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 * 说明:
 * 你可以假设 k 的值永远是有效的, 1 ≤ k ≤ n^2 。
 *
 * 思路：左上角肯定是最小的元素，右下角肯定是最大的元素
 * 二分查找，原理没弄懂，
 * 可能是只要保证第K小的数在left和right之间，不停缩小left和right直到left==right时，第K小的数就是left也就是right
 *
 * 这种前k小的就用优先队列！！！！！！！！！！！！！！！！！别给自己找事
 *
 *
 */
public class Medium378 {

	public int kthSmallest(int[][] matrix, int k) {
		int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;//[lo, hi)
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			int count = 0, j = matrix[0].length - 1;
			for (int[] row : matrix) {//统计有多少个数小于等于mid
				while (j >= 0 && row[j] > mid) j--;//如果最右边的数小于mid，那么这一行都小于
				count += (j + 1);
			}
			//如果小于mid的数目不到k个，那么第k小的数肯定在mid右边，否则就在mid左边
			if (count < k) lo = mid + 1;
			else hi = mid;
		}
		//最终当lo==hi时，他们就等于所找的数
		return lo;
	}

	//***********************************优先队列********************
	public int kthSmallest2(int[][] matrix, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2-o1);//倒序排列
		for (int[] ints : matrix) {
			for (int j = 0; j < matrix[0].length; ++j) {
				queue.offer(ints[j]);
				if (queue.size() > k) queue.poll();
			}
		}
		return queue.peek();
	}

	@Test
	public void test() {
	}

}
