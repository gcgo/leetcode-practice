package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使用栈实现队列的下列操作：
 * <p>
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 * 示例:
 * <p>
 * MyQueue queue = new MyQueue();
 * <p>
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 * 说明:
 * <p>
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 * <p>
 * 思路：两个栈，一个负责入队，一个负责出队
 * 队列是先进先出，所以入队时我们让所有数入栈，
 * 出队时将入队栈全部出栈存到出对栈去，相当于顺序翻转。然后对出队栈出栈
 */
public class Easy232 {
    class MyQueue {
        Deque<Integer> input = new ArrayDeque<>();
        Deque<Integer> output = new ArrayDeque<>();

        /**
         * Initialize your data structure here.
         */
        public MyQueue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            input.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            peek();//数据转移逻辑卸载了peek里
            return output.pop();
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (output.isEmpty())
                while (!input.isEmpty())
                    output.push(input.pop());//数据全部从input移动到output
            return output.peek();
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return input.isEmpty() && output.isEmpty();
        }
    }

    @Test
    public void test1() {

    }
}
