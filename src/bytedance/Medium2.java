package bytedance;

import org.junit.Test;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class Medium2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);//用于保存结果,虚拟头结点
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;//进位
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;//都从个位开始取值
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
//          carry = sum / 10;
            carry = sum > 9 ? 1 : 0;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;//指针后移
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {//这是考虑结果位数增加的情况，比如89+11=100,加数都是两位数，结果是三位数
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;

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
        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(2);

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(8);
        head2.next.next = new ListNode(1);

        ListNode res = addTwoNumbers(head1, head2);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;

        }
    }
}
