package javaoffer;

import org.junit.Test;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。
 * 在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 *
 * 思路：原地复制，在每一个节点后面复制自己，然后再填充random，最后再断开。
 *这道题解法和Spring解决循环依赖的原理很像，先构造对象，所有对象齐了，最后再统一填充属性
 */
public class Medium35 {

	public Node copyRandomList(Node head) {
		if (head == null) {
			return null;
		}
		Node cur = head;
		Node next;
		//1复制新链表，只复制next指针，不复制random指针
		while (cur != null) {
			next = cur.next;//保存一下
			Node newNode = new Node(cur.val);//复制
			newNode.next = cur.next;//插入到cur后面
			cur.next = newNode;
			cur = next;
		}
		cur = head;
		//2复制random指针
		while (cur != null) {
			next = cur.next.next;
			//cur.random是老链表，cur.random.next才是新链表
			cur.next.random = cur.random != null ? cur.random.next : null;
			cur = next;
		}
		//3把复制链表和源链表分开
		cur = head;
		Node newHead = cur.next;
		Node copyCur = null;
		while (cur != null) {
			//record the next node
			next = cur.next.next;
			copyCur = cur.next;
			cur.next = next;
			copyCur.next = next != null ? next.next : null;
			cur = next;
		}
		return newHead;
	}

	//Definition for a Node.
	class Node {
		int val;
		Node next;
		Node random;

		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}

	@Test
	public void test1() {

	}
}

