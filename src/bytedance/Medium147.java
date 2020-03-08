package bytedance;

import org.junit.Test;

/**
 * 对链表进行插入排序。
 * <p>
 * 思路：插入排序算法：
 *
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *
 * 思路：网友牛逼！！
 * 虚拟头结点，游标pre，head
 * head每次固定，pre从第一个元素开始遍历找到第一个大于head的元素，将head插到pre前面。
 */
public class Medium147 {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        /*这里为什么没有将dummy.next=head
        * 因为他的插入不是原地插入，而是相当于dummy另起一队，可以认为与head是平行的两个队列。
        * dummy每次从head上取一个下来，dummy加一个，head就减少一个，每次取过来以后，排序
        * 这样head取完了，dummy也就排好序了
        * 所以一上来在第一次循环中，就会将head的第一个元素取下来挂在dummy后面，从第二个元素开始再来就排序了*/
        ListNode pre = dummy;

        while (head != null) {
            ListNode temp = head.next;//保存head的后继
            /*按理说pre每次都应该从dummy出发，但是插入排序特点就是每次我都认为前面的排好序了
             * 所以pre在上一轮停留位置上的元素如果小于当前这轮要比较的元素，那证明pre之前的也小于它，
             * 他就排在pre后面就好了，节省了pre从dummy遍历的时间*/
            if (pre.val >= head.val) pre = dummy;
            while (pre.next != null && pre.next.val < head.val) {
                //如果pre.next<head证明head不应该插在它前面，pre继续往后找
                pre = pre.next;
            }
            /*此处pre.next>head了，head就应该插在pre.next前面*/
            head.next = pre.next;//head.next已经保存在temp那了，所以先操作他
            pre.next = head;
            head = temp;//head向后移动一步
        }
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
