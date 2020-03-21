package javaoffer;

import org.junit.Test;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 * 思路：和105题一样
 * 前序第一个数是根节点，在中序里去找，中序中，根节点左边是左子树最右节点，右边是右子树最左节点
 * 在前序中，从根往右数“中序中根左边元素数目”就是左子树在先序中的区间，后面的就是右子树在先序的区间。
 * 至此左右子树在前中序中的区间位置都已确定，递归求解左右子树即可。
 */
public class Medium07 {

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(0, 0, inorder.length - 1, preorder, inorder);
	}

	/**
	 *
	 * @param preStart 树在先序中的起点
	 * @param inStart 树在中序中的起点
	 * @param inEnd 树在中序中的终点
	 * @param preorder 先序数组
	 * @param inorder 中序数组
	 * @return 根节点
	 */
	public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[preStart]);//先序第一个元素，即根节点
		int inIndex = 0; // 根在中序数组中的位置
		for (; inIndex <= inEnd; inIndex++) {
			if (inorder[inIndex] == root.val) {
				break;
			}
		}
		//中序数组中根左边的元素就是左子树，右边的就是右子树
		root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
		root.right = helper(preStart + (inIndex - inStart + 1), inIndex + 1, inEnd, preorder, inorder);
		return root;
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
