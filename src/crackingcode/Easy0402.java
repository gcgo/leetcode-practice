package crackingcode;

import org.junit.Test;

/**
 * 给定一个有序整数数组，元素各不相同且按升序排列，编写一个算法，创建一棵高度最小的二叉搜索树。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *           0
 *          / \
 *        -3   9
 *        /   /
 *      -10  5
 *
 *思路：既然是排好序的，肯定选最中间为根节点最好，然后再从根节点左边里选出左孩子，右边里选出右孩子
 * 很明显选孩子过程又是一个递归过程
 *
 */
public class Easy0402 {

	public TreeNode sortedArrayToBST(int[] nums) {
		return sort(nums, 0, nums.length - 1);
	}

	private TreeNode sort(int[] nums, int start, int end) {
		if (start > end) return null;
		int mid = (start + end) >>> 1;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = sort(nums, start, mid - 1);
		root.right = sort(nums, mid + 1, end);
		return root;
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
		TreeNode t = sortedArrayToBST(tree);
		System.out.println(t);
	}

}

