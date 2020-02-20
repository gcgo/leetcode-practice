package bytedance;

import org.junit.Test;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 * 思路：链表实现,参考国际版leetcode用户ivtoskov解法：每个节点保存一个它之前的最小值，绝了绝了绝了！！！！！！
 */
public class Easy155 {
    class MinStack {
        private ListNode head;

        public MinStack() {
        }

        public void push(int x) {
            if (head == null)
                head = new ListNode(x, x);
            else {
                ListNode headNew = new ListNode(x, Math.min(head.min, x));//更新最小值
                headNew.next = head;
                head = headNew;
            }
        }

        public void pop() {//每个节点自带当前最小值，否则pop后还得遍历一遍更新最小值。。。
            if (head != null) head = head.next;
        }

        public int top() {
            if (head != null) return head.val;
            else
                throw new NullPointerException("当前栈中没有元素");
        }

        public int getMin() {
            return head.min;
        }

        class ListNode {
            int val;
            int min;
            ListNode next;

            ListNode(int x) {
                val = x;
                next = null;
            }

            ListNode(int x, int min) {//每个节点保存一个它之前的最小值，绝了绝了绝了！！！！！！
                val = x;
                this.min = min;
                next = null;
            }
        }
    }

    @Test
    public void test1() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
