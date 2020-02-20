package bytedance;

import org.junit.Test;

/**
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 * 思路：先序遍历，最后节点都通过右孩子相连
 * 将左子树插入到右子树的地方
 * 将原来的右子树接到左子树的最右边节点
 * 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
 *
 * 例子中：
 * 1》从1开始找，左孩子里找最右边的
 * 1》找到4
 * 2》将5挂到4右边
 * 3》将2挂到1右边
 * 4》将1的左边置null
 * 5》从2开始新一轮
 */

public class Medium114 {
	public void flatten(TreeNode root) {
		while (root != null) {
			//左子树为 null，直接考虑下一个节点
			if (root.left == null) {
				root = root.right;
			} else {
				// 找左子树最右边的节点,当前的右子树要挂在这个节点上
				TreeNode pre = root.left;
				while (pre.right != null) {
					pre = pre.right;
				}
				//将原来的右子树接到左子树的最右边节点
				pre.right = root.right;
				// 将左子树插入到右子树的地方
				root.right = root.left;
				root.left = null;
				// 考虑下一个节点
				root = root.right;
			}
		}
	}


	@Test
	public void test1() {
		TreeNode t1 = new TreeNode(5);
		t1.left = new TreeNode(3);
		t1.right = new TreeNode(9);
		t1.left.right = new TreeNode(4);
		t1.right.left = new TreeNode(6);

		TreeNode t2 = new TreeNode(5);
		t2.left = new TreeNode(3);
		t2.right = new TreeNode(9);
		t2.left.right = new TreeNode(4);
		t2.right.left = new TreeNode(6);


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
