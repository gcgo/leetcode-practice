package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * <p>
 * 思路：遍历小的，在大的中寻找
 */
public class Easy349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1.length < nums2.length) return helper(nums1, nums2);
        else return helper(nums2, nums1);
    }

    private int[] helper(int[] numsA, int[] numsB) {//规定传进来numsA长度比numsB小
        Set<Integer> setb = new HashSet<>();
        for (int n : numsB) setb.add(n);
        Set<Integer> res = new HashSet<>();
        for (int value : numsA) {
            if (setb.contains(value)) res.add(value);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    @Test
    public void test1() {
        System.out.println(Arrays.toString(intersection(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }
}
