package crackingcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。
 * 返回一个包含所有深度的链表的数组。
 *
 * 示例：
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 *
 *思路：层序遍历，bfs,设计两个变量记录当前层还有几个节点，以及下一层有几个节点
 *
 */
public class Medium0403 {

	public ListNode[] listOfDepth(TreeNode tree) {
		Queue<TreeNode> queue = new LinkedList<>();
		/*先用list保存，最后转换成数组输出，输出格式不影响算法逻辑*/
		List<ListNode> resList = new ArrayList<>();
		/*一个该层的头结点，一个游标用于添加元素*/
		ListNode tmp = null;
		ListNode cur = null;
		/*分别定义当前层节点数，和下一层节点数*/
		int currentLevelNum = 1;//初始第一层只有头结点
		int nextLevelNum = 0;
		/*bfs开始*/
		queue.offer(tree);
		while (!queue.isEmpty()) {
			/*出队一个当前层数量就减一*/
			TreeNode root = queue.poll();
			currentLevelNum--;
			/*初始化链表头*/
			if (tmp == null) {
				tmp = new ListNode(root.val);
				cur = tmp;
			} else {
				cur.next = new ListNode(root.val);
				cur = cur.next;
			}
			/*bfs,有左孩子，下一层节点数加1*/
			if (root.left != null) {
				queue.offer(root.left);
				nextLevelNum++;
			}
			/*bfs,有右孩子，下一层节点数加1*/
			if (root.right != null) {
				queue.offer(root.right);
				nextLevelNum++;
			}
			/*若当前层遍历完了，则保存当前链表，即该层遍历完了
			 * 更新当前层数量为下一层数目，下一层计数器清零，链表清零*/
			if (currentLevelNum == 0) {
				resList.add(tmp);
				currentLevelNum = nextLevelNum;
				nextLevelNum = 0;
				tmp = null;
				cur = null;
			}
		}
		/*输出格式问题，不重要*/
		ListNode[] res = new ListNode[resList.size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = resList.get(i);
		}
		return res;
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	@Test
	public void test() {
		int[] tree = new int[]{-10, -3, 0, 5, 9};

	}

}

