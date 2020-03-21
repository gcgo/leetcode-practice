package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 *
 * 特别地，我们希望可以就地完成转换操作。
 * 当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。
 *
 * 本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
 *
 * 思路：有序那肯定是中序遍历。可以中序遍历出来，然后挨个链接。
 *
 */
public class Medium36 {

	public Node treeToDoublyList(Node root) {
		if (root==null)return null;
		//非递归获得中序遍历序列
		Deque<Node> stack = new LinkedList<>();
		List<Node> inorder = new ArrayList<>();
		Node p = root;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				p = stack.pop();
				inorder.add(p);
				p = p.right;
			}
		}
		//获得了中序遍历结果
		for (int i = 1; i < inorder.size(); i++) {
			inorder.get(i - 1).right = inorder.get(i);
			inorder.get(i).left = inorder.get(i - 1);
		}
		//链接首尾
		inorder.get(0).left = inorder.get(inorder.size() - 1);
		inorder.get(inorder.size() - 1).right = inorder.get(0);
		//返回第一个节点
		return inorder.get(0);
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
