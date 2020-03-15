package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 插入排序：背下来！！！！！！！！！
 * 从后向前
 *
 * 插入排序，稳定：即相同元素相对位置不改变
 * 时间O(n^2),空间O(1)
 */
public class InsertSort {
    public void insertSort(int[] nums) {
        for (int i = 1, j, current; i < nums.length; i++) {
            current = nums[i];
            /*不断地拿nums[j]和current比较，如果比current大，就把他往右移动一位
             * 因为current前面已经有序了，所以只要往右移动，最终只会覆盖掉current，而current我们已经保存了*/
            for (j = i - 1; j >= 0 && nums[j] > current; j--) {
                nums[j + 1] = nums[j];
            }
            /*此时nums[j]<current了，所以nums[j+1]就是current*/
            nums[j + 1] = current;
        }
    }

    @Test
    public void test2() {
        int[] arr = new int[]{9, 15, 16, 16, 16, 16, 6, 9, 9, 9, 9, 3, 8, 6, 5, 6, 1, 6, 2, 6, 8};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
