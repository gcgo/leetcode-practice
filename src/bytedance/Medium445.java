package bytedance;

import org.junit.Test;

import java.util.Stack;

/**
 * 给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * <p>
 *  
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * 进阶:
 * <p>
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * <p>
 * 示例:
 * <p>
 * 输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 8 -> 0 -> 7
 * <p>
 * 思路：三栈，分别存两个数，再弹出来相加，存入第三个栈，再弹出形成链表，注意记录进位。
 */
public class Medium445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        Stack<Integer> stackRes = new Stack<>();

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        //入栈
        while (cur1 != null) {
            s1.push(cur1.val);
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            s2.push(cur2.val);
            cur2 = cur2.next;
        }

        //开始计算：
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int plus = 0;//进位
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int resAdd = s1.pop() + s2.pop();
            stackRes.push((resAdd + plus)>9?resAdd+plus-10:resAdd+plus);
            plus = resAdd+plus > 9 ? 1 : 0;//记录下一回合进位
        }

        if (!s1.isEmpty()) {
            while (!s1.isEmpty()) {
                int pop = s1.pop();
                int s1Next = (pop + plus) < 10 ? pop + plus : pop + plus - 10;
                stackRes.push(s1Next);
                plus = pop + plus > 9 ? 1 : 0;//记录下一回合进位
            }
        }
        if (!s2.isEmpty()) {
            while (!s2.isEmpty()) {
                int pop = s2.pop();
                int s2Next = (pop + plus) < 10 ? pop + plus : pop + plus - 10;
                stackRes.push(s2Next);
                plus = pop + plus > 9 ? 1 : 0;//记录下一回合进位
            }
        }
        if (plus > 0)
            stackRes.push(plus);
        while (!stackRes.isEmpty()) {
            cur.next = new ListNode(stackRes.pop());
            cur = cur.next;
        }

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
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        l1.next.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        ListNode res = addTwoNumbers(l1, l2);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }

    }
}
