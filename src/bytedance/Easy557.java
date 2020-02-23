package bytedance;

import org.junit.Test;

/**
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc" 
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * <p>
 * 思路：
 */
public class Easy557 {
    public String reverseWords(String s) {
        String[] arrs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String arr : arrs) {
            for (int i = arr.length() - 1; i >= 0; i--) {
                sb.append(arr.charAt(i));
            }
            sb.append(" ");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    @Test
    public void test1() {
        System.out.println(reverseWords("Let's take LeetCode contest"));
    }
}
