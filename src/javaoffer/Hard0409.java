package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。
 * 给定一个由不同节点组成的二叉树，输出所有可能生成此树的数组。
 *
 * 示例:
 * 给定如下二叉树
 *         2
 *        / \
 *       1   3
 * 返回:
 * [
 *    [2,1,3],
 *    [2,3,1]
 * ]
 *思路：题目描述我服了。。。。。意思是从左到右遍历数组，可以在任意合法的地方插入元素，不是层次优先的！！！
 *
 *网友思路：
 * 数组开头肯定是root.val，后面排列其实就是左右子树的排列再组合到一起，求解左右子树的排列显然和求解root是一样的，所以是递归。
 *然后回溯排列组合左右两边的子集
 *
 */
public class Hard0409 {
	public List<List<Integer>> BSTSequences(TreeNode root) {
		if (null == root) {
			List<List<Integer>> r = new ArrayList<>();
			r.add(new ArrayList<>());
			return r;
		}
		List<List<Integer>> lefts = BSTSequences(root.left);
		List<List<Integer>> rights = BSTSequences(root.right);
		List<List<Integer>> res = new ArrayList<>();
		LinkedList<Integer> collector = new LinkedList<>();
		collector.add(root.val);
		for (List<Integer> left : lefts) {
			for (List<Integer> right : rights) {
				backtrack(left, 0, right, 0, collector, res);
			}
		}
		return res;
	}

	private void backtrack(List<Integer> left, int l, List<Integer> right, int r,
						   LinkedList<Integer> collector, List<List<Integer>> result) {
		if (l >= left.size() && r >= right.size()) {
			result.add(new ArrayList<>(collector));
			return;
		}
		if (l < left.size()) {
			collector.addLast(left.get(l));
			backtrack(left, l + 1, right, r, collector, result);
			collector.removeLast();
		}
		if (r < right.size()) {
			collector.addLast(right.get(r));
			backtrack(left, l, right, r + 1, collector, result);
			collector.removeLast();
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

	@Test
	public void test() {

	}

}

