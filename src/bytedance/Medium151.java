package bytedance;

import org.junit.Test;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * 进阶：
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 * <p>
 * 思路:双指针，i从后向前遍历，停在第一个不为''的位置，j从i开始继续向前遍历，停在第一个为''的位置，
 * 则j+1到i这部分就是一个单词，加入sb里，注意单词键添加空格。
 */
public class Medium151 {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            while (i >= 0 && s.charAt(i) == ' ')i--;
            int j = i - 1;
            while (j >= 0 && s.charAt(j) != ' ') j--;
            sb.append(" ");
            sb.append(s, j + 1, i + 1);
            i = j - 1;
        }
        if (sb.length() > 0) sb.deleteCharAt(0);
        return sb.toString();
    }

    @Test
    public void test1() {
        System.out.println(reverseWords("the sky is blue"));
        System.out.println(reverseWords("a good   example"));
    }
}
