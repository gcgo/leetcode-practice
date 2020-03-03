package bytedance;

/**
 * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
 *
 * 进阶:
 * 如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？
 *
 * 示例:
 *
 * // 初始化一个单链表 [1,2,3].
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(2);
 * head.next.next = new ListNode(3);
 * Solution solution = new Solution(head);
 *
 * // getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
 * solution.getRandom();
 *
 * 思路：蓄水池抽样
 * 比如有一个数据流，现在来了一个数a1，我选择它的概率是1，因为就他一个；
 * 数据流继续前进，又来了一个数a2，那我选择新数的概率就是1/2，保留当前数的概率就是1/2；
 * 同理，a3来了，选择a3的概率是1/3，保留当前数的概率是2/3；
 * 。。。。经过一段时间
 * an来了，选择an的概率就是1/n，保留当前数的概率就是n-1/n
 * 总结一下就是：
 * 目前来了i个数，不知道还有几个数要来，那么我选择第i个数的概率就是:(1/i)*(1-(1/i+1))*(1-(1/i+2))*...*(1-(1/n))
 * 化简一下就是1/n，所以无论n有多少，我抽样的概率都是1/n
 *
 */
public class Medium382 {
	class Solution {

		private ListNode head;

		/** @param head The linked list's head.
		Note that the head is guaranteed to be not null, so it contains at least one node. */
		public Solution(ListNode head) {
			this.head = head;
		}

		/** Returns a random node's value. */
		public int getRandom() {
			//以1的概率选择该元素head
			ListNode current = head;
			ListNode temp = head.next;
			int len = 2;//即蓄水池流出来2个元素
			while (temp != null){
				//以1/len的概率选择元素temp
				//从1，2，3，4，5...len 中随机选择一个元素
				//random()产生的是[0,1)的double型的数
				int target = 1 + (int) (Math.random() * len);
				if (target == len)//相当于选择第i个数
					current = temp;//定位到第i个数
				//temp一直向后走，蓄水池一直出新数，同时保留现在的数和选择新的数的概率一直在更新，直到temp走到头，概率来到1/n
				temp = temp.next;
				len++;
			}
			return current.val;//通过这种方法，遍历完了，取到的那个数，概率就是1/len
		}
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static void main(String[] args) {

	}

}
