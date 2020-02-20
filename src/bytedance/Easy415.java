package bytedance;

import org.junit.Test;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 */
public class Easy415 {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;//进位
        while (i >= 0 || j >= 0) {//巧妙，不用单独考虑一个数组遍历完的情况了
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int tmp = n1 + n2 + carry;
            carry = tmp / 10;
            res.append(tmp>9?tmp-10:tmp);
            i--;
            j--;
        }
        if (carry == 1) res.append(1);//考虑最后还可能进位
        return res.reverse().toString();
    }

    @Test
    public void test1() {
    }
}
