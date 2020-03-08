package javaoffer;

import org.junit.Test;

/**
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 * 注意：本题相对原题稍作改动
 * 示例：
 *
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * 说明：
 * 给定的 k 保证是有效的。
 *
 * 思路：快慢指针，快的比慢的先走k步。
 *
 */
public class Easy0202 {
	public int kthToLast(ListNode head, int k) {
		ListNode slow = head;
		ListNode fast = head;
		while (k > 0) {
			fast = fast.next;
			k--;
		}
		while (fast!=null){
			slow=slow.next;
			fast=fast.next;
		}
		return slow.val;
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	@Test
	public void test1() {
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		System.out.println(kthToLast(head,5));
	}

}
