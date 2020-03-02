package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 思路：就是遍历所有路径，记录和为sum的路径
 *
 */
public class Medium113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), root, sum);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> tmp, TreeNode root, int sum) {
        if (root==null) return;
        sum -= root.val;
        tmp.add(root.val);
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        if (root.left != null) {
            dfs(res, tmp, root.left, sum);
            tmp.remove(tmp.size() - 1);
        }
        if (root.right != null) {
            dfs(res, tmp, root.right, sum);
            tmp.remove(tmp.size() - 1);
        }
    }

    @Test
    public void test1() {
        TreeNode t = new TreeNode(5);

        t.left=new TreeNode(4);
        t.left.left=new TreeNode(11);
        t.left.left.left=new TreeNode(7);
        t.left.left.right=new TreeNode(2);

        t.right=new TreeNode(8);
        t.right.left=new TreeNode(13);
        t.right.right=new TreeNode(4);
        t.right.right.left=new TreeNode(5);
        t.right.right.right=new TreeNode(1);

        System.out.println(pathSum(t,22));
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
