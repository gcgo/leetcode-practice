package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 示例 1:
 * 输入: [0,1,3]
 * 输出: 2
 * 示例 2:
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 *
 * 限制：
 * 1 <= 数组长度 <= 10000
 * <p>
 * 思路：有序数组找数，想想二分查找
 * 对于有序数组, 大小为i的数应当处于下标为i的位置上, 如果不在, 说明在该数字之前发生了错位
 */
public class Easy53II {

    /*二分查找*/
    public int missingNumber(int[] nums) {
        int sum = (0 + nums.length) * (nums.length + 1) / 2;
        int sum_num = Arrays.stream(nums).sum();
        return sum - sum_num;
    }

    /*求和相减*/
    public int missingNumber2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] != mid) {//反生错位，即左边少了数
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 如果从0 ~ n - 1都不缺值, 则缺少的是n
        return left == nums.length - 1 && nums[left] == left ? left + 1 : left;
    }

    @Test
    public void test1() {
        System.out.println(missingNumber2(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8}));
    }

}
