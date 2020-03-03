package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 *
 * 示例 1:
 *
 * 输入:
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: [3, 14.5, 11]
 * 解释:
 * 第0层的平均值是 3,  第1层是 14.5, 第2层是 11. 因此返回 [3, 14.5, 11].
 * 注意：
 *
 * 节点值的范围在32位有符号整数范围内。
 *
 * 思路：层序遍历，通过变量来记录当前层有几个节点，访问过了几个节点，当前层的总和
 */
public class Easy637 {
	public List<Double> averageOfLevels(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		int curLevelNum = 1;
		int fenmu = 1;
		int nextLevelNum = 0;
		double sum = 0;
		q.offer(root);
		TreeNode tmp;
		List<Double> res = new ArrayList<>();
		while (!q.isEmpty()) {
			tmp = q.poll();
			sum += tmp.val;
			curLevelNum--;
			if (tmp.right != null) {
				nextLevelNum++;
				q.offer(tmp.right);
			}
			if (tmp.left != null) {
				nextLevelNum++;
				q.offer(tmp.left);
			}
			if (curLevelNum == 0) {
				curLevelNum = nextLevelNum;
				res.add(sum / fenmu);
				fenmu = nextLevelNum;
				nextLevelNum = 0;
				sum = 0;
			}
		}
		return res;
	}

	@Test
	public void test1() {
		TreeNode t1 = new TreeNode(3);
		t1.left = new TreeNode(5);
		t1.right = new TreeNode(7);
		t1.left.left = new TreeNode(3);
		//		t1.left.left.left = new TreeNode(4);
		t1.right.left = new TreeNode(9);
		t1.right.right = new TreeNode(11);
		t1.right.right.left = new TreeNode(44);

		List<Double> averageOfLevels = averageOfLevels(t1);
		for (Double res : averageOfLevels) {
			System.out.println(res);
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
