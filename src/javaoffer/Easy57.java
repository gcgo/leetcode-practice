package javaoffer;

import org.junit.Test;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * 示例 2：
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *
 * 思路：数组有序，双指针
 */
public class Easy57 {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        int[] res = new int[2];
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                res[0] = nums[i];
                res[1] = nums[j];
                return res;
            } else if (sum > target) j--;
            else i++;
        }
        return new int[0];
    }

    @Test
    public void test1() {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
