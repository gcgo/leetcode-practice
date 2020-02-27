package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
 * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[0, 30],[5, 10],[15, 20]]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [[7,10],[2,4]]
 * 输出: 1
 * <p>
 * 思路：会议按照升序排列，记录正在开会的所有会议的结束时间。
 * 每当有新会议时，就遍历所有房间，查看是否有会议结束时间小于等于新会议开始时间的，有就复用，删除旧的结束时间，添加新的会议结束时间。
 * 可以用最小堆来保存会议结束时间，这样每次都返回最早结束的那个会议。
 */
public class Medium253 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(t -> t[0]));//按照会议开始时间排序。
        int rooms = 0;
        PriorityQueue<Integer> endTime = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            if (i > 0 && intervals[i][0] >= endTime.peek()) endTime.poll();
            else rooms++;
            endTime.offer(intervals[i][1]);
        }
        return rooms;
    }

    @Test
    public void test1() {
        int[][] intervals1 = new int[][]{
                {9, 10},
                {4, 9},
                {4, 17}
        };
        System.out.println(minMeetingRooms(intervals1));//2

        int[][] intervals2 = new int[][]{
                {2, 11},
                {6, 16},
                {11, 16}
        };
        System.out.println(minMeetingRooms(intervals2));//2

        int[][] intervals3 = new int[][]{
                {0, 30},
                {5, 10},
                {15, 20}
        };
        System.out.println(minMeetingRooms(intervals3));//2
    }
}
