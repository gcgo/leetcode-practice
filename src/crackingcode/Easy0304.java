package crackingcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 实现一个MyQueue类，该类用两个栈来实现一个队列。
 * 示例：
 * MyQueue queue = new MyQueue();
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 * 说明：
 * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size 和 is empty 操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
 *
 * 思路：栈是先入后出，队是先入先出，所以两个栈一个负责入队，一个负责出队。无论入队还是出队元素都需要来回倒腾
 *
 */
public class Easy0304 {
	static class MyQueue {
		Deque<Integer> s1;
		Deque<Integer> s2;

		/** Initialize your data structure here. */
		public MyQueue() {
			s1 = new ArrayDeque<>();
			s2 = new ArrayDeque<>();
		}

		/** Push element x to the back of queue. */
		public void push(int x) {
			while (!s2.isEmpty())
				s1.push(s2.pop());
			s1.push(x);
		}

		/** Removes the element from in front of queue and returns that element. */
		public int pop() {
			while (!s1.isEmpty())
				s2.push(s1.pop());
			return s2.pop();
		}

		/** Get the front element. */
		public int peek() {
			while (!s1.isEmpty())
				s2.push(s1.pop());
			return s2.peek();
		}

		/** Returns whether the queue is empty. */
		public boolean empty() {
			return s1.isEmpty() && s2.isEmpty();
		}
	}

	public static void main(String[] args) {
	}

}

