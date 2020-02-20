package bytedance;

import org.junit.Test;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 *
 * 进阶：
 *
 * 你能尝试使用一趟扫描实现吗？
 *
 * 思路：快慢指针法，从dummy开始走，快指针比慢指针先走n步，然后再一起走，
 * 当快指针到达尾部后，慢指针指向了倒数第n个节点的前一个，删除后面那个即可。
 *
 */
public class Medium19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null)
            return null;
        ListNode dummy = new ListNode(0);//设置虚拟头结点，方便操作
        ListNode fast = dummy, slow = dummy;
        dummy.next = head;
        for (int i = 0; i < n; ++i)
            fast = fast.next;//快指针先走n步
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
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
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);


        ListNode res = removeNthFromEnd(head1,2);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;

        }
    }
}
