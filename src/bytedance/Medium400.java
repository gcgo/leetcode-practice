package bytedance;

import org.junit.Test;

/**
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 * 注意:
 * n 是正数且在32为整形范围内 ( n < 231)。
 *
 * 示例 1:
 * 输入:
 * 3
 * 输出:
 * 3
 *
 * 示例 2:
 * 输入:
 * 11
 * 输出:
 * 0
 * 说明:
 * 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 * <p>
 * 思路：先确定区间，再确定数，再确定位。
 * 区间： 1-9, 10-99, 100-999, 1000-9999
 */
public class Medium400 {
    public int findNthDigit(int n) {
        int len = 1;//当前区间数字的长度
        long count = 9;//数字的个数
        int start = 1;//当前区间起始数字

        while (n > len * count) {//len * count就是当前区间字符的个数
            n -= len * count;//1-9字符9个，10-99字符180个。。。
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;//找到字符所在的那个数，商
        String s = Integer.toString(start);
        return s.charAt((n - 1) % len)-'0';//余数
    }

    //**************************************************************************
    @Test
    public void test1() {
        System.out.println(findNthDigit(129));
    }

}
