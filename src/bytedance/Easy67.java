package bytedance;

import org.junit.Test;

/**
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 思路：
 */
public class Easy67 {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int ca = 0;//进位
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int sum = ca;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;//防止数组越界
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
//            int s = sum > 1 ? sum - 2 : sum;//和下面一样，只是没有用%求余数
//            ans.append(s);
//            ca = sum > 1 ? 1 : 0;
            ans.append(sum % 2);
            ca = sum / 2;
        }
        ans.append(ca == 1 ? ca : "");//最后如果最高位有进位再加上
        return ans.reverse().toString();
    }

    @Test
    public void test1() {
        System.out.println(addBinary("1010","1011"));
    }
}
