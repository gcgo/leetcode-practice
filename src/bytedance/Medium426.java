package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 将一个二叉搜索树就地转化为一个已排序的双向循环链表。可以将左右孩子指针作为双向循环链表的前驱和后继指针。
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * <p>
 * 思路：中序遍历有序
 */
public class Medium426 {
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Deque<Node> stack = new ArrayDeque<>();
        Node p = root;
        Node dummy = new Node(-1);
        Node cur = dummy;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);//中序遍历，不访问
                p = p.left;
            } else {
                p = stack.pop();
                cur.right = p;//挂节点
                p.left = cur;
                cur = p;//指针后移
                p = p.right;
            }
        }
        cur.right = dummy.right;//最后cur指向最后一个节点，将他和头连起来
        dummy.right.left = cur;
        return dummy.right;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    @Test
    public void test1() {

    }

}
