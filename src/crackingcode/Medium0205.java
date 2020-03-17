package crackingcode;

import org.junit.Test;

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 *
 * 示例：
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 *
 * 进阶：假设这些数位是正向存放的，请再做一遍。
 * 示例：
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 *
 * 思路：逐位相加，考虑进位
 *
 */
public class Medium0205 {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int ca = 0;//进位
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;
		while (l1 != null && l2 != null) {
			int add = l1.val + l2.val + ca;
			ca = 0;
			if (add > 9) {
				add = add - 10;
				ca = 1;
			}
			cur.next = new ListNode(add);
			cur = cur.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		while (l1 != null) {
			int add = l1.val + ca;
			ca = 0;
			if (add > 9) {
				add = add - 10;
				ca = 1;
			}
			cur.next = new ListNode(add);
			cur = cur.next;
			l1 = l1.next;
		}
		while (l2 != null) {
			int add = l2.val + ca;
			ca = 0;
			if (add > 9) {
				add = add - 10;
				ca = 1;
			}
			cur.next = new ListNode(add);
			cur = cur.next;
			l2 = l2.next;
		}
		if (ca == 1) cur.next = new ListNode(1);
		return dummy.next;
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
		ListNode head = new ListNode(7);
		head.next = new ListNode(1);
		head.next.next = new ListNode(6);

		ListNode head2 = new ListNode(5);
		head2.next = new ListNode(9);
		head2.next.next = new ListNode(2);

		ListNode res = addTwoNumbers(head, head2);

		while (res != null) {
			System.out.print(res.val);
			res = res.next;
		}
	}

}
