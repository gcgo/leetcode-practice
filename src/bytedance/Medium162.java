package bytedance;

import org.junit.Test;

/**
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * <p>
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 * <p>
 * 思路：logN，那就是二分查找法。
 * 因为数组两头相当于无穷小，所以二分查找比较中间两个元素大小，大的那边一定存在峰值，因为他会降到无穷小
 */
public class Medium162 {
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > nums[mid + 1])//左边大，去左边找
                r = mid;
            else
                l = mid + 1;
        }
        return l;//终止条件l==r
    }

    @Test
    public void test1() {
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 5, 6, 7}));
    }
}
