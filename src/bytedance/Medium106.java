package bytedance;

import org.junit.Test;

import java.util.HashMap;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 思路：后序最后一个肯定是根节点，在中序中找根节点，两边就是左右子树。
 * 中序中，根节点左边挨着肯定是左子树的最右孩子，右边挨着肯定是右子树最左孩子
 * 后续中，上一步找到的最左孩子左边所有就是根节点左子树的部分。
 * 这样左右子树在中后数组中的范围也都确定了，然后递归求左右子树。
 */
public class Medium106 {

    HashMap<Integer, Integer> memo = new HashMap<>();//记录中序遍历中节点和索引的位置
    int[] post;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) memo.put(inorder[i], i);
        post = postorder;
        TreeNode root = buildTree(0, inorder.length - 1, 0, post.length - 1);
        return root;
    }

    /**
     * 中序遍历数组的两个位置标记 [is, ie],is是起始位置，ie是结束位置
     * 后序遍历数组的两个位置标记 [ps, pe] ps是起始位置，pe是结束位置
     */
    public TreeNode buildTree(int is, int ie, int ps, int pe) {
        if (ie < is || pe < ps) return null;
        int root = post[pe];//后序最后一个数为root
        int ri = memo.get(root);//找到root在中序中的位置

        TreeNode node = new TreeNode(root);
        /*那么左娃：中序是根节点左边，后序是：看看中序里root左边有多少个，后序范围就是前多少个*/
        node.left = buildTree(is, ri - 1, ps, ps + ri - is - 1);
        /*那么右娃：中序是根节点右边，后序是：从刚才左娃后序结束位置，一直到倒数第二个*/
        node.right = buildTree(ri + 1, ie, ps + ri - is, pe - 1);
        return node;
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
        TreeNode res = buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3});
    }
}
