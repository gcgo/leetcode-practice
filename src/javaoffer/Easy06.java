package javaoffer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * 限制：
 * 0 <= 链表长度 <= 10000
 *
 * 思路：
 *
 */
public class Easy06 {

	public int[] reversePrint(ListNode head) {
		Deque<Integer> stack = new ArrayDeque<>();
		ListNode cur = head;
		while (cur != null) {
			stack.push(cur.val);
			cur = cur.next;
		}
		int[] res = new int[stack.size()];
		int i = 0;
		while (!stack.isEmpty()) {
			res[i] = stack.pop();
			i++;
		}
		return res;
	}
	//思路2，反正用栈也是要扫描两遍，还不如第一遍计算链表长度，然后构建数组，第二遍再遍历链表，对数组从后向前赋值


	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	@Test
	public void test1() {
	}
}
