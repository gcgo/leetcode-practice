package bytedance;

import org.junit.Test;

/**
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 思路：归并排序，在链表排序时空间复杂度为o（1）
 * https://www.cnblogs.com/qiaozhoulin/p/4585401.html
 */
public class Medium148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {
        if (head.next == null)//归并排序分组分组。。。最后就剩一个元素
            return head;
        ListNode slow, fast, preSlow;
        slow = head;
        fast = head;
        preSlow = null;
        while (fast != null && fast.next != null) {//注意快慢指针的条件！！
            fast = fast.next.next;
            preSlow = slow;
            slow = slow.next;
        }
        preSlow.next = null;//分成两段
        ListNode l, r;
        l = mergeSort(head);//递归,归并排序
        r = mergeSort(slow);
        return merge(l, r);//合并两个有序数组
    }

    private ListNode merge(ListNode l, ListNode r) {
        ListNode dummy=new ListNode(0);
        ListNode temp=dummy;
        while(l!=null&&r!=null)
        {
            if(l.val<=r.val)
            {
                temp.next=l;
                temp=temp.next;
                l=l.next;
            }
            else
            {
                temp.next=r;
                temp=temp.next;
                r=r.next;
            }
        }
        if(l!=null)
            temp.next=l;
        if(r!=null)
            temp.next=r;
        return dummy.next;
    }

    //**********************************************************************
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //*********************************************************************
    @Test
    public void test1() {

    }
}
