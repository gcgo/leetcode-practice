package bytedance;

import org.junit.Test;

/**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大
 * （一个节点也可以是它自己的祖先）。”
 * <p>
 * 思路：对于二分搜索树，最近公共祖先肯定使得这两个节点在它左右，即A < 祖先 < B。
 */
public class Easy235 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null && p == null && q == null) {
            return null;
        }
        //他俩都<根，那就去左边找
        if (Math.max(p.val, q.val) < root.val) return lowestCommonAncestor(root.left, p, q);
        //他俩都>根，那就去右边找
        else if (Math.min(p.val, q.val) > root.val) return lowestCommonAncestor(root.right, p, q);
        //分散两边，即找到了
        else return root;
    }

    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(3);
        t1.left.left.left = new TreeNode(4);
        t1.right.right = new TreeNode(3);
//		t1.right.right.right = new TreeNode(4);

        TreeNode t2 = new TreeNode(5);
        t2.left = new TreeNode(3);
//		t2.right = new TreeNode(9);
        t2.left.right = new TreeNode(4);
//		t2.right.left = new TreeNode(6);

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
