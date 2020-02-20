package bytedance;

import org.junit.Test;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class Easy20 {
    public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i<s.length(); i++) {
			if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{')
				stack.push(s.charAt(i));
			else if(s.charAt(i) == ')' && !stack.empty() && stack.peek() == '(')
				stack.pop();
			else if(s.charAt(i) == ']' && !stack.empty() && stack.peek() == '[')
				stack.pop();
			else if(s.charAt(i) == '}' && !stack.empty() && stack.peek() == '{')
				stack.pop();
			else
				return false;
		}
		// 考虑s="["单一开括号情况
		return stack.empty();
    }

    @Test
    public void test1() {
        System.out.println(isValid("]"));
    }
}
