package bytedance;

import org.junit.Test;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。
 * 如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例: 
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * <p>
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * 思路：
 *
 *
 */
public class Medium209 {

    public int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];//从左边第一个开始累加
            while (sum >= s) {// 当累加和大于等于s时
                res = Math.min(res, i - left + 1);//记录最小长度
                sum -= nums[left];//从和里面减去左边的元素，直到和小于s
                left++;
            }
        }
        return (res != Integer.MAX_VALUE) ? res : 0;
    }

    @Test
    public void test1() {
        int res = minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
        System.out.println(res);

    }
}
