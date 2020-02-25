package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 力扣数据中心有 n 台服务器，分别按从 0 到 n-1 的方式进行了编号。
 * <p>
 * 它们之间以「服务器到服务器」点对点的形式相互连接组成了一个内部集群，其中连接 connections 是无向的。
 * <p>
 * 从形式上讲，connections[i] = [a, b] 表示服务器 a 和 b 之间形成连接。任何服务器都可以直接或者间接地通过网络到达任何其他服务器。
 * <p>
 * 「关键连接」是在该集群中的重要连接，也就是说，假如我们将它移除，便会导致某些服务器无法访问其他服务器。
 * <p>
 * 请你以任意顺序返回该集群内的所有 「关键连接」。
 *
 *
 * 概念：
 * 如果N个节点可以形成一个闭环，那么切掉闭环上任意一条边是不影响其连通的。
 * 反之这N个节点是一条线（直线或折线），那么任意一条边切断就会将这条线分为2段，这条被切断的边即是本题中提到的「关键连接」。
 * 所以如果一条边是关键链接，那么他肯定不在一个闭环里！！！！！
 *
 * 解法：
 * tarjan算法，求图的割边（桥）
 * 问题：我们怎么找到环呢？
 * 使用dfs来判断。
 * 为每一个节点定义一个深度属性，即当前节点所处dfs的深度，初始值为0。
 * 每通过dfs来到下一个节点，则该节点深度加1。
 * 所以，如果我们的节点没有环的话，它的深度会越来越大。
 * 但是，当下一个节点的深度小于当前节点时，则证明我们找到了一个环路，即回去了，不再往深走了。
 * 问题：如何知道下一个节点的深度呢？
 * 让dfs回溯时返回一个值，该值是该节点所能找到的最小深度。
 * 这样的话，比如现在有u->v，u当前的深度为3，它调用v的dfs返回值，发现v找到的最小深度为2，则证明：
 * v找到了另外一条路，回到了u的前面。即闭环出现。同时证明，当前的u->v在环路中。
 *
 * 根据这个逻辑，引申思考：
 * 第一个节点开始dfs；
 * 他的深度为0，最小深度当前深度，即也是0；
 * 接着它去dfs下一个节点，并等待回溯的返回值，若返回值小于当前深度，则更新当前深度值为此返回值。
 * 若返回值大于当前节点的深度，则当前节点->下一个节点的这段连接为关键链接！！！
 *
 *
 */
public class Hard1192 {

    List<List<Integer>> res = new ArrayList<>(); // 返回结果
    int[] deepArray; // 深度数组，即用来标记该节点是否走过，也记录下标对应节点的深度。
    ArrayList<Integer>[] map; // 链接表，是一个ArrayList数组

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        deepArray = new int[n]; // 初始化深度数组
        Arrays.fill(deepArray, -1); // 所有节点初始深度为-1
        map = new ArrayList[n]; // 初始化结构图map[i]代表节点i可以连通哪些节点
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();//数组元素是ArrayList
        }
        // 构建路径图,即记录每个节点直接连接了哪些其他节点。另一种表达方式是邻接矩阵
        for (List<Integer> connection : connections) {
            map[connection.get(0)].add(connection.get(1));
            map[connection.get(1)].add(connection.get(0));
        }
        dfs(0, 0, 0); // 开始dfs，将第一个节点初始深度置0
        return res;
    }

// current为当前节点
// previous为前节点
// deep为当前深度
// 返回值为当前节点所有dfs路径终点的最小深度
    private int dfs(int current, int previous, int deep) {
        deepArray[current] = deep; // 将当前深度存入深度数组
        // 返回值，返回的是该节点走到尽头的最小深度
        int result = deep;//返回值初始值为当前深度。
        for (int i : map[current]) { // 遍历当前节点能走的所有节点
            if (i == previous) { // 不能走回头路
                continue;
            }
            int endDeep; // 下一个节点dfs终点深度
            if (deepArray[i] == -1) { // 深度为-1的点没走过，可以dfs
                endDeep = dfs(i, current, deep + 1);
                // 如果下一个节点的最终深度，即更新完一圈后，深度还是大于当前深度，
                // 说明下一个节点无法不通过当前节点而回到更早的祖先节点，即下一个节点不在圈里，所以当前节点为割点，
                // 当前节点与下一个节点的路径为桥！！！！
                // 即找到一组关键链接，保存
                if (endDeep > deep) {
                    List<Integer> list = new ArrayList<>();
                    list.add(current);
                    list.add(i);
                    res.add(list);
                }
            } else {
                // i节点深度不为-1，说明已经走过，i节点为dfs终点，直接从深度纪录表中更新
                endDeep = deepArray[i];
            }
            // 更新最小深度，实际比较的就是下一个节点深度和当前节点深度，取最小值
            result = Math.min(result, endDeep);
        }
        return result; // 返回最小深度
    }




    @Test
    public void test1() {
        List<List<Integer>> con = new ArrayList<>(4);
        List<Integer> list = new ArrayList<>(2);
        list.add(0);
        list.add(1);
        con.add(new ArrayList<>(list));
        list.clear();
        list.add(1);
        list.add(2);
        con.add(new ArrayList<>(list));
        list.clear();
        list.add(2);
        list.add(0);
        con.add(new ArrayList<>(list));
        list.clear();
        list.add(1);
        list.add(3);
        con.add(new ArrayList<>(list));
        List<List<Integer>> lists = criticalConnections(4, con);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
