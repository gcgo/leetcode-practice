package javaoffer;

import org.junit.Test;

/**
 * 编写一个函数，检查输入的链表是否是回文的。
 *
 * 示例 1：
 * 输入： 1->2
 * 输出： false
 * 示例 2：
 * 输入： 1->2->2->1
 * 输出： true
 *  
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 思路：快慢指针，快走两步慢走一步，链表长度为奇数，慢指针指向最中间，偶数指向后半段开头
 * O(1)空间复杂度实现：遍历前半段时，让每个元素指向都相反，则完成了反转
 * 常规思路从中间开始将后半段入栈，在一个个弹出来后前半段比较，空间复杂度O(n)
 */
public class Easy0206 {
	public boolean isPalindrome(ListNode head) {
		ListNode slow = head, fast = head;
		ListNode rev = null;//前半段反转后的头结点
		while (fast != null && fast.next != null) {
			ListNode tmp = slow;
			slow = slow.next;
			fast = fast.next.next;
			tmp.next = rev;//指针转向
			rev = tmp;//头往后移
		}
		/*通过fast状态判断链表长度奇偶性
		 * fast != null证明长度为奇数，此时slow指向正中间那个元素，所以往后移动一位*/
		if (fast != null) slow = slow.next;
		/*此时链表rev是前半段反转后的结果，比较即可*/
		while (slow != null) {
			if (rev.val != slow.val) return false;
			slow = slow.next;
			rev = rev.next;
		}
		return true;
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
		head.next.next.next = new ListNode(2);
		head.next.next.next.next = new ListNode(1);

		System.out.println(isPalindrome(head));
	}
}

