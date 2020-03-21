package bytedance;

import org.junit.Test;

/**
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * <p>
 * 要求返回这个链表的 深拷贝。 
 * <p>
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * <p>
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 *
 *
 * 思路：原地复制，在每一个节点后面复制自己，然后再填充random，最后再断开。
 * 这道题解法和Spring解决循环依赖的原理很像，先构造对象，所有对象齐了，最后再统一填充属性
 */
public class Medium138 {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next;
        //1复制新链表，只复制next指针，不复制random指针
        while (cur != null) {
            next = cur.next;//保存一下
            Node newNode = new Node(cur.val);//复制
            newNode.next = cur.next;//插入到cur后面
            cur.next = newNode;
            cur = next;
        }
        cur = head;
        //2复制random指针
        while (cur != null) {
            next = cur.next.next;
            //cur.random是老链表，cur.random.next才是新链表
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        //3把复制链表和源链表分开
        cur = head;
        Node newHead = cur.next;
        Node copyCur = null;
        while (cur != null) {
            //record the next node
            next = cur.next.next;
            copyCur = cur.next;
            cur.next = next;
            copyCur.next = next != null ? next.next : null;
            cur = next;
        }
        return newHead;

    }

    //Definition for a Node.
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    @Test
    public void test1() {
        Node test = new Node(1);
        Node test2 = test ;
        System.out.println(test==test2);

    }

}
