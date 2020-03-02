package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * <p>
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * <p>
 * 必须原地修改，只允许使用额外常数空间。
 * <p>
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * <p>
 * 思路：如1　　2　　7　　4　　3　　1
 * 我们通过观察原数组可以发现，如果从末尾往前看，数字逐渐变大，到了2时才减小的，
 * 然后再从后往前找第一个比2大的数字，是3，那么我们交换2和3，再把此时3后面的所有数字逆转一下即可，步骤如下：
 *
 * 1　　2　　7　　4　　3　　1
 *
 * 1　　2　　7　　4　　3　　1
 *
 * 1　　3　　7　　4　　2　　1
 *
 * 1　　3　　1　　2　　4　　7
 */
public class Medium31 {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        for (; i > 0; i--) {
            if (nums[i - 1] < nums[i]) {//从后向前找第一个突然变小的数
                break;
            }
        }
        //如果i=0,证明该排列是当前的最大值，从左到右降序。返回最小值即可，即从左到右升序
        if (i == 0) {
            int j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
            return;
        }

        //如果找到了那个突然变小的数，在i-1处，则从后往前找第一个比它大的数，然后交换位置，然后把从i位置到末尾的数倒序。
        //1从后往前找第一个比它大的数
        int lastbiggerIndex = findLastbiggerIndex(nums, nums[i - 1]);
        //2交换
        swap(nums, i - 1, lastbiggerIndex);
        //3i往后逆序
        int j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    /**
     * @param nums   数组
     * @param target 查找的数
     * @return 目标数的下标;
     * 范围内数据肯定是从左到右降序的
     */
    private int findLastbiggerIndex(int[] nums, int target) {
        int tmp = nums.length - 1;
        while (nums[tmp] <= target)
            tmp--;
        return tmp;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    @Test
    public void test1() {
        int[] test = new int[]{1, 5, 1};
        System.out.println(Arrays.toString(test));
        nextPermutation(test);
        System.out.println(Arrays.toString(test));
    }
}
