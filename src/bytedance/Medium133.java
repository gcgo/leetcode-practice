package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。
 * <p>
 * 图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。
 * <p>
 * class Node {
 * public int val;
 * public List<Node> neighbors;
 * }
 * <p>
 * 测试用例格式：
 * <p>
 * 简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1，第二个节点值为 2，以此类推。该图在测试用例中使用邻接列表表示。
 * <p>
 * 邻接列表是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。
 * <p>
 * 给定节点将始终是图中的第一个节点（值为 1）。你必须将 给定节点的拷贝 作为对克隆图的引用返回。
 * <p>
 * 点数介于 1 到 100 之间。
 * 每个节点值都是唯一的。
 * 无向图是一个简单图，这意味着图中没有重复的边，也没有自环。
 * 由于图是无向的，如果节点 p 是节点 q 的邻居，那么节点 q 也必须是节点 p 的邻居。
 * 图是连通图，你可以从给定节点访问到所有节点。
 * <p>
 * 思路：每个节点值都是唯一的，那就可以用一个map记录处理过的节点，dfs来遍历所有节点，克隆
 */
public class Medium133 {

    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>();//<老node，clone的Node>
        return dfs(node, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {//bfs
        if (node == null) return null;
        if (map.containsKey(node)) return map.get(node);//关联过就直接返回
        Node clone = new Node(node.val, new ArrayList<>());//否则新建一个
        map.put(node, clone);//关联一下
        for (Node n : node.neighbors) clone.neighbors.add(dfs(n, map));
        return clone;
    }

    //*******************************************Dfs*******************************************
    public Node cloneGraph2(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Node clone = new Node(node.val, new ArrayList<>());
        map.put(node, clone);
        Deque<Node> queue = new LinkedList<>();
        queue.offer(node);//所给老节点先入队
        while (!queue.isEmpty()) {
            Node tmp = queue.poll();//老节点
            for (Node n : tmp.neighbors) {//遍历老节点的关系网n
                if (!map.containsKey(n)) {//如果n没有关联新clone，证明还没clone过
                    map.put(n, new Node(n.val, new ArrayList<>()));//那就clone一个,并关联起来
                    queue.offer(n);//老节点n入队
                }
                //将n关联的clone，填充到老节点clone的关系网上，
                // 即n是tmp的关系网之一，所以map.get(n)也要是map.get(tmp)的关系网之一
                map.get(tmp).neighbors.add(map.get(n));
            }
        }
        return clone;
    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    @Test
    public void test1() {

    }
}
