package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 思路：依次遍历所有矩形条，尝试计算以该矩形条为高度的矩形面积。
 * 用stack存index，stack保持index对应高度的升序，单调栈，当遇到高度比栈顶index对应高度小时，就去计算高度:
 * 例如，现在heights[i]比栈顶对应的高度A小，则面积=A*（右边界-左边界-1）或A*（右边界-0）
 * 右边界：i
 * 左边界：以A为高的矩形，左边界只能延伸到第一个比他矮的位置，即栈中下一个。
 *         所以栈不为空，则是A弹出后的栈顶；栈为空，则证明左边没有比A再矮的了，则可以延伸到0
 * 高度：A
 * 当确定右边界后，需要遍历栈中每一个位置的高度，计算面积，取最大值。
 */
public class Hard84 {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Deque<Integer> s = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {//注意i的范围！！
            int h = (i == len ? 0 : heights[i]);//给数组末尾添个0
            if (s.isEmpty() || h >= heights[s.peek()]) {//如果高度比栈顶元素大，则入栈
                s.push(i);
            } else {//否则
                int tp = s.pop();
                maxArea = Math.max(maxArea, heights[tp] * (s.isEmpty() ? i : i - (s.peek() + 1)));
                i--;
            }
        }
        return maxArea;
    }

    @Test
    public void test1() {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }
}
