package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 使用队列实现栈的下列操作：
 * <p>
 * push(x) -- 元素 x 入栈
 * pop() -- 移除栈顶元素
 * top() -- 获取栈顶元素
 * empty() -- 返回栈是否为空
 * 注意:
 * <p>
 * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
 * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
 * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
 * <p>
 * 思路：要么入队O(n),出队O(1)；要么出队O(n),入队O(1)
 */
public class Easy225 {
    class MyStack {
        private Queue<Integer> queue;

        /**
         * Initialize your data structure here.
         */
        public MyStack() {
            this.queue = new LinkedList<>();
        }

        /**
         * Push element x onto stack.
         */
        public void push(int x) {//相当于入栈一个元素后，不断出队入队，把新加入的放到队首
            queue.add(x);
            for (int i = 0; i < queue.size()-1; i++) {
                queue.add(queue.poll());
            }
        }

        /**
         * Removes the element on top of the stack and returns that element.
         */
        public int pop() {//由于入栈时把新加入的元素移动到了队首，所以出栈时直接弹出队首元素
            return queue.poll();
        }

        /**
         * Get the top element.
         */
        public int top() {
            return queue.peek();
        }

        /**
         * Returns whether the stack is empty.
         */
        public boolean empty() {
            return queue.isEmpty();
        }
    }

    @Test
    public void test1() {

    }
}
