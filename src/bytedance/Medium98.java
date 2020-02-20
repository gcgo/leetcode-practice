package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 *   2
 *  / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 * 思路：
 * 1若是二叉搜索树的话，中序遍历应该是升序，所以中序遍历出来，然后比较相邻元素大小
 * 2
 */
public class Medium98 {
    //******************************非递归*************************************************
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {//中序遍历，所以先来到最左边，中途的节点入栈
                stack.push(root);
                root = root.left;
            }//此时root应该是最左边的叶子了
            root = stack.pop();//开始出栈，
            if(pre != null && root.val <= pre.val) return false;//和栈中下一个元素比较，比他大证明中序遍历不是升序
            pre = root;//否则迭代当前节点
            root = root.right;//把右子节点准备入栈
        }
        return true;
    }
    //******************************递归*************************************************
    public boolean isValidBST2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder2(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) return false;
        }
        return true;
    }

    private void inorder2(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder2(root.left, list);
        list.add(root.val);
        inorder2(root.right, list);
    }

    //**************************************************************************
    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(5);
        t1.left = new TreeNode(1);
        t1.right = new TreeNode(4);
        t1.right.left = new TreeNode(3);
        t1.right.right = new TreeNode(6);

        System.out.println(isValidBST(t1));
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
