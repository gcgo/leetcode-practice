package bytedance;

import org.junit.Test;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 思路：
 * 设置一个虚拟头结点，一个游标节点，然后比较两个链表，从第一个开始，谁小谁就先链接到虚拟头结点上去。
 */
public class Easy21 {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		// 虚拟头节点
		ListNode prehead = new ListNode(-1);

		ListNode prev = prehead;//游标，负责链接
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;//l1可以安心往后走了
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;//游标向前移动
		}

		//走到这肯定是由一个链表为空了，则把非空那个连接到后面。
		prev.next = l1 == null ? l2 : l1;

		return prehead.next;

	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	@Test
	public void test1() {
		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(2);
		head1.next.next = new ListNode(4);

		ListNode head2 = new ListNode(1);
		head2.next = new ListNode(3);
		head2.next.next = new ListNode(4);

		ListNode res = mergeTwoLists(head1, head2);
		while (res!=null){
			System.out.println(res.val);
			res=res.next;
		}
	}

}
