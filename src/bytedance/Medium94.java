package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回它的中序 遍历。
 * 思路：左子树 ---> 根结点 ---> 右子树
 * 1递归，2栈
 * <p>
 * 1递归：没啥可说的，最简单明了。但是递归效率低。
 * 2栈：不好理解
 * 3颜色标记法：
 * 其核心思想如下：
 * <p>
 * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
 * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
 * 如果遇到的节点为灰色，则将节点的值输出。
 */
public class Medium94 {
    public List<Integer> inorderTraversal(TreeNode root) {//递归
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);//左
        list.add(root.val);//中
        inorder(root.right, list);//右
    }

    //***********************************************************************
    public List<Integer> inorderTraversal2(TreeNode root) {//非递归
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);//先自己入栈，再左孩子入栈，直到左孩子为空
                curr = curr.left;
            }
            curr = stack.pop();//出栈，打印，
            res.add(curr.val);
            curr = curr.right;
        }
        return res;

    }

    //**********************************************************************
    public List<Integer> inorderTravers3(TreeNode root) {//颜色标记
        if (root == null) return new ArrayList<Integer>();

        List<Integer> res = new ArrayList<>();
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root, "white"));

        while (!stack.empty()) {
            ColorNode colorNode = stack.pop();//先弹出一个

            if (colorNode.color.equals("white")) {//如果颜色为白色，即未访问过
                if (colorNode.node.right != null) //右孩子先入栈
                    stack.push(new ColorNode(colorNode.node.right, "white"));

                stack.push(new ColorNode(colorNode.node, "gray"));//自己入栈，标记为灰色

                if (colorNode.node.left != null) //左孩子最后入栈，这样出栈时候，左中右
                    stack.push(new ColorNode(colorNode.node.left, "white"));
            } else {//如果是灰色，就输出
                res.add(colorNode.node.val);
            }
        }

        return res;
    }


    class ColorNode {//颜色标记法内部类
        TreeNode node;
        String color;

        public ColorNode(TreeNode node, String color) {
            this.node = node;
            this.color = color;
        }
    }

    //**************************************************************************
    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.right.right = new TreeNode(6);
        t1.left.left = new TreeNode(4);
        t1.left.right = new TreeNode(5);
//		t1.left.right = new TreeNode(5);
//		t1.left.right.right = new TreeNode(5);

        TreeNode t2 = new TreeNode(71);
        t2.left = new TreeNode(62);
        t2.right = new TreeNode(84);
        t2.left.left = new TreeNode(14);
        t2.left.right = new TreeNode(80);
        t2.right.right = new TreeNode(81);
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
