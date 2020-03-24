package javaoffer;

import org.junit.Test;

/**
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 */
public class Easy55I {
	public int maxDepth(TreeNode root) {
		int depth = 0;//depth要么是0要么是1
		if (root == null) {
			return depth;
		}
		depth = 1;
		return depth + Math.max(maxDepth(root.left), maxDepth(root.right));
	}

	@Test
	public void test1() {
		TreeNode t2 = new TreeNode(5);
		t2.left = new TreeNode(3);
		t2.right = new TreeNode(9);
		t2.left.right = new TreeNode(4);
		t2.right.left = new TreeNode(6);

		System.out.println(maxDepth(t2));

	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

}
