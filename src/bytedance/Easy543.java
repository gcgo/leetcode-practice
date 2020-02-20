package bytedance;

import org.junit.Test;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 * <p>
 * 示例 :
 * 给定二叉树
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 * <p>
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 * <p>
 * 思路：最长直径肯定是左子树叶子节点到右子树叶子节点，取最大值
 * 实际就是求左右子树深度相加。
 */
public class Easy543 {
    int maxDiameter = 0;//全局变量

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        getDepth(root);
        return maxDiameter;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        maxDiameter = Math.max(maxDiameter, l + r);
        return Math.max(l, r) + 1;
    }

    @Test
    public void test1() {
        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(2);
        t2.right = new TreeNode(3);
        t2.left.right = new TreeNode(5);
        t2.left.left = new TreeNode(4);

        System.out.println(diameterOfBinaryTree(t2));

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
