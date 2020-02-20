package bytedance;

import org.junit.Test;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * 思路：没什么可说的。。。
 */
public class Medium24 {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curPre = dummy;
        ListNode cur = head;
        ListNode next;
        while (cur != null && cur.next != null) {
            next = cur.next.next;
            curPre.next = cur.next;
            cur.next.next = cur;
            cur.next = next;
            curPre = cur;
            cur = next;
        }

        return dummy.next;

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
//*****************************************************************************
    @Test
    public void test1() {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
		head1.next.next.next = new ListNode(4);
		head1.next.next.next.next = new ListNode(5);

		ListNode res = swapPairs(head1);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

}
