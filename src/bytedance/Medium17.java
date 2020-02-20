package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * <p>
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 2：abc
 * 3:def
 * 4:ghi
 * 5:jkl
 * 6:mno
 * 7:pqrs
 * 8:tuv
 * 9:wxyz
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * <p>
 * 思路：字典树回溯
 */
public class Medium17 {
    private String[] letterMap = {
            "",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    private ArrayList<String> res;

    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();
        if (digits.equals(""))
            return res;

        findCombination(digits, 0, "");
        return res;
    }

    private void findCombination(String digits, int index, String s) {

        if (index == digits.length()) {
            res.add(s);
            return;
        }

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination(digits, index + 1, s + letters.charAt(i));
        }

    }

    public List<String> letterCombinations2(String digits) {

        res = new ArrayList<>();
        if (digits.equals(""))
            return res;

        findCombination2(digits, 0, new StringBuilder());
        return res;
    }

    private void findCombination2(String digits, int index, StringBuilder sb) {

        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }

        char c = digits.charAt(index);
        String letters = letterMap[c - '0'];
        for (int i = 0; i < letters.length(); i++) {
            findCombination2(digits, index + 1, sb.append(letters.charAt(i)));
            sb.deleteCharAt(sb.length()-1);
        }

    }

    @Test
    public void test1() {
        System.out.println(letterCombinations2("23"));
    }
}
