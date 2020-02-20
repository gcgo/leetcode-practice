package bytedance;

import org.junit.Test;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * <p>
 * 思路：二分查找,当x>2时，它的平方根肯定< x/2 所以不用全遍历，需要考虑几个特殊值：int最大值、0、1、4
 */
public class Easy69 {
    public int mySqrt(int x) {
        for (long i = 0; i <= (x >> 1) + 1; i++) {
            if (i * i == x) return (int) i;
            if (i * i > x) return (int) (i - 1);
        }
        return 0;
    }

    @Test
    public void test1() {
        System.out.println(mySqrt(2147483647));
    }
}
