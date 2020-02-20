package bytedance;


import org.junit.Test;

/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 思路：
 *双指针迭代
 * 我们可以申请两个指针，第一个指针叫 pre，最初是指向 null 的。
 * 第二个指针 cur 指向 head，然后不断遍历 cur。
 * 每次迭代到 cur，都将 cur 的 next 指向 pre，然后 pre 和 cur 前进一位。
 * 都迭代完了(cur 变成 null 了)，pre 就是最后一个节点了。
 *
 */
public class Easy206 {
	public ListNode reverseList(ListNode head) {
		if (head==null) {
			return head;
		}
		//申请节点，pre和 cur，pre指向null
		ListNode pre = null;
		ListNode cur = head;
		ListNode tmp;
		while(cur!=null) {
			//记录当前节点的下一个节点
			tmp = cur.next;
			//然后将当前节点指向pre
			cur.next = pre;
			//pre和cur节点都前进一位
			pre = cur;
			cur = tmp;
		}
		return pre;


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
		head1.next.next = new ListNode(3);
		head1.next.next.next = new ListNode(4);
		head1.next.next.next.next = new ListNode(5);
		
		
		ListNode q =reverseList(head1);
		while(q!=null) {
			System.out.println(q.val);
			q=q.next;
		}

	}

}
