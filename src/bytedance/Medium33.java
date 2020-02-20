package bytedance;

import org.junit.Test;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 */
public class Medium33 {
    /**
     * 如果mid左边是递增的，且target在左边范围内，就在左边找，否则在右边找；
     * 如果mid右边是递增的，且target在右边范围内，就在右边找，否则在左边找；
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchRange(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i <= j) {
            int mid = i + (j - i) >> 1;
            if (nums[mid] == target)
                return mid;
            if (nums[i] <= nums[mid]) {
                if (target < nums[mid] && target >= nums[i])
                    j = mid - 1;
                else
                    i = mid + 1;
            }
            if (nums[mid] <= nums[j]) {
                if (target > nums[mid] && target <= nums[j])
                    i = mid + 1;
                else
                    j = mid - 1;
            }

        }
        return -1;
    }

    @Test
    public void test1() {
        int result = searchRange(new int[]{1, 2, 6, 7, 8, 9}, 7);
        System.out.println(result);

    }
}
