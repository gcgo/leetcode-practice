package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
 * <p>
 * 你需要按照以下要求，帮助老师给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。
 * 那么这样下来，老师至少需要准备多少颗糖果呢？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,0,2]
 * 输出: 5
 * 解释: 你可以分别给这三个孩子分发 2、1、2 颗糖果。
 * <p>
 * 思路：
 * 典型的贪心算法
 * * 题本身可以用贪心法来做，我们用candy[n]表示每个孩子的糖果数，遍历过程中，
 * * 如果孩子i+1的rate大于孩子i 的rate，那么当前最好的选择自然是：给孩子i+1的糖果数=给孩子i的糖果数+1
 * * 如果孩子i+1的rate小于等于孩子i 的rate咋整？这个时候就不大好办了，
 * * 因为我们不知道当前最好的选择是给孩子i+1多少糖果。
 * * 解决方法是：暂时不处理这种情况。等数组遍历完了，我们再一次从尾到头遍历数组，
 * * 这回逆过来贪心，就可以处理之前略过的孩子。
 * * 最后累加candy[n]即得到最小糖果数。
 */
public class Hard135 {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length <= 0)
            return 0;

        int[] candy = new int[ratings.length];//表示每个孩子的糖果数
        Arrays.fill(candy, 1);//先都至少给上1块糖

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1])//贪心法则：后面分数比前一个高的话就给我照前一个的糖数多发一个
                candy[i] = candy[i - 1] + 1;//没考虑我的分数比前一个低咋办。。。。
            // 示例数组这步走完糖数为：[1,1,2]
        }

        for (int i = ratings.length - 2; i >= 0; i--) {//倒着来一遍贪心，即前面分数比后面分数低的话，
            // 且前面糖数还不比后面多。则前面糖数照后面糖数加1。
            if (ratings[i] > ratings[i + 1] && candy[i] < candy[i + 1] + 1)
                candy[i] = candy[i + 1] + 1;
        }

        int sum = 0;
        for (int value : candy) sum += value;
        return sum;
    }

    @Test
    public void test1() {
        System.out.println(candy(new int[]{1, 0, 2}));
    }
}
