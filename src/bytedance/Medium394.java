package bytedance;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例:
 * <p>
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 * <p>
 * 思路：两个辅助栈，一个存倍数，一个存对应“[”的res。
 * 遇到数字：用变量先记录倍数；
 * 数字完了应该就是【：则把当前res入栈，把当前倍数入栈
 * 遇到其他字母：则存进res
 * 遇到】：则循环复制当前res，再res+=栈中res。
 *
 */
public class Medium394 {
    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;//倍数
        Deque<Integer> stack_multi = new LinkedList<>();//辅助栈存倍数
        Deque<String> stack_res = new LinkedList<>();//辅助栈存结果
        for (Character c : s.toCharArray()) {//遍历字符串s
            if (c == '[') {//如果是左边括号
                stack_multi.push(multi);//把倍数存进去
                stack_res.push(res.toString());//把当前的结果存进去
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.pop();//弹出对应当前"]"的倍数作为循环次数，循环复制的是res
                for (int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.pop() + tmp);//res复制完了，再从栈里取出上一个res合并。
            } else if (c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");//考虑数字类似23[]这种情况
            else res.append(c);
        }
        return res.toString();//最后的结果保存在res里。
    }

    //**************************************************************************
    @Test
    public void test1() {
        System.out.println(decodeString("3[a2[c]]"));
    }

}
