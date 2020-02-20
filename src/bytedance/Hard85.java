package bytedance;

import org.junit.Test;

import java.util.Stack;

/**
 * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * 输出: 6
 * 思路：请看84题，求最大长方形面积，这个题就是循环把前[1-n]行的1作为高，求出每一层的 heights[] 然后传给上一题的函数就可以了。
 * 第一次传，前一行[1,0,1,0,0]
 * 第二次传，前两行[2,0,2,1,1]
 * 第三次传，前三行[3,1,3,2,2]
 * 第四次传，前四行[4,0,0,3,0]
 * 注意：最底下一行为0，则该列就为0。（没有地基）
 */
public class Hard85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0)return 0;
        int[] heights = new int[matrix[0].length];
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0')
                    heights[j] = 0;
                else //matrix[i][j]=='1'
                    heights[j] = heights[j] + 1;
            }
            int max = largestRectangleArea(heights);
            res = Math.max(res, max);
        }
        return res;
    }

    private int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<Integer>();
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
        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };

    }
}
