package javaoffer;

import org.junit.Test;

import java.util.*;

/**
 * 请实现一个函数按照之字形顺序打印二叉树，
 * 即第一行按照从左到右的顺序打印，
 * 第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 *
 * 提示：
 * 节点总数 <= 1000
 *
 * 思路：bfs
 */
public class Medium32III {

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) return res;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		int currentLevel = 1;
		int nextLevel = 0;
		boolean left2right = false;
		TreeNode p;
		List<Integer> tmp = new ArrayList<>();
		while (!queue.isEmpty()) {
			p = queue.poll();
			tmp.add(p.val);
			currentLevel--;
			if (p.left != null) {
				queue.offer(p.left);
				nextLevel++;
			}
			if (p.right != null) {
				queue.offer(p.right);
				nextLevel++;
			}
			if (currentLevel == 0) {
				currentLevel = nextLevel;
				nextLevel = 0;
				if (res.size() % 2 == 1) Collections.reverse(tmp);
				res.add(tmp);
				tmp = new ArrayList<>();
				left2right = !left2right;
			}
		}
		return res;
	}

	/*更简洁版本*/
	public List<List<Integer>> levelOrder2(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		List<List<Integer>> res = new ArrayList<>();
		if(root != null) queue.add(root);
		while(!queue.isEmpty()) {
			List<Integer> tmp = new ArrayList<>();
			for(int i = queue.size(); i > 0; i--) {
				TreeNode node = queue.poll();
				tmp.add(node.val);
				if(node.left != null) queue.add(node.left);
				if(node.right != null) queue.add(node.right);
			}
			if(res.size() % 2 == 1) Collections.reverse(tmp);
			res.add(tmp);
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

	@Test
	public void test1() {

	}

}
