package bytedance;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 示例: 
 * <p>
 * 你可以将以下二叉树：
 * ---------1
 * --------/ \
 * -------2   3
 * ----------/ \
 * ---------4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 * <p>
 * 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
 * <p>
 * 思路：leetcode格式是层序遍历，我们就按照leetcode格式写。
 */
public class Hard297 {
    public class Codec {

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            TreeNode t;
            queue.add(root);
            while (!queue.isEmpty()) {
                t = queue.poll();
                if (t != null) {
                    sb.append(t.val).append(",");
                    queue.add(t.left);//无论空不空都往里存，取的时候再判断
                    queue.add(t.right);
                } else {
                    sb.append("null").append(",");
                }
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {//怎么序列化，就怎么反序列化，同样用一个队列
            String[] roots = data.split(",");
			if (roots[0].equals("null")) return null;
			Queue<TreeNode> queue = new LinkedList<>();
            int p = 0;
            TreeNode root = getTreeNode(roots, p++);
            queue.add(root);//头先入队
            while (!queue.isEmpty()) {
                TreeNode t = queue.poll();//弹出来给他的左右孩子赋值，值就在String数组里，层序排列
                TreeNode l = getTreeNode(roots, p++);//先给左孩子赋值
                TreeNode r = getTreeNode(roots, p++);//再给右孩子赋值
                if (l != null) queue.add(l);//左孩子入队
                if (r != null) queue.add(r);//右孩子入队
                t.left = l;//挂父亲
                t.right = r;
            }
            return root;
        }

        private TreeNode getTreeNode(String[] roots, int p) {
            if (roots[p].equals("null")) return null;
            else return new TreeNode(Integer.parseInt(roots[p]));
        }
    }

    @Test
    public void test1() {
        TreeNode t2 = new TreeNode(1);
        t2.left = new TreeNode(2);
        t2.right = new TreeNode(3);
        t2.right.left = new TreeNode(4);
        t2.right.right = new TreeNode(5);

		TreeNode t1=null;

        Codec codec = new Codec();
        String serialize = codec.serialize(t2);
        System.out.println(serialize);

        TreeNode deserialize = codec.deserialize(serialize);
        System.out.println(deserialize);

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
