package crackingcode;

import org.junit.Test;

/**
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 *
 * 示例 1:
 *
 * 输入: root = [2,1,3], p = 1
 *
 *   2
 *  / \
 * 1   3
 *
 * 输出: 2
 * 示例 2:
 *
 * 输入: root = [5,3,6,2,4,null,null,1], p = 6
 *
 *       5
 *      / \
 *     3   6
 *    / \
 *   2   4
 *  /
 * 1
 *
 * 输出: null
 *
 *思路：中序遍历的下一个节点，可以利用二分搜索树性质，二分查找，
 * 对于中序遍历:
 * 1.p存在右子树，那么p的后继就是p.right子树的最左节点
 * 2.p不存在右子树，那么p的后继就是p所在子树的根作为左孩子所在的树的父节点
 *
 */
public class Medium0406 {

	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		/*p存在右子树，那么p的后继就是p.right子树的最左节点*/
		if (p.right != null) {
			p = p.right;
			while (p.left != null) p = p.left;
			return p;
		}
		/*p不存在右子树，那么p的后继就是p所在子树的根作为左孩子所在的树的父节点*/
		TreeNode res = null;
		TreeNode t = root;
		while (p != t) {
			if (p.val < t.val) {
				res = t;
				t = t.left;
			} else {
				t = t.right;
			}
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
	public void test() {

	}

}

