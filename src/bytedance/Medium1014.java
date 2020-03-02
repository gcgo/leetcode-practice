package bytedance;

import org.junit.Test;

/**
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 * <p>
 * 思路：就是求A[i] + A[j] + i - j的最大值
 */
public class Medium1014 {
    public int maxScoreSightseeingPair(int[] A) {
        int one = 0;//第一个景点
        int res = A[0] + A[1] - 1;
        for(int two = 1; two < A.length; two++){//第二个景点
            int point = A[one]+A[two]+one-two;//线路得分
            res = Math.max(res, point);
            if(A[two]+two>A[one]+one)//如果景点2更适合做起点，那就改变起点
                one = two;
        }
        // cout << left<<right<<endl;
        return res;
    }

    @Test
    public void test1() {

    }
}
