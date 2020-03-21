package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回：
 *
 * [3,9,20,15,7]
 * 提示：
 * 节点总数 <= 1000
 *
 * 思路：bfs
 */
public class Medium32 {

	public int[] levelOrder(TreeNode root) {
		if (root==null)return new int[0];
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		TreeNode p;
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			p = queue.poll();
			res.add(p.val);
			if (p.left != null) {
				queue.offer(p.left);
			}
			if (p.right != null) {
				queue.offer(p.right);
			}
		}
		return res.stream().mapToInt(Integer::valueOf).toArray();
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	@Test
	public void test1() {

	}

}
