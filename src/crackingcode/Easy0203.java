package crackingcode;

import org.junit.Test;

/**
 * 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点。
 *
 * 示例：
 * 输入：单向链表a->b->c->d->e->f中的节点c
 * 结果：不返回任何数据，但该链表变为a->b->d->e->f
 *
 * 思路：替换法！按理说要删除一个元素需要知道它的前驱，然而现在只知道它本身。
 * 所以可以让它的值改成它.next的值，然后删除它.next，变相删除当前节点
 *
 */
public class Easy0203 {
	public void deleteNode(ListNode node) {
		node.val=node.next.val;
		node.next=node.next.next;
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

	}

}
