package bytedance;

import org.junit.Test;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * 题目先理解明白，比如“)((())”的最长有效子串为“(())”长度为4，所以他所谓的子串，就是全部能够消除，左右括号数量相同，能抵消
 * <p>
 * 思路：栈
 * 我们首先将 -1 放入栈顶。
 * <p>
 * 对于遇到的每个 ‘(’ ，我们将它的下标放入栈中。
 * 对于遇到的每个 ‘)’ ，我们弹出栈顶的元素并将当前元素的下标与弹出元素下标作差，得出当前有效括号字符串的长度。
 * 通过这种方法，我们继续计算有效子字符串的长度，并最终返回最长有效子字符串的长度。
 */
public class Hard32 {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();//用来存'('的index
        int start = 0;//记录合法括号串的起始位置
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty())
                    start = i + 1;//栈为空证明之前有匹配了的计算过了，而且现在是')'，所以start至少是i+1。
                else {
                    stack.pop();//弹出栈顶位置
                    //
                    res=stack.isEmpty()?Math.max(res,i-start+1):Math.max(res,i-stack.peek());
                }
            }
        }
        return res;
    }

    @Test
    public void test1() {
        System.out.println(longestValidParentheses(")((())"));
    }
}
