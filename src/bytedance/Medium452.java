package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。
 * 开始坐标总是小于结束坐标。
 * 平面内最多存在104个气球。
 * <p>
 * 一支弓箭可以沿着x轴从不同点完全垂直地射出。
 * 在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
 * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
 * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * <p>
 * Example:
 * <p>
 * 输入:
 * [[10,16], [2,8], [1,6], [7,12]]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 对于该样例，我们可以在x = 6（射爆[2,8],[1,6]两个气球）和 x = 11（射爆另外两个气球）。
 * <p>
 * 思路：先排序！！，遍历所有区间，按照最小交集合并，最后有多少个区间就是需要多少只箭。。
 */
public class Medium452 {
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparingInt(n -> n[0]));
        List<int[]> small = new ArrayList<>();
        small.add(points[0]);
        for (int i = 1; i < points.length; i++) {
            int[] point = points[i];
            boolean didMerge = false;
            for (int[] smallPoint : small) {
                if ((point[0] <= smallPoint[1] && point[0] >= smallPoint[0]) ||//新区间起点在小区间里
                        (point[1] >= smallPoint[0] && point[1] <= smallPoint[1]) ||//新区间终点在小区间里
                        (point[0] >= smallPoint[0] && point[1] <= smallPoint[1]) ||//新区间在小区间里
                        (point[0] <= smallPoint[0] && point[1] >= smallPoint[1])) //小区间起点在新区间里
                {//四种交集情况
                    smallPoint[0] = Math.max(point[0], smallPoint[0]);
                    smallPoint[1] = Math.min(point[1], smallPoint[1]);
                    didMerge = true;
                    break;
                }
            }
            if (!didMerge) small.add(point);
        }
        return small.size();
    }

    //**************************************************************************************

    /**
     * 换一种想法：按照区间终点排序，则对于第一个终点A来说，若有区间的起点<=A<=区间的终点，那么这些区间都可以通过从A射箭来打爆
     * 直到出现一个区间，他的起点>A，则需要新的箭来射。
     */
    public int findMinArrowShots2(int[][] points) {
        int len = points.length;
        if (len < 2) return len;

        // 按照区间终点进行排序
        Arrays.sort(points, (p1, p2) -> {
            if (p1[1] != p2[1]) return p1[1] - p2[1];
            return p1[0] - p2[0];//终点相等按照起点排序
        });

        int count = 1;
        int end = points[0][1];
        for (int i = 1; i < len; i++) {
            if (points[i][0] > end) {//只要该区间起点>A，则需要新的箭来射。
                // 就得多用一支箭
                end = points[i][1];//更新下一个终点
                count++;
            }
        }
        return count;
    }

    @Test
    public void test1() {
        int[][] balloon1 = new int[][]{//2
                {10, 16},
                {2, 8},
                {1, 6},
                {7, 12}
        };
        int[][] balloon2 = new int[][]{//2
                {9, 12},
                {1, 10},
                {4, 11},
                {8, 12},
                {3, 9},
                {6, 9},
                {6, 7},
        };
        int[][] balloon3 = new int[][]{//2
                {3, 9},
                {7, 12},
                {3, 8},
                {6, 8},
                {9, 10},
                {2, 9},
                {0, 9},
                {3, 9},
                {0, 6},
                {2, 8}
        };
//        System.out.println(findMinArrowShots(balloon1));
//        System.out.println(findMinArrowShots(balloon2));
        System.out.println(findMinArrowShots2(balloon3));
    }
}
