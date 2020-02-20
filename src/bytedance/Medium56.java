package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 *
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 *
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 思路：
 * 所有区间先按照左边界排序。
 * 若当前区间右边界比下一个区间左边界大，证明有交叉，则新区间为[当前区间]
 */
public class Medium56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // 区间按照左边界大小排序
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0];
        result.add(newInterval);//先把数组存进列表里，外边改数组值，依然会影响列表的值。java值传递！！！
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) // 如果下一个区间的左边界<=上一个区间的右边界，证明重合
                //更新新区间的右边界为：上一个区间和下一个区间右边界大的
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            else {//否则证明没有重合，则该区间为其中一个结果！！
                newInterval = interval;//java值传递！！此处newInterval指向新的对象，不影响原来存在result中的那个值
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }


    @Test
    public void test1() {
        int[][] res = new int[][] { {1,3},{2,6},{8,10},{15,18} };

        System.out.println(Arrays.deepToString(merge(res)));

    }
}
