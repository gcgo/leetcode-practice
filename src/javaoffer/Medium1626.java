package javaoffer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现一个基本的计算器来计算简单的表达式字符串。
 * 表达式字符串可以包含左括号 ( 和右括号 )，加号 + 和减号 -，非负 整数和空格 。
 * 表达式字符串只包含非负整数， +, -, *, / 操作符，左括号 ( ，右括号 )和空格 。整数除法需要向下截断。
 * 你可以假定给定的字符串总是有效的。所有的中间结果的范围为 [-2147483648, 2147483647]。
 *
 * 一些例子：
 *
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 *  
 * 注：不要 使用内置库函数 eval。
 *
 * 思路：三步实现，加减、乘除、括号
 */
public class Medium1626 {

    public int calculate(String s) {
        Queue<Character> queue = new LinkedList<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') queue.offer(c);
        }
        queue.offer('+');
        return calculate(queue);
    }

    private int calculate(Queue<Character> queue) {
        char sign = '+';
        int num = 0;
        //stack用于保存局部结果，最后用于相加
        Deque<Integer> stack = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            if (Character.isDigit(c)) {//如果c是数字，则赋值给num
                num = 10 * num + c - '0';
            } else if (c == '(') {//如果是左括号，则递归
                num = calculate(queue);
            } else {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(num * stack.pop());//乘法优先级高
                } else if (sign == '/') {
                    stack.push(stack.pop()/num);
                }
                num = 0;
                sign = c;
                /*当是右括号时跳出循环*/
                if (c == ')') break;
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    //*********************************************************************************
    @Test
    public void test1() {
        System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
    }
}
