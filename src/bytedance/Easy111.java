package bytedance;

import org.junit.Test;

/**
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最小深度  2.
 *
 * 思路：
 *
 */
public class Easy111 {
	public int minDepth(TreeNode root) {
		if (root == null) return 0;//当前节点为空
		if (root.left == null && root.right == null) return 1;//左右娃都为空
		if (root.left == null) return minDepth(root.right) + 1;//左娃为空，看右娃
		if (root.right == null) return minDepth(root.left) + 1;//右娃为空，看左娃
		//左右娃都不为空
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		return 1 + Math.min(left, right);//看小的
	}

	@Test
	public void test1() {
		TreeNode t1 = new TreeNode(1);
		t1.left = new TreeNode(2);
		t1.right = new TreeNode(2);
		t1.left.left = new TreeNode(3);
		t1.left.left.left = new TreeNode(4);
		t1.right.right = new TreeNode(3);
//		t1.right.right.right = new TreeNode(4);

		TreeNode t2 = new TreeNode(5);
		t2.left = new TreeNode(3);
//		t2.right = new TreeNode(9);
		t2.left.right = new TreeNode(4);
//		t2.right.left = new TreeNode(6);

		System.out.println(minDepth(t1));
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
