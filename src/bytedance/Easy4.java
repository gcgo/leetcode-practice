package bytedance;

import org.junit.Test;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 进阶：尝试不要转换字符串
 * <p>
 * 思路：反转x，比较x和反转后的x，不用考虑int溢出问题，因为要是回文数字肯定不会溢出。
 */
public class Easy4 {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        else
            return reverse(x) == x;
    }

    private int reverse(int x) {
        int res = 0;
        while (x > 0) {
            if (res > Integer.MAX_VALUE / 10) return -1;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    @Test
    public void test1() {
		System.out.println(isPalindrome(121));
    }
}
