package bytedance;

import org.junit.Test;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例: 
 * 给定如下二叉树，以及目标和 sum = 22，
 * <p>
 * 5
 * / \
 * 4   8
 * /   / \
 * 11  13  4
 * /  \      \
 * 7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * <p>
 * 思路：dfs
 */
public class Easy112 {
    public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;

		sum -= root.val;
		if ((root.left == null) && (root.right == null))
			return (sum == 0);
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}


    @Test
    public void test1() {
        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(9);
        t2.left.right = new TreeNode(4);
        t2.right.left = new TreeNode(6);

        System.out.println(hasPathSum(t2, 12));

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
