package crackingcode;

import org.junit.Test;

/**
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1。
 *
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *       1
 *      / \
 *     2   2
 *    / \
 *   3   3
 *  / \
 * 4   4
 * 返回 false 。
 *
 * 思路：注意定义，是任意一颗子树高度差不超过1，所以不仅要判断root，还得判断以左右儿子为根的子树
 * 显然判断root和判断儿子是一样的，以root为例，我需要计算左边的高度和右边的高度，让他俩相减，超过1就不行。
 * 所以问题变为如何计算高度。
 * 那就需要dfs了，我一路向下，哪边能往下走就走哪边，每走一层高度就加1。
 *
 *
 */
public class Easy0404 {

	public boolean isBalanced(TreeNode root) {
		if (root == null) return true;
		return Math.abs(height(root.left) - height(root.right)) < 2 &&
				isBalanced(root.left) &&
				isBalanced(root.right);
	}

	private int height(TreeNode root) {
		if (root == null) return 0;
		return 1 + Math.max(height(root.left), height(root.right));
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
	public void test() {
		int[] tree = new int[]{-10, -3, 0, 5, 9};
	}

}

