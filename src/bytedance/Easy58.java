package bytedance;

import org.junit.Test;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。
 * <p>
 * 如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指仅由字母组成、不包含任何空格的 最大子字符串。
 * <p>
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 * <p>
 * 思路：双指针,从后往前，i先找第一个不等于空格的位置，j接着i找第一个等于空格的位置。最后长度就是i-j
 * 注意边界情况！！
 */
public class Easy58 {
    public int lengthOfLastWord(String s) {
        if (s == null) return 0;
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') i--;
        if (i < 0) return 0;
        int j = i - 1;
        while (j >= 0 && s.charAt(j) != ' ') j--;
        return j >= 0 ? i - j : i + 1;
    }

    @Test
    public void test1() {
        System.out.println(lengthOfLastWord("Hello World"));
        System.out.println(lengthOfLastWord("World"));
        System.out.println(lengthOfLastWord("    "));
    }
}
