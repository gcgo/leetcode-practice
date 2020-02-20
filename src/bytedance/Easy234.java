package bytedance;

import org.junit.Test;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * 思路：
 * 找到前半部分链表的尾节点。
 * 反转后半部分链表。
 * 判断是否为回文。
 * 恢复链表。
 * 返回结果。
 */
public class Easy234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head,preSlow=head;
        //1、找到链表的中点，链表长度奇偶不影响
        while (fast != null && fast.next != null) {
        	preSlow=slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        preSlow.next=null;//断开链表
        //2、将slow之后链表进行断开且反转，最后翻转完成之后pre指向反转链表的头节点
        ListNode pre = slow;
        ListNode cur = slow.next;
        slow.next = null;//头变尾
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }//反转完pre是新头
        //3、前后链表进行比较，注意若为奇数链表，后半部分回比前部分多1一个节点，然而我们只比较相同长度的节点值，巧妙地避开这点判断
		ListNode curHead=head;
        while (curHead != null && pre != null) {
            if (curHead.val != pre.val) return false;
			curHead = curHead.next;
            pre = pre.next;
        }
        return true;
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
        head1.next.next.next = new ListNode(2);
        head1.next.next.next.next = new ListNode(1);

        System.out.println(isPalindrome(head1));

    }

}
