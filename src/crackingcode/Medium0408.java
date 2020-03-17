package crackingcode;

import org.junit.Test;

/**
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
 *
 * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
 *
 *     3
 *    / \
 *   5   1
 *  / \ / \
 * 6  2 0  8
 *   / \
 *  7   4
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输入: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 *
 *思路：
 * 1) 对于root节点: 如果root为空节点,返回null
 *   如果root与p或q相等,返回p或q。
 *
 *2) 如果没有在情况1返回,说明root不为空并且不与p,q相等
 *   那么,可能节点的分布存在以下情况:
 *
 *  2 一:节点一个分布在root的左子树一个分布在root的右子树
 *  2 二:节点都分布在root的左子树
 *  2 三:节点都分布在root的右子树
 *
 *  　我们对左右节点分别进行递归.左右节点分别成为新root节点.(记为新root节点)
 *
 *  3) 那么,左右两个搜索方法的返回值 searchLeft和searchRight 也根据搜索有了不同的情况
 *  　　一: searchLeft 和 searchRight 都不为空,对应着情况(2 一)
 *  　　二: searchLeft不为空,searchRight为空 , 对应着情况(2 二)
 *  　　三: searchRight不为空,searchLeft为空 , 对应着情况(2 三)
 *
 */
public class Medium0408 {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root.val == p.val || root.val == q.val) {
			return root;
		}
		//root不为空并且不与p,q相等,则递归左右子树
		TreeNode searchLeft = lowestCommonAncestor(root.left, p, q);
		TreeNode searchRight = lowestCommonAncestor(root.right, p, q);

		if (searchLeft != null && searchRight != null) { //左右各有一个节点
			return root;
		}
		if (searchLeft != null) { //节点都在左边
			return searchLeft;
		}
		return searchRight;//节点都在右边,或者为空（叶子节点）
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

