package bytedance;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *1递归：root与自己照镜子
 * 递归结束条件：
 *
 * 都为空指针则返回 true
 * 只有一个为空则返回 false
 * 递归过程：
 *
 * 判断两个指针当前节点值是否相等
 * 判断 A 的右子树与 B 的左子树是否对称
 * 判断 A 的左子树与 B 的右子树是否对称
 * 短路：
 *
 * 在递归判断过程中存在短路现象，也就是做 与 操作时，如果前面的值返回 false 则后面的不再进行计算
 *2非递归：
 * 构造队列，队列中连续两个数应该是相等的。
 */
public class Easy101 {
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

	//****************************非递归↓↓↓↓↓↓↓↓↓↓↓↓↓↓*****************************************
	public boolean isSymmetric2(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode t1 = q.poll();
			TreeNode t2 = q.poll();
			if (t1 == null && t2 == null) continue;
			if (t1 == null || t2 == null) return false;
			if (t1.val != t2.val) return false;
			//下面俩应该相等
			q.add(t1.left);
			q.add(t2.right);
			//下面俩应该相等
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
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
