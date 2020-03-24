package javaoffer;

import org.junit.Test;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 * 注意：本题与主站 34 题相同（仅返回值不同）
 * ：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * 思路：排序数组找数，就可以往二分查找方向找
 */
public class Easy53 {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] == target) {
                int i = mid, j = mid;
                while (i >= 0 && nums[i] == target) i--;
                while (j < nums.length && nums[j] == target) j++;
                return j - i - 1;
            } else if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return 0;
    }

    @Test
    public void test1() {

    }

}
