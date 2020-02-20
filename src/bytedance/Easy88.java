package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 思路：从数组nums1后面开始添加
 */
public class Easy88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int ind1 = m - 1;
        int ind2 = n - 1;
        int indMerge = m + n - 1;
        while (indMerge >= 0) {
            if (ind1 < 0) {//数组1已经遍历完了，剩下的都由数组2填充
                nums1[indMerge--] = nums2[ind2--];
                continue;
            }
            if (ind2 < 0) {//数组2遍历完了，数组1不用动
                break;
            }

            if (nums1[ind1] > nums2[ind2]) {//从后向前比较，如果数组1大
                nums1[indMerge--] = nums1[ind1--];//将数组1排过去
            } else {
                nums1[indMerge--] = nums2[ind2--];//否则将数组2排过去
            }
        }

    }

    @Test
    public void test() {
        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};

        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
