package crackingcode;

import org.junit.Test;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 *
 * 示例2:
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 *
 * 思路：双指针，一个指针更新新表，一个指针遍历老表
 * 这题不用排序，只是去重
 *
 */
public class Easy0201 {
	public ListNode removeDuplicateNodes(ListNode head) {
		ListNode dummy = new ListNode(-1);//新起一个头
		ListNode check = dummy;//游标指针，在新链表中遍历与head比较
		ListNode tmp;
		/*每次固定head，然后check从新头开始遍历，与head比较，重复则换下一个head，不重复则添加head*/
		while (head != null) {
			tmp = head.next;//先保存head.next
			check = dummy;//check归零位
			while (check.next != null && check.next.val != head.val) {
				/*如果重复，则停止比较,否则check向后继续*/
				check = check.next;
			}
			/*check.next == null表示新表比较完了，都没有重复的，可以将head加入
			 * check.next ！= null表示新表没遍历完，已经发现重复的了，head不能加入*/
			if (check.next == null) {
				check.next = head;
				head.next = null;
			}
			head = tmp;//head迭代
		}
		return dummy.next;
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
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(3);
		head.next.next.next.next = new ListNode(2);
		head.next.next.next.next.next = new ListNode(1);

		ListNode res = removeDuplicateNodes(head);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}

	}

}
