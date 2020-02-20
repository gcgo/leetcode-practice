package bytedance;
/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * <p>
 * 思路：二分查找法：注意边界问题
 */

import org.junit.Test;

import java.util.Arrays;

public class Medium34 {
    public int[] searchRange2(int[] nums, int target) {
        int len = nums.length;
        int l = 0;
        int r = len - 1;//决定了用哪种二分查找法，即while（条件）、l和r的每一轮取值
        int ind1, ind2;
        int[] res = new int[2];
        while (l <= r) {//因为r值可取，所以是≤
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                ind1 = mid;
                ind2 = mid;
                while (ind1 >= 0 && nums[ind1] == target) {//有循环，就不是log n复杂度了
                    ind1--;
                }
                while (ind2 <= len - 1 && nums[ind2] == target) {
                    ind2++;
                }
                res[0] = ind1 + 1;
                res[1] = ind2 - 1;
                return res;
            } else if (nums[mid] > target) {//证明得去左边找
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        if (l > r) {
            res[0] = -1;
            res[1] = -1;
        }
        return res;
    }

    //*************************************logn复杂度**********************************************
    public int[] searchRange(int[] nums, int target) {
        int left = lower_bound(nums, target);
        int right = higher_bound(nums, target);
        return new int[]{left, right};
    }

    private int lower_bound(int[] a, int v) {//找左边界
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (a[mid] < v) {
                l = mid + 1;
            } else if (a[mid] > v) {
                r = mid - 1;
            } else if (a[mid] == v) {
                res = mid;
                r = mid - 1;//继续往左找
            }
        }
        return res;
    }

    private int higher_bound(int[] a, int v) {
        int l = 0;
        int r = a.length - 1;
        int res = -1;
        while (l <= r) {
            int mid = (l + r) >>> 1;
            if (a[mid] > v) {
                r = mid - 1;
            } else if (a[mid] < v) {
                l = mid + 1;
            } else if (a[mid] == v) {
                res = mid;
                l = mid + 1;//继续往右找
            }
        }
        return res;
    }

    @Test
    public void test1() {
        System.out.println(Arrays.toString(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8)));
    }
}
