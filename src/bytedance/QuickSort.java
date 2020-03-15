package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 快速排序：背下来！！！！！！！！！
 * 把数组分成较大和较小的两个子数组，然后递归排序两个子数组
 *
 * 快速排序，不稳定：
 * 时间O(N*logN),空间O(logN)
 */
public class QuickSort {
    /*快速排序主体函数*/
    public void quickSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        /*p为分隔的基准点*/
        int p = partition(nums, lo, hi);
        quickSort(nums, lo, p - 1);
        quickSort(nums, p + 1, hi);
    }

    private int partition(int[] nums, int lo, int hi) {
        /*随机位置上的数和最后一个数交换位置，作为pivot*/
        swap(nums, (int) (Math.random() * (hi - lo + 1)) + lo, hi);
        /*不断地把比nums[hi]小的数赋值到nums[i]*/
        int i, j;
        for (i = lo, j = lo; j < hi; j++) {
            if (nums[j] <= nums[hi]) {
                swap(nums, i++, j);
            }
        }
        /*循环完毕后i之前的数都比基准值小，最后将末尾的基准值放置在i位置*/
        swap(nums, i, j);
        /*返回指针i作为基准点的位置*/
        return i;
    }

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    @Test
    public void test2() {
        int[] arr = new int[]{9, 15, 16, 16, 16, 16, 6, 9, 9, 9, 9, 3, 8, 6, 5, 6, 1, 6, 2, 6, 8};
        int[] arr2 = new int[]{8,7,6,5,4,4,4,3,2,1};
        quickSort(arr2, 0, arr2.length - 1);
        System.out.println(Arrays.toString(arr2));
    }

}
