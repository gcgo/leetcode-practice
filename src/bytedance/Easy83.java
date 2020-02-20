package bytedance;

import org.junit.Test;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * 思路：没啥可说的，注意head循环结束条件
 */
public class Easy83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode q = head;
        while (q != null && q.next != null) {
            if (q.val == q.next.val) {
                q.next = q.next.next;
            } else {
                q = q.next;
            }
        }
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
        ListNode head = new ListNode(0);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(3);

        ListNode resNode = deleteDuplicates(head);
        ListNode q = resNode;
        while (q != null) {
            System.out.print(q.val);
            q = q.next;
        }

    }

}
