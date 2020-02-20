package bytedance;

import org.junit.Test;

/**
 * 翻转一棵二叉树。
 *
 * 示例：
 *
 * 输入：
 *
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 输出：
 *
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 * 思路：递归
 */
public class Easy226 {
	 public TreeNode invertTree(TreeNode root) {
		 if (root==null) {
			return null;
		}
	        TreeNode tempNode = root.left;
	        root.left = root.right;
	        root.right = tempNode;

	        invertTree(root.left);
	        invertTree(root.right);

	        return root;
	    }

	@Test
	public void test1() {
		TreeNode t1 = new TreeNode(1);
		t1.left = new TreeNode(2);
		t1.right = new TreeNode(2);
		t1.left.left = new TreeNode(3);
		t1.left.left.left = new TreeNode(4);
		t1.right.right = new TreeNode(3);

		TreeNode t2 = new TreeNode(5);
		t2.left = new TreeNode(3);
		t2.left.right = new TreeNode(4);

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
