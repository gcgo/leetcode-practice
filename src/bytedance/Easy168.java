package bytedance;

import org.junit.Test;

/**
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 * 例如，
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *     27 -> AA
 *     28 -> AB
 *     ...
 * 示例 1:
 * 输入: 1
 * 输出: "A"
 *
 * 示例 2:
 * 输入: 28
 * 输出: "AB"
 *
 * 示例 3:
 * 输入: 701
 * 输出: "ZY"
 *
 * <p>
 * 思路：工作中遇到过，低位开始求，求对26的余数对应字母，但有一个问题
 * 对于26来说，余数是0，对应Z，而其他1-25对应A-Y，不统一。所以为了统一处理，需要把余数按顺序映射到A-Z上，方便直接+‘A’来得到字母
 * 所以对n来说余数是那样对应的，那么对n-1来说余数0就对应A，25就可以对应Z了，所以每次
 *
 */
public class Easy168 {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();
        while (n > 0) {
            n--;
            result.append((char)('A' + n % 26));
            n /= 26;
        }
        result.reverse();//倒过来
        return result.toString();
    }

    @Test
    public void test1() {
        System.out.println(convertToTitle(9999));
    }

}
