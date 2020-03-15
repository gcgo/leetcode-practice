package bytedance;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 思路：贪心算法，若果区间重合，则删除右边界大的那个，因为它更可能和别的区间继续重合。
 */
public class Medium435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        /*先按照区间起始位置排序*/
        Arrays.sort(intervals, Comparator.comparingInt(n -> n[0]));
        /*考察*/
        int end = intervals[0][1];//记录目前为止的结束点
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {//有重合
                end = Math.min(end, intervals[i][1]);//相当于谁小谁就留下来了
                res++;
            } else {//没有重合
                end = intervals[0][1];//更新结束时间点
            }
        }
        return res;
    }

    @Test
    public void test1() {

    }

}
