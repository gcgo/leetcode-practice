package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 三路快排：pivot为分界点，把数组分为小于pivot、等于pivot、大于pivot三部分
 * [l..........lt-1,    lt......gt-1,     gt.......r]
 * ****< pivot************== pivot**********> pivot**
 * 对第一和第三部分递归
 */
public class QuickSort3Ways {

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

    public void quicksort(int[] arr) {
        int n = arr.length;
        quicksort3way(arr, 0, n - 1);
    }

    public void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    @Test
    public void test2() {
        int[] arr = new int[]{9, 15, 16, 16, 16, 16, 6, 9, 9, 9, 9, 3, 8, 6, 5, 6, 1, 6, 2, 6, 8};
        quicksort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
