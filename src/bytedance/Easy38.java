package bytedance;

import org.junit.Test;

/**
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
 * <p>
 * 注意：整数序列中的每一项将表示为一个字符串。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 解释：这是一个基本样例。
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 * 解释：当 n = 3 时，序列是 "21"，其中我们有 "2" 和 "1" 两组，"2" 可以读作 "12"，
 * 也就是出现频次 = 1 而 值 = 2；类似 "1" 可以读作 "11"。所以答案是 "12" 和 "11" 组合在一起，也就是 "1211"。
 * <p>
 * 思路：题目描述不清。。应该是序列从1开始，之后每一个数描述前一个数的个数，若连续重复数字，则组合描述
 * 如第5个数111221，是这样描述第四个数1211：
 * 一个1，一个2，两个1。
 * 所以第n个数就是描述第n-1个数的。
 */
public class Easy38 {
    public String countAndSay(int n) {
        String str;
        StringBuilder res = new StringBuilder("1");
        for (int i = 1; i < n; i++) {
            str = res.toString();
            res = new StringBuilder();
            for (int j = 0; j < str.length(); ) {
                int count = 0, k = j;
                while (k < str.length() && str.charAt(k) == str.charAt(j)) {
                    k++;
                    count++;
                }
                res.append(count).append(str.charAt(j));
                j = k;
            }
        }
        return res.toString();
    }

    @Test
    public void test1() {

    }
}
