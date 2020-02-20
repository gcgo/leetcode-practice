package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 思路：
 * 1dfs：最快！！
 * <p>
 * <p>
 * 2非递归
 * <p>
 * <p>
 * 3颜色标记法：
 * 其核心思想如下：
 * <p>
 * 使用颜色标记节点的状态，新节点为白色，已访问的节点为灰色。
 * 如果遇到的节点为白色，则将其标记为灰色，然后将其右子节点、自身、左子节点依次入栈。
 * 如果遇到的节点为灰色，则将节点的值输出。
 */
public class Medium102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        levelHelper(res, root, 0);
        return res;
    }

    public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
        if (root == null) return;
        if (height == res.size()) {//开启新一层
            res.add(new LinkedList<Integer>());
        }
        res.get(height).add(root.val);
        levelHelper(res, root.left, height + 1);
        levelHelper(res, root.right, height + 1);
    }

    //***********************************************************************
    public List<List<Integer>> levelOrder2(TreeNode root) {//非递归
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // start the current level
            levels.add(new ArrayList<>());

            // number of elements in the current level
            int level_length = queue.size();
            for (int i = 0; i < level_length; ++i) {
                TreeNode node = queue.remove();

                // fulfill the current level
                levels.get(level).add(node.val);

                // add child nodes of the current level
                // in the queue for the next level
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            // go to next level
            level++;
        }
        return levels;
    }

    //**********************************************************************
    public List<List<Integer>> levelOrder3(TreeNode root) {//颜色标记
        if (root==null)return new ArrayList<>();

        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        Stack<ColorNode> stack = new Stack<>();
        stack.push(new ColorNode(root, "white", 0));//root是第0层,level就是结果集中的index

        while (!stack.empty()) {
            ColorNode colorNode = stack.pop();//先弹出一个

            if (colorNode.color.equals("white")) {//如果颜色为白色，即未访问过
                if (colorNode.node.right != null) //右孩子先入栈
                    stack.push(new ColorNode(colorNode.node.right, "white", colorNode.level + 1));

                if (colorNode.node.left != null) //左孩子最后入栈，这样出栈时候，左中右
                    stack.push(new ColorNode(colorNode.node.left, "white", colorNode.level + 1));

                stack.push(new ColorNode(colorNode.node, "gray", colorNode.level));//自己入栈，标记为灰色
            } else {//如果是灰色，就输出
                if (levels.size() == colorNode.level)//如果结果集中结果个数应该大于当前节点level值，因为level是下标。
                    levels.add(new ArrayList<>());//若不大于，则新建一层结果
                levels.get(colorNode.level).add(colorNode.node.val);
            }
        }

        return levels;
    }

    class ColorNode {//颜色标记法内部类
        TreeNode node;
        String color;
        int level;

        public ColorNode(TreeNode node, String color, int level) {
            this.node = node;
            this.color = color;
            this.level = level;
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
