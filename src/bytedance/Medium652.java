package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 *
 * 示例 1：
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   2   4
 *        /
 *       4
 * 下面是两个重复的子树：
 *
 *       2
 *      /
 *     4
 * 和
 *
 *     4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 *
 * 思路：dfs,遍历出所有组合，存入map集合，key是所有路径，格式为（n1n2n3...）,value是路径的根节点
 * 最后返回路径对应根节点数大于1的其中一个根节点即可。
 */
public class Medium652 {

	public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
		Map<String, List<TreeNode>> map = new HashMap<>();
		List<TreeNode> dups = new ArrayList<>();
		serialize(root, map);
		for (List<TreeNode> group : map.values())
			if (group.size() > 1) dups.add(group.get(0));
		return dups;
	}

	private String serialize(TreeNode node, Map<String, List<TreeNode>> map) {
		if (node == null) return "";
		String s = "(" + serialize(node.left, map) + node.val + serialize(node.right, map) + ")";
		if (!map.containsKey(s)) map.put(s, new ArrayList<>());
		map.get(s).add(node);
		return s;
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

	}
}
