package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * <p>
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * <p>
 * 思路：双栈交替遍历
 * 把所有层分为奇偶层，将根节点看作第0层(偶数层)，并入栈evenLevel
 * 遍历偶数层的节点时，将偶数层的节点栈evenLevel依次出栈，同时把出栈节点的子树按left、right分别入奇数层节点栈oddLevel，直到偶数层的栈为空
 * 遍历奇数层的节点时，将奇数层的节点栈oddLevel依次出栈，同时把出栈节点的子树按right、left分别入偶数层节点栈evenLevel，直到奇数层的栈为空
 * 第2、3步骤交替进行，当两个栈都为空时结束锯齿遍历
 */
public class Medium103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Stack<TreeNode> evenLevel = new Stack<>();//记录偶数层的节点栈
        Stack<TreeNode> oddLevel = new Stack<>();//记录奇数层的节点栈
        evenLevel.push(root);//根节点入偶栈
        for (int i = 0; !evenLevel.isEmpty() || !oddLevel.isEmpty(); i++) {
            List<Integer> cur = new ArrayList<Integer>();//新建一层
            if ((i & 1) == 0) {
                // 偶数层,从0开始
                while (!evenLevel.isEmpty()) {
                    TreeNode pop = evenLevel.pop();
                    if (pop != null) {
                        cur.add(pop.val);
                        // 左子树先入栈，因为下一层（奇数层）访问顺序是:右节点->左节点
                        // 根据栈的FILO,左节点先入栈
                        oddLevel.push(pop.left);
                        oddLevel.push(pop.right);
                    }
                }
            } else {
                // 奇数层
                while (!oddLevel.isEmpty()) {
                    TreeNode pop = oddLevel.pop();
                    if (pop != null) {
                        cur.add(pop.val);
                        // 右子树先入栈，因为下一层（偶数层）访问顺序是:左节点->右节点
                        evenLevel.push(pop.right);
                        evenLevel.push(pop.left);
                    }
                }
            }
            if (!cur.isEmpty()) {
                res.add(cur);
            }
        }
        return res;
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
