package bytedance;

import org.junit.Test;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * <p>
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class Medium92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 空表返回
        if (head == null) {
            return null;
        }

        // cur移动到m
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;//同步和m递减，保持n-m不变
        }

        // con是前半段表尾，tail是后半段表位
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {//con==null只会出现在m=1的时候，即从头就开始反转
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;

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
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);


        ListNode head3 = new ListNode(1);

        ListNode res = reverseBetween(head1, 1, 5);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

}
