package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例:
 *
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 *
 * 思路：bfs
 *
 */
public class Medium199 {
	public List<Integer> rightSideView(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		List<Integer> res = new ArrayList<>();
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if (i == size - 1) {//最右边的元素
					res.add(node.val);
				}
				if (node.left != null) {
					queue.add(node.left);
				}
				if (node.right != null) {
					queue.add(node.right);
				}
			}
		}
		return res;

	}

	//*********************************************************************
	@Test
	public void test1() {
		TreeNode t1 = new TreeNode(5);
		t1.left = new TreeNode(3);
		t1.right = new TreeNode(9);
//		t1.left.right = new TreeNode(4);
//		t1.right.left = new TreeNode(6);

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
