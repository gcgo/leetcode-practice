package javaoffer;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 栈排序。 编写程序，对栈进行排序使最小元素位于栈顶。
 * 最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 *
 * 示例1:
 *  输入：
 * ["SortedStack", "push", "push", "peek", "pop", "peek"]
 * [[], [1], [2], [], [], []]
 *  输出：
 * [null,null,null,1,null,2]
 *
 * 示例2:
 *  输入：
 * ["SortedStack", "pop", "pop", "push", "pop", "isEmpty"]
 * [[], [], [], [1], [], []]
 *  输出：
 * [null,null,null,null,null,true]
 * 说明:
 * 栈中的元素数目在[0, 5000]范围内。
 *
 * 思路：
 *
 */
public class Medium0305 {
	static class SortedStack {
		Deque<Integer> s1;
		Deque<Integer> s2;

		public SortedStack() {
			s1 = new ArrayDeque<>();
			s2 = new ArrayDeque<>();
		}

		public void push(int val) {
			if (s1.isEmpty()) s1.push(val);
				/*始终保持s1自顶向下升序，利用s2把s1中小于val的暂时保存，val找到合适位置后，在将s2元素存回s1*/
			else if (!s1.isEmpty()) {
				while (!s1.isEmpty() && val > s1.peek()) s2.push(s1.pop());
				s1.push(val);
				while (!s2.isEmpty()) s1.push(s2.pop());
			}
		}

		public void pop() {
			if (!s1.isEmpty()) s1.pop();
		}

		public int peek() {
			if (s1.isEmpty()) return -1;
			return s1.peek();
		}

		public boolean isEmpty() {
			return s1.isEmpty();
		}
	}

	public static void main(String[] args) {

	}

}

