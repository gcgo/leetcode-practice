package javaoffer;

import org.junit.Test;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * 限制：
 * 0 <= 链表长度 <= 1000
 *
 * 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * 思路：虚拟头结点，重新构建。可以新链表每个节点都new，也可以去不断链接l1和l2的节点。
 *
 */
public class Easy25 {

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(-1);
		ListNode cur = dummy;

		ListNode cur_l1 = l1;
		ListNode cur_l2 = l2;

		while (cur_l1 != null && cur_l2 != null) {
			if (cur_l1.val <= cur_l2.val) {
				cur.next = cur_l1;
				cur_l1 = cur_l1.next;
			} else {
				cur.next = cur_l2;
				cur_l2 = cur_l2.next;
			}
			cur = cur.next;
		}

		if (cur_l1 != null) cur.next = cur_l1;
		if (cur_l2 != null) cur.next = cur_l2;

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

	}
}
