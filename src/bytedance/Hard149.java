package bytedance;

import org.junit.Test;

/**
 * 给定一个二维平面，平面上有 n 个点，求最多有多少个点在同一条直线上。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,1],[2,2],[3,3]]
 * 输出: 3
 * 解释:
 * ^
 * |
 * |        o
 * |     o
 * |  o  
 * +------------->
 * 0  1  2  3  4
 * 示例 2:
 * <p>
 * 输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出: 4
 * 解释:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 * <p>
 * 思路：网友题解
 * 问题转化为：同一条直线肯定斜率相等，我们遍历所有点，用点斜式算出斜率存入哈希表中。
 * 斜率不能用float存储，精度会不对，所以用分数，分别计算分子分母，约分后用“分子@分母”的形式作为key存储，value是该斜率出现的次数。
 * 由于点斜式能唯一确定一条直线
 *
 * 没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！
 * 没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！
 * 没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！没整明白！！
 */
public class Hard149 {
    public int maxPoints(int[][] points) {
        return 0;
    }

    //**************************************************************************
    @Test
    public void test1() {

    }

}
