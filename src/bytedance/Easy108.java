package bytedance;

import org.junit.Test;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 思路：平衡二叉树的话，就需要从中间取元素作为根，每一次都是这样取值，递归
 */
public class Easy108 {
	 public TreeNode sortedArrayToBST(int[] nums) {
		 if (nums==null) {
			return null;
		}
		return convertTree(nums,0,nums.length-1);
	    }


	private TreeNode convertTree(int[] nums, int l, int r) {
		if (l<=r) {
			int mid =(l+r)>>>1;
			TreeNode root=new TreeNode(nums[mid]);
			root.left=convertTree(nums, l, mid-1);
			root.right=convertTree(nums, mid+1, r);
			return root;
		}else {
			return null;
		}
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
