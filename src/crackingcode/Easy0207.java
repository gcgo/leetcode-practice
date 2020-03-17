package crackingcode;

import org.junit.Test;

/**
 * 给定两个（单向）链表，判定它们是否相交并返回交点。
 * 请注意相交的定义基于节点的引用，而不是基于节点的值。
 * 换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，
 * 相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 * 注意：
 * 如果两个链表没有交点，返回 null 。
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * 思路：如果有交点一定是Y字形，他们有共同的尾结点，
 * 可以先把其中一个头尾相连，就变成了链表找环入口问题了。
 * 然后快慢指针，快指针一次走两步，慢指针一次走一步，有环一定会相遇，
 * 然后一个再从遭遇点开始，一个从头开始，一步一步遍历，再次相遇就是环入口，即交点
 *
 */
public class Easy0207 {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) return null;
		ListNode curA = headA;
		ListNode curB = headB;
		while (curA.next != null) curA = curA.next;
		while (curB.next != null) curB = curB.next;
		/*如果没有共同尾部，则不相交*/
		if (curA != curB) return null;
		ListNode tail = curA;//保存尾部用于之后还原
		curA.next = headA;//让链表A形成环
		/*问题转化为找环入口*/
		curA = curB = headB;
		while (true) {//curA慢指针，curB快指针
			curA = curA.next;
			curB = curB.next.next;
			if (curA == curB) break;//先移动再比较
		}
		curA = headB;//curA从头开始
		while (true) {
			if (curA == curB) break;//先比较再移动
			curA = curA.next;
			curB = curB.next;
		}
		/*此时curA==curB==交点，记得把环断开*/
		tail.next = null;
		return curA;
	}

	/*网友思路：双指针遍历，一步一格子，只要不相等就一直遍历，当一个为空了，就让他指向另外一个头部，继续遍历
	 * 这样如果他们有交点，最后肯定会相遇，没有交点，他们会同时等于null*/
	public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
		ListNode curA = headA;
		ListNode curB = headB;
		while (curA != curB) {
			if (curA != null) curA = curA.next;
			else curA = headB;
			if (curB != null) curB = curB.next;
			else curB = headA;
		}
        return curA;
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
		ListNode head = new ListNode(4);
		head.next = new ListNode(1);
		head.next.next = new ListNode(8);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);

		ListNode head2 = new ListNode(5);
		head2.next = new ListNode(0);
		head2.next.next = new ListNode(1);
		head2.next.next.next = head.next.next;

		ListNode head3 = head;

		System.out.println(getIntersectionNode(head, head2).val);
	}
}

