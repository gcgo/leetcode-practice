package bytedance;

import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 在由 2D 网格表示的校园里有 n 位工人（worker）和 m 辆自行车（bike），n <= m。所有工人和自行车的位置都用网格上的 2D 坐标表示。
 * 我们需要为每位工人分配一辆自行车。
 * 在所有可用的自行车和工人中，我们选取彼此之间曼哈顿距离最短的工人自行车对  (worker, bike) ，并将其中的自行车分配給工人。
 * 如果有多个 (worker, bike) 对之间的曼哈顿距离相同，那么我们选择工人索引最小的那对。
 * 类似地，如果有多种不同的分配方法，则选择自行车索引最小的一对。不断重复这一过程，直到所有工人都分配到自行车为止。
 * 给定两点 p1 和 p2 之间的曼哈顿距离为 Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|。
 * 返回长度为 n 的向量 ans，其中 a[i] 是第 i 位工人分配到的自行车的索引（从 0 开始）。
 * <p>
 * 输入：workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
 * 输出：[1,0]
 *  解释：
 *  * 工人 1 分配到自行车 0，因为他们最接近且不存在冲突，工人 0 分配到自行车 1 。所以输出是 [1,0]。
 *
 * 输入：workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
 * 输出：[0,2,1]
 * 解释：
 * 工人 0 首先分配到自行车 0 。工人 1 和工人 2 与自行车 2 距离相同，因此工人 1 分配到自行车 2，工人 2 将分配到自行车 1 。
 * 因此输出为 [0,2,1]。
 *
 * 提示：
 *
 * 0 <= workers[i][j], bikes[i][j] < 1000
 * 所有工人和自行车的位置都不相同。
 * 1 <= workers.length <= bikes.length <= 1000
 *
 * 思路：建立距离哈希表，key是距离，value是该距离的所有工人自行车对儿（set集合）
 * 这样可以固定距离，然后对工人和自行车优先级排序
 */
public class Medium1057 {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, Set<int[]>> map = new TreeMap<>();//<距离，该距离的[工人,自行车]集合>
        for (int i = 0; i < workers.length; i++) {
            for (int j = 0; j < bikes.length; j++) {
                int d = Math.abs(workers[i][0] - bikes[j][0]) + Math.abs(workers[i][1] - bikes[j][1]);
                map.putIfAbsent(d, factory());
                map.get(d).add(new int[]{i, j});
            }
        }
        int[] res = new int[workers.length];
        boolean[] wUsed = new boolean[workers.length], bUsed = new boolean[bikes.length];
        for (int key : map.keySet()) {
            for (int[] e : map.get(key)) {
                int w = e[0], b = e[1];//提取出相同距离的工人-自行车对儿
                if (wUsed[w] || bUsed[b])//如果工人自行车处理过了，则不考虑
                    continue;
                res[w] = b;//否则就让该工人选这辆车
                wUsed[w] = true;//标记
                bUsed[b] = true;//标记
            }
        }
        return res;
    }

    private Set<int[]> factory() {
        return new TreeSet<>((a, b) -> {//数组排序，优先比较工人序号，工人相等，比较自行车序号
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
    }

    @Test
    public void test1() {

    }
}
