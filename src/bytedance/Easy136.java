package bytedance;

import org.junit.Test;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 思路：异或，相同是0，不同是1
 * 异或具有交换律，且0和任何数异或还是任何数
 * 所以遍历数组，两两异或，最后结果就是出现一次的那个数
 */
public class Easy136 {
    public int singleNumber(int[] nums) {
        int ans = nums[0];
        if (nums.length > 1) {
            for (int i = 1; i < nums.length; i++) {
                ans ^= nums[i];
            }
        }
        return ans;
    }

    @Test
    public void test1() {

    }
}
