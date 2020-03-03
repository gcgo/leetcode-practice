package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层次遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 思路：层序遍历，然后倒序输出
 *
 */
public class Easy107 {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		Deque<List<Integer>> stack = new ArrayDeque<>();//存每一层的节点
		List<Integer> listLevel = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		if (root == null) return result;
		int curLevelNum = 1;//根节点1个
		int nextLevelNum = 0;//下一层有几个还不知道
		TreeNode p;
		q.offer(root);
		while (q.size() > 0) {
			p = q.poll();
			listLevel.add(p.val);
			curLevelNum--;//弹出一个，当前层节点数减1
			if (p.left != null) {
				q.offer(p.left);
				nextLevelNum++;//入队一个，当前层节点数加1
			}
			if (p.right != null) {
				q.offer(p.right);
				nextLevelNum++;//入队一个，当前层节点数加1
			}
			if (curLevelNum == 0) {
				curLevelNum = nextLevelNum;
				nextLevelNum = 0;
				stack.push(listLevel);
				listLevel = new ArrayList<>();
			}

		}
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
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

		List<List<Integer>> list = levelOrderBottom(t1);
		for (List<Integer> list2 : list) {
			Iterator<Integer> iterator = list2.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}
		}

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
