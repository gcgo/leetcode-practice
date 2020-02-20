package bytedance;

import org.junit.Test;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class Easy203 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode prev = dummy, cur = head;
        while (cur != null) {
            if (cur.val == val) prev.next = cur.next;
            else prev = cur;
            cur = cur.next;
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

    @Test
    public void test1() {
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(3);
        head1.next.next = new ListNode(2);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(3);

        ListNode pre = removeElements(head1, 3);
        while (pre != null) {
            System.out.print(pre.val);
            pre = pre.next;
        }
    }

}
