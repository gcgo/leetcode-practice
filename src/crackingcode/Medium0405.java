package crackingcode;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 *思路：二分搜索树中序遍历是有序的，可以中序遍历然后查看。
 * 可以优化一下空间复杂度，不用把整个序列存进来，中序遍历后只需考虑前后元素大小即可，和考察局部树的错误想法是有区别的
 *
 */
public class Medium0405 {

	public boolean isValidBST(TreeNode root) {
		Deque<TreeNode> stack = new ArrayDeque<>();
		List<Integer> res = new ArrayList<>();
		TreeNode p = root;
		Integer pre = null;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				p = stack.pop();
				if (pre==null)pre=p.val;
				else {
					int cur=p.val;
					if (cur<=pre)return false;
					pre=cur;
				}
				p = p.right;
			}
		}
		return true;
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

