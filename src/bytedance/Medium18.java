package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，
 * 判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 思路：避免重复集合的一般都得先排序！！！！！！
 * 双指针查找
 */
public class Medium18 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> results = new ArrayList<>();

        if (nums == null || nums.length < 4)
            return results;

        Arrays.sort(nums);//先排序！！

        for (int left = 0; left < nums.length - 3; left++) {
            if (left > 0 && nums[left] == nums[left - 1])//left剪枝，重复的跳过
                continue;
            // 考察left是否太大了
            if (nums[left] + nums[left + 1] + nums[left + 2] + nums[left + 3] > target)
                break;
            for (int right = nums.length - 1; right >= left + 2; right--) {
                if (right < nums.length - 1 && nums[right] == nums[right + 1])//right剪枝
                    continue;
                // 考察right是否太小了，如果第一个right就小了，那搭配这个left的所有right都不用看了，直接break
                if (nums[left] + nums[right] + nums[right - 1] + nums[right - 2] < target)
                    break;
                // 考察left是否太大了
                if (nums[left] + nums[left + 1] + nums[left + 2] + nums[right] > target)
                    continue;
                //如果left、right没问题，则定下来，在这个区间搜索
                int ll = left + 1;//ll从left往右走
                int rr = right - 1;//rr从right往左走
                while (ll < rr) {
                    int sum = nums[left] + nums[ll] + nums[rr] + nums[right];
                    if (sum < target) {//如果和小了
                        while (rr > ll && nums[ll] == nums[++ll]) {//ll右移，去重
                        }
                    } else if (sum > target) {//如果和大了
                        while (rr > ll && nums[rr] == nums[--rr]) {//rr左移，去重
                        }
                    } else {//sum == target
                        results.add(Arrays.asList(nums[left], nums[ll], nums[rr], nums[right]));
                        while (rr > ll && nums[ll] == nums[++ll]) {
                        }
                        while (rr > ll && nums[rr] == nums[--rr]) {
                        }
                    }
                }
            }
        }

        return results;
    }

    @Test
    public void test1() {
        System.out.println(fourSum((new int[]{-3, -2, -1, 0, 0, 1, 2, 3}), 0));
        System.out.println(fourSum((new int[]{0, 0, 0, 0}), 0));
        System.out.println(fourSum((new int[]{-1, 0, 1, 2, -1, -4}), -1));
        System.out.println(fourSum((new int[]{-4, 0, -4, 2, 2, 2, -2, -2}), 7));

    }
}
