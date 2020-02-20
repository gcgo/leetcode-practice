package bytedance;

import org.junit.Test;

/**
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 * <p>
 * 思路：二分查找法：因为数组是1-n范围，所以我们从1到n往里收缩查找。
 * 先求当前范围中间的那个数mid，假如分布均匀，则数组中小于mid和大于mid的数的数量应该一样多。但本题中告诉我们，有重复的，所以，
 * 每次求出mid后，算一下数组中比mid小的数目cnt。若cnt<=mid,即比mid小的数量少，证明重复的数字在大的那边，更新左边界。
 * 反之更新右边界。最后返回边界值即可。
 * <p>
 * 边界的理解：即我要找到这个重复的数，我若以它为分界线，则在数组中，小于等于它的和大于等于它的数一样多。
 */
public class Medium287 {

    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        while (low <= high) {
            int mid = (high + low) >>> 1;
            int cnt = 0;
            for (int a : nums) {
                if (a <= mid) ++cnt;
            }
            if (cnt <= mid) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    @Test
    public void test1() {
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2}));
    }
}
