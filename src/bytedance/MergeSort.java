package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 归并排序：背下来！！！！！！！！！
 * 把数组划分成两个子数组；
 * 一直递归地把子数组划分成更小的子数组，直到子数组里面只有一个元素；
 * 依次按照递归返回顺序，合并排好序的的子数组，直到最后把整个数组顺序排号
 * 归并排序，稳定：即相同元素相对位置不改变
 * 时间O(N*logN),空间O(n)
 */
public class MergeSort {
    /*归并排序主体函数*/
    public void mergeSort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo+(hi-lo)/2;
        mergeSort(nums, lo, mid);
        mergeSort(nums, mid + 1, hi);

        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        /*复制一份原来的数组*/
        int[] copy = nums.clone();
        /*k表示从什么位置开始修改数组
         * i表示左半边的起始位置
         * j表示右半边的起始位置*/
        int k = lo, i = lo, j = mid + 1;
        /*一共可能有四种情况：*/
        while (k <= hi) {
            if (i > mid) nums[k++] = copy[j++];//左半边都处理完毕，只需要把右半边逐个拷贝过去就好了
            else if (j > hi) nums[k++] = copy[i++];//右半边都处理完毕，只需要把左半边逐个拷贝过去就好了
            else if (copy[j] < copy[i]) nums[k++] = copy[j++];//右边的数小于左边的数，只需要把右边数拷贝到合适的位置
            else nums[k++] = copy[i++];//左边的数小于右边的数，只需要把左边数拷贝到合适的位置
        }

    }


    @Test
    public void test2() {
        int[] arr = new int[]{9, 15, 16, 16, 16, 16, 6, 9, 9, 9, 9, 3, 8, 6, 5, 6, 1, 6, 2, 6, 8};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
