package javaoffer;

import org.junit.Test;

import java.util.*;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 * 提示：
 * 数组长度 <= 1000
 *
 * 思路：单调栈,倒序遍历这个后序序列，应该是root->右->左
 * 所以我们从root开始一直压入栈，root完了是右孩子，大于root，而以右孩子为根的子树，下一个节点仍然是大于它的。
 * 即栈中元素一直是单调递增的。而当突然遍历到一个元素小于栈顶元素，说明现在来到左子树了，因为左子树是小于根的，更是小于右子树的。
 * 所以就弹出栈元素，直到栈顶元素不再大于当前左子树元素，此时更新当前的根为最后一次弹出的栈顶。
 * 接下来遍历的就都是对于当前根节点的左孩子了，所以都应该小于根节点，若大于则不是后序遍历。
 * 然后重复刚才所有判断及操作即可。
 *
 * 最开始假设根为无穷大。
 *
 */
public class Medium33 {

	public boolean verifyPostorder(int[] postorder) {
		// 单调栈使用，单调递增的单调栈
		Deque<Integer> stack = new LinkedList<>();
		// 表示上一个根节点的元素，这里可以把postorder的最后一个元素root看成无穷大节点的左孩子
		int pervElem = Integer.MAX_VALUE;
		// 逆向遍历，就是翻转的先序遍历
		for (int i = postorder.length - 1; i >= 0; i--) {
			// 左子树元素必须要小于递增栈被peek访问的元素，否则就不是二叉搜索树
			if (postorder[i] > pervElem) {
				return false;
			}
			while (!stack.isEmpty() && postorder[i] < stack.peek()) {
				// 数组元素小于单调栈的元素了，表示往左子树走了，记录下上个根节点
				// 找到这个左子树对应的根节点，之前右子树全部弹出，不再记录，因为不可能在往根节点的右子树走了
				pervElem = stack.pop();
			}
			// 这个新元素入栈
			stack.push(postorder[i]);
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
	public void test1() {
		System.out.println(verifyPostorder(new int[]{1, 3, 2, 6, 5}));
	}

}
