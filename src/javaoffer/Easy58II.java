package javaoffer;

import org.junit.Test;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
 * 请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，
 * 该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *
 * 1 <= k < s.length <= 10000
 * <p>
 * 思路:
 */
public class Easy58II {
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        return sb.append(s.substring(n)).append(s, 0, n).toString();
    }

    @Test
    public void test1() {
        System.out.println(reverseLeftWords("lrloseumgh",6));
    }
}
