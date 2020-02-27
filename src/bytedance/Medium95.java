package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 * 思路：dfs,和96题很像，分别以i为根，递归左右子树，递归终止条件是左边界>右边界
 */
public class Medium95 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<>();
        return getTrees(1, n);
    }

    private ArrayList<TreeNode> getTrees(int start, int end) {
        ArrayList<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
        }
        for (int i = start; i <= end; i++) {//分别以i为根
            ArrayList<TreeNode> left = getTrees(start, i - 1);//i左边返回左子树
            ArrayList<TreeNode> right = getTrees(i + 1, end);//i右边返回右子树
            for (TreeNode treeNode : left) {  //总树就是左右子树的笛卡尔积
                for (TreeNode node : right) {
                    TreeNode root = new TreeNode(i);
                    root.left = treeNode;
                    root.right = node;
                    res.add(root);
                }
            }

        }
        return res;
    }

    //**************************************************************************
    @Test
    public void test1() {

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
