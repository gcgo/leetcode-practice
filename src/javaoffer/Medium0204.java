package javaoffer;

import org.junit.Test;

/**
 * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。
 * 如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。
 * 分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 *
 * 示例:
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 *
 * 思路：元素不必保持相同的相对顺序。我们只需要确保小于基准点的元素必须位于比基准点大的元素之前。
 * 所以凡是小于X的节点全部插到头节点之前去。
 * 双指针：dummy用于头插，tail用于尾插
 *
 */
public class Medium0204 {
	public ListNode partition(ListNode head, int x) {
		ListNode dummy = new ListNode(-1);
		ListNode tail = dummy;
		ListNode tmp;
		while (head != null) {
			tmp = head.next;
			if (dummy.next != null && head.val < x) {//头插
				head.next = dummy.next;
				dummy.next = head;
			} else {//尾插，包括一开始dummy.next == null的情况
				tail.next = head;
				head.next = null;
				tail = tail.next;
			}
			head = tmp;
		}
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
		ListNode head = new ListNode(1);
		head.next = new ListNode(4);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(5);
		head.next.next.next.next.next = new ListNode(2);

		ListNode res = partition(head, 3);//2,2,1,5,3,4
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

}
