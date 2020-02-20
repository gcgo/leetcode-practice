package bytedance;

import org.junit.Test;

/**
 * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * <p>
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 * 动态规划，
 * imax表示以当前节点为终结节点的最大连续子序列乘积
 * imin表示以当前节点为终结节点的最小连续子序列乘积
 * 遇到负数，交换最大最小值。
 */
public class Medium152 {

    public int maxProduct(int[] nums) {
		int max = Integer.MIN_VALUE, imax = 1, imin = 1;
		for (int num : nums) {
			if (num < 0) {
				int tmp = imax;
				imax = imin;
				imin = tmp;
			}
			imax = Math.max(imax * num, num);
			imin = Math.min(imin * num, num);

			max = Math.max(max, imax);
		}
		return max;
    }

    @Test
    public void test1() {
//		int[] A= new int[] {2,3,-2,4};
        int[] A = new int[]{-2, 0, -1};
        System.out.println(maxProduct(A));
    }
}
