package bytedance;

import org.junit.Test;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 *
 *
 * 思路：
 * 中位数就是有序数组的最中间位置的数
 * 但现在两个数组，有需要时间复杂度log，所以需要对两个有序数组分开进行二分法。
 * 每次都在两个数组中找第(m+n)/2个数，比较这两个数，谁小，证明即使以后合并了，它所在数组排在它前面的所有数一定都不是中位数。
 * 照此思路，第二次循环在每个数组中找第“（m+n）/2-刚才排除的数量”，再判断
 */
public class Hard4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //函数说明：在两个有序数组中找到第K小的元素。
        //这里求两次是为了合并m+n分别为基数和偶数的情况，当m+n为基数是，这两次的结果一样；为偶数时即中间那俩数的平均值
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) +
                getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;//数组1的长度
        int len2 = end2 - start2 + 1;//数组2的长度
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1。
        // 即如果len1长了，就反过来，保证数组1先搜索完
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        //如果数组1找完了，直接在数组2里返回第k个元素。
        if (len1 == 0) return nums2[start2 + k - 1];
        //如果K==1了，就返回两个数组最小的元素。这也是最一般情况的递归跳出条件
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        //一般情况：分别在 nums1 和 nums2 中查找第 K/2 个元素，不确定数组还有没有第k/2个元素，没有了就返回最后一个元素。
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {//如果数组一的第k/2个元素大于数组2的第k/2个元素，
            // 则排除数组二的前k/2个元素,下一轮从k中减去该数量。
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {//同理数组1
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }


    @Test
    public void test1() {

    }
}
