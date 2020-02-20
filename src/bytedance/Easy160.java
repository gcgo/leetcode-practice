package bytedance;

import org.junit.Test;

/**
 * 返回两个链表的交点，没有交点返回null
 * <p>
 * 思路：定义两个指针，分别从两个链表头开始走，走到头了就转到另一个链表接着走，
 * 若相交，链表A： a+c, 链表B : b+c. a+c+b+c = b+c+a+c 。则会在公共处c起点相遇。
 * 若不相交，a +b = b+a 。因此相遇处是NULL
 */
public class Easy160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode ha = headA, hb = headB;
        while (ha != hb) {
            ha = ha != null ? ha.next : headB;
            hb = hb != null ? hb.next : headA;
        }
        return ha;
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
//        ListNode head1 = new ListNode(0);
//        head1.next = new ListNode(1);
//        ListNode head2 = new ListNode(0);
//        ListNode headm = new ListNode(20);
//        headm.next = new ListNode(0);
//        head2.next = new ListNode(2);
//        head1.next.next = headm;
//        headm.next.next = new ListNode(1);
//        head2.next.next = new ListNode(2);
//        headm.next.next.next = new ListNode(3);
//        head2.next.next.next = headm;

		ListNode head1 = new ListNode(1);
		head1.next = new ListNode(2);
		ListNode head2 = new ListNode(10);
		 head2.next = new ListNode(11);
		 head2.next.next = new ListNode(12);

        System.out.println(getIntersectionNode(head1, head2));

    }

}
