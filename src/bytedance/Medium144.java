package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个二叉树，返回它的 前序 遍历。
 * 1颜色法：
 * 2常规非递归：栈,先访问，在入栈，在判断左儿子
 */
public class Medium144 {
    public List<Integer> preorderTraversal(TreeNode root) {
        TreeNode p = root;//p为当前节点
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        //栈不为空时，或者p不为空时循环
        while (p != null || !stack.isEmpty()) {
            //当前节点不为空。访问并压入栈中。并将当前节点赋值为左儿子
            if (p != null) {
                stack.push(p);//入栈
                res.add(p.val);//访问
                p = p.left;
            }
            //当前节点为空：
            //  1、当p指向的左儿子时，此时栈顶元素必然是它的父节点
            //  2、当p指向的右儿子时，此时栈顶元素必然是它的爷爷节点
            //取出栈顶元素，赋值为right
            else {
                p = stack.pop();
                p = p.right;
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal(TreeNode root) {//中序遍历
        TreeNode p = root;//p为当前节点
        Deque<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        //栈不为空时，或者p不为空时循环
        while (p != null || !stack.isEmpty()) {
            //当前节点不为空。压入栈中。并将当前节点赋值为左儿子
            if (p != null) {
                stack.push(p);//入栈，不访问
                p = p.left;
            }
            //当前节点为空：
            //  1、当p指向的左儿子时，此时栈顶元素必然是它的父节点
            //  2、当p指向的右儿子时，此时栈顶元素必然是它的爷爷节点
            //取出并访问栈顶元素，赋值为right
            else {
                p = stack.pop();
                res.add(p.val);//访问
                p = p.right;
            }
        }
        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {//后序遍历
//        List<Integer> res = new LinkedList<>();//注掉的部分都是链表。
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);//实际是按照先序遍历的方法，只不过顺序是“根右左”，最后倒序输出就是后序遍历了！
//                res.addFirst(p.val);  // 头插，输出时就是倒序了
                res.add(p.val);  //
                p = p.right;             // 先往右孩子找
            } else {
                p = stack.pop();
                p = p.left;           // 再找左孩子
            }
        }
//        return res;
        Collections.reverse(res);//反转输出
        return res;
    }

    public List<Integer> levelorderTraversal(TreeNode root) {//层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        TreeNode p;
        queue.add(root);//根节点先入队
        while (!queue.isEmpty()) {//每次出队一个节点，就把他的左右孩子都入队
            p = queue.poll();//出队
            res.add(p.val);//访问
            if (p.left != null)
                queue.add(p.left);//左娃入队
            if (p.right != null)
                queue.add(p.right);//右娃入队
        }
        return res;
    }

    @Test
    public void test1() {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(4);
        t1.left.right = new TreeNode(5);
        t1.right.left = new TreeNode(6);
        t1.right.right = new TreeNode(7);

        System.out.println(preorderTraversal(t1));
        System.out.println(inorderTraversal(t1));
        System.out.println(postorderTraversal(t1));
        System.out.println(levelorderTraversal(t1));
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
