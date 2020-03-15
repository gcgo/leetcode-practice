package bytedance;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * 思路：利用最小堆，把所有链表存进去，最小堆按照链表当前节点大小排序，这样每次堆顶都是最小的，弹出，链接在dummy后面，
 * 然后把弹出的节点的next继续入堆。
 *
 */
public class Hard23 {
    public ListNode mergeKLists(ListNode[] lists) {
        //极端情况处理
        int len = 0;
        if (lists == null || (len = lists.length) == 0) return null;
        if (len == 1) return lists[0];
        //2个或以上个链表的情况
        ListNode preHead = new ListNode(-1);
        ListNode cur = preHead;
        //按节点数值大小比较建立最小堆
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(len, Comparator.comparingInt(n -> n.val));
        for (ListNode list : lists) {
            if (list != null) minHeap.offer(list);//节点入队，自动排序
        }
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            cur.next = minNode;
            cur = cur.next;
            if (minNode.next != null) {//一个节点取出后，将他的next入队。
                minHeap.offer(minNode.next);
            }
        }
        return preHead.next;

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
    }
}
