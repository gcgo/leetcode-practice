package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * <p>
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 * <p>
 * 思路：三路快排，也是Arrays.sort源码思想
 * 三个指针，分别是 left、cur、right。left指向数组最左侧，right指向数组最右侧，cur代表当前正在遍历的数组元素，
 * 当 cur 遍历的元素是 0 时，将 cur 指向的元素 与 left 指向的元素交换，然后 cur++，left++。
 * 当 cur 遍历的元素是 1 时，cur++。
 * 当 cur 遍历的元素是 2 时，将 cur 指向的元素与 right 指向的元素交换，然后 p2--，cur不动！！！
 * 直到 cur > p2 ,循环结束（即全部扫描完毕）。
 */
public class Medium75 {
    public void sortColors(int[] nums) {
        int left = 0, cur = 0;
        int right = nums.length - 1;
        while (cur <= right) {
            if (nums[cur] == 0) {
                swap(nums, left, cur);
                left++;
                cur++;
            } else if (nums[cur] == 2) {
                swap(nums, right, cur);
                right--;
                //这里为什么cur不用自加了呢？、
                //很好理解，因为cur是从左边过来的，所以它与右边交换后，新换过来的这个数他没见过，需要再次考察
                //而左边的数，他都见过，若与左边进行交换，则cur++
            } else if (nums[cur] == 1) {
                cur++;
            }
        }
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    //***************************快速排序总结****************************************

    //******************************************************************************
    @Test
    public void test() {
//		int[] tes= new int[] {2,0,2,1,1,0};
        int[] test = new int[]{2, 0, 2, 1, 1, 0};
        sortColors(test);
        System.out.println(Arrays.toString(test));
    }

}
