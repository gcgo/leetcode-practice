package bytedance;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，返回它的 后序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 思路：后序就是用栈保存,顺序为：根入栈，循环：[出栈，左娃入栈，右娃入栈]；最后倒序输出
 */
public class Hard145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> output = new LinkedList<>();
        if (root == null) {
            return output;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pollLast();
            output.addFirst(node.val);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
        return output;
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
