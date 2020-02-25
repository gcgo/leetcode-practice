package bytedance;

import org.junit.Test;

/**
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 * <p>
 * 思路：经典二分搜索模板
 */
public class Easy704 {
    public int search(int[] nums, int target) {//数组不重复，找target
        int start = 0;
        int end = nums.length - 1;//end取值决定了while循环的判断条件
        //end决定了搜索空间为[start,end],所以while就在搜索空间为空时停止，即start>end时
        while (start <= end) {
            int mid = start + end >>> 1;
            if (target == nums[mid]) return mid;
                //同样这里为什么是end=mid-1，而不是end=mid，因为mid的值已经判断过了，
                // 需要更新搜索区间，所以新区间就不再包含mid了
            else if (target < nums[mid]) end = mid - 1;
            else if (target > nums[mid]) start = mid + 1;
        }
        return -1;
    }

    //***************************找target左边界*************************************************
    public int search2(int[] nums, int target) {//target在数组中出现多次，找到第一次的位置。
        int start = 0;
        int end = nums.length;//end取值决定了while循环的判断条件
        //end决定了搜索空间为[start,end）,所以while就在搜索空间为空时停止，即start==end时
        while (start < end) {
            int mid = start + end >>> 1;
            //因为我们要找target出现的左边界，所以当找到target时，更新右边界，在[start,mid)中继续查找
            if (target == nums[mid]) end = mid;
                //同样这里为什么是end=mid，而不是end=mid-1，因为end定义的值我们取不到
                // 需要更新搜索区间，所以新区间右边界还是开区间mid
            else if (target < nums[mid]) end = mid;
            else if (target > nums[mid]) start = mid + 1;
        }
        // target ⽐所有数都⼤
        if (start == nums.length) return -1;
        return nums[start] == target ? start : -1;//最后返回start还是end是一样的，因为终止条件时start==end
    }

    //***************************找target右边界*************************************************
    public int search3(int[] nums, int target) {//target在数组中出现多次，找到最后一次的位置。
        int start = 0;
        int end = nums.length;//end取值决定了while循环的判断条件
        //end决定了搜索空间为[start,end）,所以while就在搜索空间为空时停止，即start==end时
        while (start < end) {
            int mid = start + end >>> 1;
            //因为我们要找target出现的右边界，所以当找到target时，更新左边界，在[mid+1,end)中继续查找
            if (target == nums[mid]) start = mid + 1;
                //同样这里为什么是end=mid，而不是end=mid-1，因为end定义的值我们取不到
                // 需要更新搜索区间，所以新区间右边界还是开区间mid
            else if (target < nums[mid]) end = mid;
            else if (target > nums[mid]) start = mid + 1;
        }
        if (start == 0) return -1;//即target比数组元素都小
        //为什么返回start-1？因为我们一直更新左边界是start=mid+1，且target最后一次出现一定是在mid位置，而mid=start-1
        return nums[start - 1] == target ? start - 1 : -1;//最后返回start还是end是一样的，因为终止条件时start==end
    }

    @Test
    public void test1() {
        System.out.println(search(new int[]{-1, 0, 3, 5, 9, 12}, 2));
        System.out.println(search2(new int[]{-1, 2, 2, 2, 9, 12}, 2));
        System.out.println(search3(new int[]{-1, 2, 2, 2, 9, 12}, 2));
    }
}
