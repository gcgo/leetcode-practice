package bytedance;

import org.junit.Test;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 说明：不允许修改给定的链表。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * 思路：快慢指针，相遇则有环，让一个指针从相遇点，另一个从头开始走，再次相遇的位置即为环入口
 */
public class Medium142 {

    public ListNode detectCycle(ListNode head) {
        ListNode slow, fast;
        slow = head;
        fast = head;
		if (slow==null||slow.next==null) {//只有一个元素的情况
			return null;
		}
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) break;
        }
        if (fast != slow) return null;//即fast走到头为null了，使得循环跳出，即没有环
		//否则有环
		fast=head;
		while (fast!=slow){
			fast=fast.next;
			slow=slow.next;
		}
		return slow;
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
        ListNode head1 = new ListNode(9);
        head1.next = new ListNode(8);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = head1.next;

        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = head2;

        ListNode res = detectCycle(head2);
        System.out.print(res.val + " ");
    }

}
