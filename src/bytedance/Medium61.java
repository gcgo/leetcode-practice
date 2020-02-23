package bytedance;

import org.junit.Test;

/**
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * <p>
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 * <p>
 * 思路：链表长度为l,则实际是移动了k%l次
 * 拿张纸画一画！！！！！！
 */
public class Medium61 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k == 0) {
            return head;
        }
        if (head == null || head.next == null) {
            return head;
        }
        int l = 1;
        ListNode p2 = head;
        while (p2.next != null) {//获得链表长度
            l++;
            p2 = p2.next;
        }
        k = k % l;//计算真实移动次数
        ListNode pHead = head;
        ListNode p1 = head;
        for (int i = 0; i < l - k - 1; i++) {
            p1 = p1.next;
        }
        p2.next = pHead;//尾连到头
        pHead = p1.next;//头到新头的位置
        p1.next = null;//新尾
        return pHead;//返回新头
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

        ListNode head2 = new ListNode(1);
//		head2.next = new ListNode(2);

        ListNode res = rotateRight(head1, 2);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

}
