package javaoffer;

import org.junit.Test;

/**
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 限制：
 * 0 <= 节点个数 <= 1000
 * 注意：本题与主站 101 题相同：https://leetcode-cn.com/problems/symmetric-tree/
 *
 * 思路：
 */
public class Easy28 {

	public boolean isSymmetric(TreeNode root) {
		return isSame(root, root);//root照镜子
	}

	public boolean isSame(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) return true;//都为空指针则返回 true
		if (t1 == null || t2 == null) return false;//只有一个为空则返回 false
		return (t1.val == t2.val) &&//首先看值是否相等
				isSame(t1.right, t2.left) &&//递归判断t1左子树和t2右子树是否对称
				isSame(t1.left, t2.right);//同理
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
