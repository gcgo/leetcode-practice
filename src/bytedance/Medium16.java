package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。
 * 假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */
public class Medium16 {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);//先排序
        int len = nums.length;
        int res = Integer.MAX_VALUE;//存最后的和
        int minv = Integer.MAX_VALUE;//存最小的差
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {//重复元素不考虑
                continue;
            }
            int begin = i + 1;//双指针
            int end = len - 1;
            while (begin < end) {
                int sum = nums[i] + nums[begin] + nums[end];//固定nums[i]
                int minus = sum - target;
                if (minus == 0) {
                    return sum;
                } else if (minus > 0) {//sum>target
                    if (minus < minv) {//保存最接近的结果
                        minv = minus;
                        res = sum;
                    }
                    end--;
                } else {//sum<target
                    if (-minus < minv) {
                        minv = -minus;
                        res = sum;
                    }
                    begin++;
                }
            }
        }
        return res;
    }

    @Test
    public void test1() {
    }

}
