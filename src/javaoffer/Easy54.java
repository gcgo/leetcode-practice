package javaoffer;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 *思路：中序遍历即是升序,逆中序遍历是降序，right->root->left
 *
 */
public class Easy54 {
	/*逆中序遍历*/
	public int kthLargest(TreeNode root, int k) {
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode p = root;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				stack.push(p);
				p = p.right;//先右
			} else {
				p = stack.pop();
				if (--k == 0) return p.val;//再中
				p = p.left;//最后左
			}
		}
		return -1;
	}

	@Test
	public void test1() {

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
