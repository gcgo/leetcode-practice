package javaoffer;

import org.junit.Test;

/**
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * <p>
 * 思路：定义函数返回树的深度，比较左右子树深度
 */
public class Easy55II {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(depth(root.left) - depth(root.right)) < 2 &&
                isBalanced(root.left) &&
                isBalanced(root.right);
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    @Test
    public void test1() {
        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(3);
        t2.right = new TreeNode(9);
        t2.left.right = new TreeNode(4);
        t2.right.left = new TreeNode(6);

        System.out.println(isBalanced(t2));

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
