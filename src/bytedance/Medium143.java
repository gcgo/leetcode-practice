package bytedance;

import org.junit.Test;

/**
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 示例 1:
 * <p>
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * <p>
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 * <p>
 * 思路：三步走
 * 1找中间结点，把链表分成两半；
 * 2把后半段逆序；
 * 3分别从前后半段交叉取元素合并。
 */
public class Medium143 {
    public void reorderList(ListNode head) {
        //特殊值
        if (head==null||head.next==null)return;
//  1、找中间结点，把链表分成两半；
        //快慢指针找中点
        ListNode slow = head;
        ListNode slowPre = head;//慢指针前置节点，用于删除
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slowPre = slow;
            slow = slow.next;
        }
        slowPre.next = null;

//  2、把后半段逆序；
        //此时slow是后半段开头
        ListNode pre = slow;
        ListNode cur = slow.next;//游标
        ListNode next;
        pre.next = null;//头变尾
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //最后pre是逆序后头结点。
//  3、分别从前后半段交叉取元素合并。
        ListNode cur1 = head;
        ListNode cur2 = pre;
        ListNode tmp;
        while (cur1.next != null) {
            tmp = cur1.next;
            cur1.next = cur2;
            cur1 = tmp;

            tmp = cur2.next;
            cur2.next = cur1;
            cur2 = tmp;
        }
        if (cur2 != null)
            cur1.next = cur2;
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
//        head1.next.next.next.next.next = new ListNode(6);

        reorderList(head1);
        while (head1 != null) {
            System.out.println(head1.val);
            head1 = head1.next;
        }
    }

}
