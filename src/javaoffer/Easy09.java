package javaoffer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 * 思路：
 *
 */
public class Easy09 {

	class CQueue {
		private Deque<Integer> s1;
		private Deque<Integer> s2;

		public CQueue() {
			s1 = new ArrayDeque<>();
			s2 = new ArrayDeque<>();
		}

		public void appendTail(int value) {
			while (!s2.isEmpty()) s1.push(s2.pop());
			s1.push(value);
		}

		public int deleteHead() {
			while (!s1.isEmpty()) s2.push(s1.pop());
			return s2.isEmpty()?-1:s2.pop();
		}
	}

	@Test
	public void test1() {
	}
}
