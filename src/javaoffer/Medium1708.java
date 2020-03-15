package javaoffer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 有个马戏团正在设计叠罗汉的表演节目，一个人要站在另一人的肩膀上。
 * 出于实际和美观的考虑，在上面的人要比下面的人矮一点且轻一点。
 * 已知马戏团每个人的身高和体重，请编写代码计算叠罗汉最多能叠几个人。
 *
 * 示例：
 * 输入：height = [65,70,56,75,60,68] weight = [100,150,90,190,95,110]
 * 输出：6
 * 解释：从上往下数，叠罗汉最多能叠 6 层：(56,90), (60,95), (65,100), (68,110), (70,150), (75,190)
 * 提示：
 *
 * height.length == weight.length <= 10000
 *
 * 思路：
 * 这里先使用sort根据height从小到大排序，其中关键的是如果height相同则按照weight从大到小排序（注意一个是从小到大，一个是从大到小）。
 * 这样安排是为了让后续的根据weight求最大上升子序列时不会选到height一样的数据，
 * 即让height相同的数据的weight从大到小排列，让它不满足上升序列的“上升”，这样就巧妙避开了重复选取。
 * 最后就是根据weight值求最长上升子序列的长度，即leetcode300的方法。
 *
 * Arrays.binarySearch():左闭右开！
 * [1] 搜索值不是数组元素，且在数组范围内，从1开始计数，得“ - 插入点索引值”；
 *
 * [2] 搜索值是数组元素，从0开始计数，得搜索值的索引值；
 *
 * [3] 搜索值不是数组元素，且大于数组内元素，索引值为 – (length + 1);
 *
 * [4] 搜索值不是数组元素，且小于数组内元素，索引值为 – 1。
 * ————————————————
 *
 */
public class Medium1708 {

    public int bestSeqAtIndex(int[] height, int[] weight) {
        int len = height.length;
        int[][] person = new int[len][2];
        for (int i = 0; i < len; ++i)
            person[i] = new int[]{height[i], weight[i]};

        Arrays.sort(person, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        /*接下来求体重的最长上升子序列长度*/
        int[] dp = new int[len];
        int res = 0;
        for (int[] p : person) {
            /*二分查找法，返回p[1]应该所在的位置，
             * 若p[1]不在数组中，返回负数*/
            int i = Arrays.binarySearch(dp, 0, res, p[1]);
            /*如果i==-1表示：p[1]不在dp范围内，且比dp元素都小，将他映射到dp[0]
             * 如果i==-(len+1)表示：p[i]不在dp范围内，且比dp都大,将他映射到dp[res]*/
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = p[1];
            if (i == res)
                ++res;
        }
        return res;
    }

    //*********************************************************************************
    @Test
    public void test1() {
        //        int[] test = new int[]{2, 4, 6, 8, 10};
        //        System.out.println(Arrays.binarySearch(test, 0, 5, 11));

        int[] h = new int[]{2868, 5485, 1356, 1306, 6017, 8941, 7535, 4941, 6331, 6181};
        int[] w = new int[]{5042, 3995, 7985, 1651, 5991, 7036, 9391, 428, 7561, 8594};
        int res = bestSeqAtIndex(h, w);
        System.out.println(res);
    }
}
