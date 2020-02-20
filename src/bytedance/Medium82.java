package bytedance;

import org.junit.Test;

public class Medium82 {
    /*
     * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5
示例 2:

输入: 1->1->1->2->3
输出: 2->3
* 思路：设置虚拟头结点为新队列头，遍历老队列头，重复的话就跳过，不重复就连到新节点头后面

     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode tmp = dummy;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) { //
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
            } else {
                tmp.next = head;
                tmp = tmp.next;
            }
            head = head.next;
        }
        tmp.next = head;
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
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(4);

        ListNode head2 = new ListNode(1);
//		head2.next = new ListNode(2);

        ListNode res = deleteDuplicates(head1);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

}
