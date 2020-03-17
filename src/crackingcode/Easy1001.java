package crackingcode;

import org.junit.Test;

/**
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例:
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 * 说明:
 * A.length == n + m
 *
 * 思路：逆向双指针，AB都从数组后面遍历，谁大谁就存进A的最后面，直到一个数组遍历完，把剩下的存过去
 */
public class Easy1001 {
	public void merge(int[] A, int m, int[] B, int n) {
		int p1 = m - 1;
		int p2 = n - 1;
		int cur = m + n - 1;
		/*技巧，因为最后都合并到A，所以while只需判断B是否遍历完
		 * 若B没遍历完，即使A便利完了，也可以执行else
		 * 若B遍历完了，循环结束，A也不用动*/
		while (p2 >= 0) {
			if (p1 >= 0 && A[p1] > B[p2]) {
				A[cur] = A[p1];
				p1--;
			} else {
				A[cur] = B[p2];
				p2--;
			}
			cur--;
		}
	}

	@Test
	public void test1() {

	}
}
