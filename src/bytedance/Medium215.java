package bytedance;

import org.junit.Test;

/**
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 思路：可以用优先队列；此处用另外一种方法，类快排思想
 * 选定一个数组内的值作为pivot，将小于pivot的数字放到pivot右边，大于等于pivot的数字放到pivot左边。
 * 接着判断两边数字的数量，
 * 如果左边的数量小于k个，说明第k大的数字存在于pivot及pivot右边的区域之内，对右半区执行partition函数；
 * 如果右边的数量小于k个，说明第k大的数字在pivot和pivot左边的区域之内，对左半区执行partition函数。
 * 直到左半区刚好有k-1个数，那么第k大的数就已经找到了。
 */
public class Medium215 {
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        return partition(start, end, nums, k);
    }

    private int partition(int start, int end, int[] nums, int k) {
        int i = start;
        int j = end;
        int pivot = nums[i];//以最左边元素为pivot
        while (i < j) {
            while (i < j && nums[j] >= pivot) {//从后面开始判断：j指向第一个小于pivot的数
                j--;
            }
            if (i < j)//交换左边界和右边第一个小于pivot的数
                nums[i++] = nums[j];
            while (i < j && nums[i] <= pivot) {//i指向第一个大于pivot的数
                i++;
            }
            if (i < j)
                nums[j--] = nums[i];//交换右边界和左边第一个大于pivot的数
        }

        nums[j] = pivot;//最后停下来的位置赋值pivot,此处j==i

        if (k == end - j + 1) {//从队尾到pivot（也就是j右边）一共k-1个元素的话，那么pivot就是第k大的元素
        	//这个式子应该是k-1==end-(j+1)+1，就好理解了
            return pivot;
        } else if (k < end - j + 1) {//比pivot大的数已经超过k个了，所以第k大的数肯定在这里面
            return partition(j + 1, end, nums, k);
        } else {//否则就在j左边，但是现在右边已经有end-j+1个比k大的数了，所以从新区间找（k减去这些数）个数就行了。
            return partition(start, j - 1, nums, k - (end - j + 1));
        }
    }

    @Test
    public void test1() {
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}
