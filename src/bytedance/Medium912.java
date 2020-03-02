package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整数数组 nums，将该数组升序排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[5,2,3,1]
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：[5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * <p>
 * 思路：排序大总结！！
 */
public class Medium912 {

    public List<Integer> sortArray(int[] nums) {
        insertSort(nums);
        return new ArrayList<>();
    }

    /**
     *  
     *   插入排序<br/>  
     *   <ul>  
     *   <li>从第一个元素开始，该元素可以认为已经被排序</li>  
     *   <li>取出下一个元素，在已经排序的元素序列中从后向前扫描</li>  
     *   <li>如果该元素（已排序）大于新元素，将该元素移到下一位置</li>  
     *   <li>重复步骤3，直到找到已排序的元素小于或者等于新元素的位置</li>  
     *   <li>将新元素插入到该位置中</li>  
     *   <li>重复步骤2</li>  
     *   </ul>  
     *     
     *   @param numbers  
     *  
     */
    public void insertSort(int[] numbers) {
        int temp, j;
        for (int i = 1; i < numbers.length; i++) {
            temp = numbers[i];
            for (j = i; j > 0 && numbers[j - 1] > temp; j--)
                numbers[j] = numbers[j - 1];//nums[j-1]比temp大就把它往后移动
            numbers[j] = temp;
        }
    }
//**********************************************************************************

    /**
     * 三路快排
     *
     * @param arr
     */
    public void quicksort(int[] arr) {
        int n = arr.length;
        quicksort3way(arr, 0, n - 1);
    }

    public void quicksort3way(int[] arr, int l, int r) {
        if (l >= r) return;
//      swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);//第一个数随机交换位置，实际就是随机选pivot
        int pivot = arr[l];//用于比较的值
        int lt = l;     // arr[l+1...lt] < pivot，初始值往左扩一格
        int gt = r + 1; // arr[gt...r] > pivot，初始值往右扩一格
        int cur = l + 1;  // arr[lt+1...cur) == pivot
        while (cur < gt) {//从cur开始考察
            if (arr[cur] < pivot) {
                swap(arr, cur, lt + 1);//交换时lt往右扩一格，对比初始值记忆
                cur++;
                lt++;
            } else if (arr[cur] > pivot) {
                swap(arr, cur, gt - 1);//交换时gt往左扩一格，对比初始值记忆
                gt--;
                //这里为什么 cur 不用自加了呢？、
                //很好理解，因为 cur 是从左边过来的，所以它与右边交换后，新换过来的这个数他没见过，需要再次考察
                //而左边的数，他都见过，若与左边进行交换，则 cur++
            } else if (arr[cur] == pivot) {
                cur++;
            }
        }
        swap(arr, l, lt);//考察完一轮，交换pivot和最后一个小于pivot的数
        quicksort3way(arr, l, lt - 1);//对小于pivot部分递归
        quicksort3way(arr, gt, r);//对大于pivot部分递归
    }

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    @Test
    public void test1() {
        int[] arr = new int[]{5, 1, 1, 2, 0, 0};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
