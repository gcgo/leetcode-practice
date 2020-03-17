package crackingcode;

import org.junit.Test;

/**
 * 给定一个有环链表，实现一个算法返回环路的开头节点。
 * 有环链表的定义：在链表中某个节点的next元素指向在它前面出现过的节点，则表明该链表存在环路。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：tail connects to node index 0
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：no cycle
 * 解释：链表中没有环。
 * 进阶：
 * 你是否可以不用额外空间解决此题？
 *
 * 思路：快慢指针
 *
 */
public class Medium0208 {
	public ListNode detectCycle(ListNode head) {
		ListNode slow = head;
		ListNode fast = head;
		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if (slow == fast) break;
		}
		/*先判断有没有环*/
		if (fast == null || fast.next == null) return null;
		/*有环继续找入口*/
		fast = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}
		/*当他们再次相遇就是环的入口*/
		return fast;
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
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = head.next;

		ListNode res = detectCycle(head);
		System.out.print(res.val);
	}
}

