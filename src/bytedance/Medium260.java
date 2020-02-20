package bytedance;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * <p>
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 * <p>
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * 思路：1最简单就是map存次数，返回次数为1的，没什么可说的。。。。
 * <p>
 * 2异或：首先所有一起值异或一遍，得到的值应该是那两个出现一次的数异或的结果
 * 然后再用这个数与所有值一起异或，得到其中一个出现一次的数。
 * 在用这个数去异或第一次异或的值，得到第二个数。
 */
public class Medium260 {
    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> hashmap = new HashMap();
        for (int n : nums)
            hashmap.put(n, hashmap.getOrDefault(n, 0) + 1);

        int[] output = new int[2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> item : hashmap.entrySet())
            if (item.getValue() == 1) output[idx++] = item.getKey();

        return output;
    }
//******************************************************************************

    /**
     * 有两个数只出现了一次记为 num1、num2 初始化为 0, 其余的数出现了两次,
     * 我们可以对所有的数进行一次异或操作, 易知这个结果就等于 num1 和 num2
     * 的异或结果(相同的数异或结果都为 0, 0和任意数异或结果都为那个数).
     * <p>
     * 那么我可以考虑异或结果的某个非 0 位，如最后一个非 0 位, 因为我们知道只
     * 有当 num1、num2 在该位不一样的时候才会出现异或结果为 1. 所以我们以该
     * 位是否为 1 对数组进行划分, 只要该位为 1 就和 num1 异或, 只要该位为 0
     * 就和 num2 异或, 这样最终得到就是只出现过一次的两个数(其他在该位为 1 或
     * 0 的数必然出现 0/2 次对异或结果无影响)
     */
    public int[] singleNumber2(int[] nums) {
        int num1 = 0, num2 = 0;
        int xor = 0;
        for (int num : nums)
            xor ^= num;

        int bit_1 = 1;
        while ((xor & bit_1) == 0) {//让bit_1不断去和xor做与运算，bit_1不断左移，看xor哪一位是1。
            bit_1 <<= 1;
        }

        for (int num : nums) {
            if ((num & bit_1) == 0)//将所有数按照第bit_1位是否为1来区分，这样肯定把那两个出现一次的数分开了。
                num1 ^= num;//不断异或，就能分出这两个数
            else
                num2 ^= num;
        }

        return new int[]{num1, num2};
    }

    @Test
    public void test1() {
        System.out.println(Arrays.toString(singleNumber2(new int[]{1, 2, 1, 3, 2, 5})));
    }
}
