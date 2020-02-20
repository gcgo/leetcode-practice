package bytedance;

import org.junit.Test;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 思路：前序第一个数肯定是根节点；
 * 再在中序里找根节点，可以分出左右子树；
 * 在左右子树中再根据前序找根节点（递归了）
 */
public class Medium105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);//先序第一个元素，即根节点
        int inIndex = 0; // 根在中序数组中的位置
        for (; inIndex <= inEnd; inIndex++) {
            if (inorder[inIndex] == root.val) {
                break;
            }
        }
        //中序数组中根左边的元素就是左子树，右边的就是右子树
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + (inIndex - inStart + 1), inIndex + 1, inEnd, preorder, inorder);
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
    public void test1() {
        TreeNode res = buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7});
    }
}
