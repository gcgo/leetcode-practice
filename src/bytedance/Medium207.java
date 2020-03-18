package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 提示：
 *
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 *
 * 思路：拓扑排序。
 * 入度表（广度优先遍历）
 * 算法流程：
 * 统计课程安排图中每个节点的入度，生成 入度表 indegrees。
 * 借助一个队列 queue，将所有入度为 0 的节点入队。
 * 当 queue 非空时，依次将队首节点出队，在课程安排图中删除此节点 pre：
 * 并不是真正从邻接表中删除此节点 pre，而是将此节点对应所有邻接节点 cur 的入度 -1，即 indegrees[cur] -= 1。
 * 当入度 -1后邻接节点 cur 的入度为 0，说明 cur 所有的前驱节点已经被 “删除”，此时将 cur 入队。
 * 在每次 pre 出队时，执行 numCourses--；
 * 若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。
 * 换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 0。
 * 因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
 *
 */
public class Medium207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*入度表*/
        int[] indegrees = new int[numCourses];
        /*邻接表*/
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        /*构建邻接表*/
        for (int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // Get the indegree and adjacency of every course.
        for (int[] cp : prerequisites) {
            indegrees[cp[0]]++;//更新入度，通过课程依赖表查看，它要依赖别人，它的入度就加一
            adjacency.get(cp[1]).add(cp[0]);//更新邻接表
        }
        // 将入度为0的课程入队
        for (int i = 0; i < numCourses; i++)
            if (indegrees[i] == 0) queue.add(i);
        // BFS TopSort.
        while (!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            /*遍历该门课的所有下家，入度都减1*/
            for (int cur : adjacency.get(pre)) {
                indegrees[cur]--;//入度先减1
                if (indegrees[cur] == 0) queue.add(cur);//如果入度为0了，就将它入队
            }
        }
        return numCourses == 0;//如果所有课程都能遍历完，证明可以学完，若不能则证明始终有课程入度不为0，即图中有环
    }

    //**************************************************************************
    @Test
    public void test1() {

    }

}
