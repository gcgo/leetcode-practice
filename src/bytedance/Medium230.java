package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 1
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * 思路：二分搜索树中序遍历是升序的,所以中序遍历前k个数就行
 *
 */
public class Medium230 {
	public int kthSmallest(TreeNode root, int k) {
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode p = root;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else if (p == null) {
				p=stack.pop();
				k--;
				if (k==0)return p.val;//相当于访问
				p=p.right;
			}
		}
		return -1;
	}

	@Test
	public void test1() {
		TreeNode t1 = new TreeNode(3);
		t1.left = new TreeNode(5);
		t1.right = new TreeNode(7);
		t1.left.left = new TreeNode(3);
		//		t1.left.left.left = new TreeNode(4);
		t1.right.left = new TreeNode(9);
		t1.right.right = new TreeNode(111);
		t1.right.right.left = new TreeNode(44);

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
