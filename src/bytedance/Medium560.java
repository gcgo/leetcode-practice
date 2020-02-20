package bytedance;

import org.junit.Test;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * <p>
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 * 思路：暴力法，不说了
 * 哈希法：绝了
 * 如果累计总和，在索引 i 和 j 处相差 k，即 sum[i]−sum[j]=k，则位于索引 i 和 j 之间的元素之和是 k。
 *
 */
public class Medium560 {
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            if (tmp == k) {
                res++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                tmp += nums[j];
                if (tmp == k) {
                    res++;
                }
            }
        }
        return res;
    }

    public int subarraySum2(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();//<累加的和，该和出现的次数>
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k))//如果累加到当前的和与map中存的某之前的和相差k，证明中间这段累加和为k。
                count += map.get(sum - k);//map存的这个次数就相当于k出现的次数。
            map.put(sum, map.getOrDefault(sum, 0) + 1);//记录每一次的累加和
        }
        return count;
    }

    @Test
    public void test1() {
        System.out.println(subarraySum2(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, 0));
    }
}
