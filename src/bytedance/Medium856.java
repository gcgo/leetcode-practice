package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
 * <p>
 * () 得 1 分。
 * AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
 * (A) 得 2 * A 分，其中 A 是平衡括号字符串。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入： "()"
 * 输出： 1
 * 示例 2：
 * <p>
 * 输入： "(())"
 * 输出： 2
 * 示例 3：
 * <p>
 * 输入： "()()"
 * 输出： 2
 * 示例 4：
 * <p>
 * 输入： "(()(()))"
 * 输出： 6
 *  
 * 提示：
 * <p>
 * S 是平衡括号字符串，且只含有 ( 和 ) 。
 * 2 <= S.length <= 50
 * <p>
 * 思路：
 * 构建一个栈
 * 如果遇到(就往栈里面添加
 * 如果遇到)就去寻找最近的左括号抵消，同时计算里面的分数
 * 拿(()(()))示例, 栈结构变化如下
 * <p>
 * [(]                # 遇到 ( 往栈添加
 * [(, (]             # 继续添加
 * [(, 1]             # 遇到 ） 合成一个1
 * [(, 1, (]          # 遇到 ( 往栈添加
 * [(, 1, (, (]       # 继续添加
 * [(, 1, (, 1]       # 遇到 ） 合成一个1
 * [(, 1, 2]          # 遇到 ） ，结构就是（1）， 所以计算的话是 1 * 2
 * [6]                # 遇到 ） ，结构是（1，2）， 所以计算的话是 （1 + 2） * 2
 */
public class Medium856 {
    public int scoreOfParentheses(String S) {
        if (S == null || S.equals("")) return 0;
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < S.length(); i++) {
            String c = S.charAt(i) + "";
            if (c.equals("(")) stack.push(c);//左括号直接入栈
            else if (c.equals(")")) {//右括号的话
                if (stack.peek().equals("(")) {//看栈顶是不是左括号
                    stack.pop();
                    stack.push("1");//是的话直接合并为1，入栈
                } else if (!stack.peek().equals("(")) {//栈顶不是左括号，那肯定是之前计算的分数
                    int tmp = 0;
                    while (!stack.peek().equals("(")) {//把所有分数出栈相加
                        tmp += Integer.parseInt(stack.pop());
                    }
                    stack.pop();//现在栈顶肯定是左括号，故弹出
                    stack.push(Integer.toString(tmp * 2));//分数乘以2入栈
                }
            }
        }
        int res = 0;//S遍历完栈中应该只有分数，所有分出出栈相加即可
        while (!stack.isEmpty()) res += Integer.parseInt(stack.pop());
        return res;
    }

    @Test
    public void test1() {
        System.out.println(scoreOfParentheses("()()"));
        System.out.println(scoreOfParentheses("(()(()))"));
    }
}
