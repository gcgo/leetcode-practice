package bytedance;

import org.junit.Test;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 示例 :
 * <p>
 * 给定这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 思路：首先设置一个虚拟头结点，dummy-->head
 *使用4个指针：
 * n、p、c、start,初始都让他们等于dummy
 * (如果没有k个节点了，则就不用了倒叙了)
 * p在循环中始终是固定的指向p；
 * c = p.next, 代表了当前正要移动的指针；
 * start表示下一个循环开始的节点
 *
 */
public class Hard25 {
    public ListNode reverseKGroup(ListNode head, int k) {
		ListNode dummy = new ListNode(0), start = dummy;
		dummy.next = head;
		while(true) {
			ListNode p = start, c, n = p;
			start = p.next;//这个点会在第一段倒序之后变成最后一个点，所以同他来记录下一个循环的开始
			for(int i = 0; i < k && n != null; i++) n = n.next;//n向前走k步
			if(n == null) break;//n==null的话，就不用了反转了
			for(int i = 0; i < k-1; i++) {//循环看不懂的话，就想java的值传递！！！！
				c = p.next;
				p.next = c.next;
				c.next = n.next;
				n.next = c;
			}
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
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = reverseKGroup(head, 3);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
