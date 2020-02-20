package bytedance;

import org.junit.Test;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，
 * 最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 思路：
 * 1) 对于root节点: 如果root为空节点,返回null
 * 　　　　   如果root与p或q相等,返回p或q.
 * <p>
 * 　　　　　　2) 如果没有在情况1返回,说明root不为空并且不与p,q相等
 * 　　　　　　　　 那么,可能节点的分布存在以下情况:
 * <p>
 * 　　　　　　　　2 一:节点一个分布在root的左子树一个分布在root的右子树
 * 　　　　　　　　2 二:节点都分布在root的左子树
 * 　　　　　　　　2 三:节点都分布在root的右子树
 * <p>
 * 　　　　　　　　 我们对左右节点分别进行递归.左右节点分别成为新root节点.(记为新root节点)
 * <p>
 * 　　　　      3) 那么,左右两个搜索方法的返回值 searchLeft和searchRight 也根据搜索有了不同的情况
 * 　　　　　　　　 一: searchLeft 和 searchRight 都不为空,对应着情况(2 一)
 * 　　　　　　　　 二: searchLeft不为空,searchRight为空 , 对应着情况(2 二)
 * 　　　　　　　　 三: searchRight不为空,searchLeft为空 , 对应着情况(2 三)
 */
public class Medium236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        //root不为空并且不与p,q相等,则递归左右子树
        TreeNode searchLeft = lowestCommonAncestor(root.left, p, q);
        TreeNode searchRight = lowestCommonAncestor(root.right, p, q);

        if (searchLeft != null && searchRight != null) { //左右各有一个节点
            return root;
        }
        if (searchLeft != null) { //节点都在左边
            return searchLeft;
        }
        return searchRight;//节点都在右边,或者为空（叶子节点）
    }

    //**************************************************************************
    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(3);
        t1.left = new TreeNode(5);
        t1.right = new TreeNode(1);
        t1.right.right = new TreeNode(8);
        t1.right.left = new TreeNode(0);
        t1.left.left = new TreeNode(6);
        t1.left.right = new TreeNode(2);
        t1.left.right.right = new TreeNode(4);
        t1.left.right.left = new TreeNode(7);

        System.out.println(lowestCommonAncestor(t1, new TreeNode(7), new TreeNode(1)).val);
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
