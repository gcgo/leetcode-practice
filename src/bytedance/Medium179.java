package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 * <p>
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * <p>
 * 思路：自定义比较器
 * 此题单纯降序排列行不通，如案例2,30>3，但显然3排在30前面才是正确答案
 * 所以自定义排序，规则是：
 * s1和s2比较，定义若s1+s2>s2+s1则我们称s1>s2，如330>303,所以3>30
 */
public class Medium179 {
    public String largestNumber(int[] nums) {
        Integer[] integers = new Integer[nums.length];//先把int数组转成integer数组
        for (int i = 0; i < nums.length; i++) {
            integers[i] = nums[i];
        }
        Arrays.sort(integers, (n1, n2) -> {//integer数组可以应用Arrays类自定义比较器
            String s1 = n1 + "" + n2;
            String s2 = n2 + "" + n1;
            return s2.compareTo(s1);//倒序
        });
        StringBuilder sb = new StringBuilder();
        for (Integer n : integers) {
            sb.append(n);
        }
        String res = sb.toString();
        return res.charAt(0) == '0' ? "0" : res;//这是防止所有数都是0的情况，即判断一下开头如果是0，则输出0，而不是一大串0
    }

    @Test
    public void test1() {
        int[] nums1 = new int[]{3, 30, 34, 5, 9};
        int[] nums2 = new int[]{10, 2};
        System.out.println(largestNumber(nums1));
    }
}
