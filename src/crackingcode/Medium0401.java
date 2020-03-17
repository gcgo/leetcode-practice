package crackingcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 节点间通路。给定有向图，设计一个算法，找出两个节点之间是否存在一条路径。
 *
 * 示例1:
 *  输入：n = 3, graph = [[0, 1], [0, 2], [1, 2], [1, 2]], start = 0, target = 2
 *  输出：true
 *
 * 示例2:
 *  输入：n = 5, graph = [[0, 1], [0, 2], [0, 4], [0, 4], [0, 1], [1, 3], [1, 4], [1, 3], [2, 3], [3, 4]],
 *  start = 0, target = 4
 *  输出 true
 * 提示：
 *
 * 节点数量n在[0, 1e5]范围内。
 * 节点编号大于等于 0 小于 n。
 * 图中可能存在自环和平行边。
 *
 * 思路：dfs,bfs
 * 题目所给graph相当于边集，我们首先要把边集转换成邻接矩阵
 *
 */
public class Medium0401 {
	/*dfs*/
	public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
		/*新建一个数组记录访问情况*/
		boolean[] visited = new boolean[n];
		/*将边集转换为邻接表,由map表示<顶点，相连顶点的集合>*/
		Map<Integer, LinkedList<Integer>> map = new HashMap<>();
		for (int[] edge : graph) {
			//若map中还没有这个顶点对应的集合，则新建一个存起来然后返回
			LinkedList<Integer> list = map.computeIfAbsent(edge[0], from -> new LinkedList<>());
			list.addLast(edge[1]);//添加关系
		}
		/*dfs深度优先遍历*/
		return dfs(map, start, target, visited);
	}

	public boolean dfs(Map<Integer, LinkedList<Integer>> map, int start, int target, boolean[] visited) {
		/*如果新的起始点就是我们要找的终点，即找到了*/
		if (start == target) return true;
		/*否则以当前start为起点继续深度查找*/
		visited[start] = true;
		if (map.get(start) == null) return false;
		//通过邻接表找他能到达的邻居，并以邻居为起点继续找
		LinkedList<Integer> neighbors = map.get(start);
		for (int neighbor : neighbors) {
			if (!visited[neighbor])
				if (dfs(map, neighbor, target, visited)) return true;
		}
		/*回溯，即以该节点开头都找不到*/
		visited[start] = false;
		return false;
	}

	/*bfs*/
	public boolean findWhetherExistsPath2(int n, int[][] graph, int start, int target) {
		/*新建一个数组记录访问情况*/
		boolean[] visited = new boolean[n];
		/*将边集转换为邻接表,由map表示<顶点，相连顶点的集合>*/
		Map<Integer, LinkedList<Integer>> map = new HashMap<>();
		for (int[] edge : graph) {
			//若map中还没有这个顶点对应的集合，则新建一个存起来然后返回
			LinkedList<Integer> list = map.computeIfAbsent(edge[0], from -> new LinkedList<>());
			list.addLast(edge[1]);//添加关系
		}
		/*bfs广度优先搜索*/
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int s = queue.poll();
			if (s == target) return true;
			if (!visited[s]) {
				visited[s] = true;
				LinkedList<Integer> neighbors = map.get(s);
				if (neighbors != null) queue.addAll(neighbors);
			}
		}
		return false;
	}

	@Test
	public void test() {
	}

}

