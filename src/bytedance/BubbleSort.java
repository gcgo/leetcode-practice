package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序：背下来！！！！！！！！！
 * 外层循环内层循环不耦合
 * 外层只是控制最大循环次数，就是n
 * 内层循环做实际排序，从第一个元素开始，和它后面的比较，大的话交换。
 * 通过布尔变量判断内层循环是否有过交换，从而证明排序是否结束！！
 *
 * 冒泡排序，稳定：即相同元素相对位置不改变
 * 时间O(n^2),空间O(1)
 */
public class BubbleSort {
    public void bubbleSort(int[] nums) {
        boolean hasChanged = true;
        for (int i = 0; i < nums.length - 1 && hasChanged; i++) {
            hasChanged = false;
            for (int j = 0; j < nums.length - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    hasChanged = true;
                }
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    @Test
    public void test2() {
        int[] arr = new int[]{9, 15, 16, 16, 16, 16, 6, 9, 9, 9, 9, 3, 8, 6, 5, 6, 1, 6, 2, 6, 8};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
