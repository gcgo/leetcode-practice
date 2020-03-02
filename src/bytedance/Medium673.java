package bytedance;

import org.junit.Test;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * <p>
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * <p>
 * 思路：所谓子序列，隔着也算。
 * 动态规划，len[i]表示以i结尾的最长子序列长度;
 * cnt[i]表示以i结尾的最长子串的数目。
 */
public class Medium673 {
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        int[] len = new int[n], cnt = new int[n];
        for (int i = 0; i < n; i++) {//固定i
            len[i] = cnt[i] = 1;
            for (int j = 0; j < i; j++) {//j在0到i遍历
                if (nums[i] > nums[j]) {//如果有比num[i]小的，则证明num[i]可以为这个num[j]结尾的子串增加一个长度
                    //如果以j结尾的子串最长的长度+1==以i结尾的子串最长长度
                    //则以i结尾的最长子串数目可以再添加cnt[j]个。
                    if (len[i] == len[j] + 1) cnt[i] += cnt[j];
                    //如果长度超过原来以i结尾的最长的长度，则更新数据
                    if (len[i] < len[j] + 1) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if (max_len == len[i]) res += cnt[i];//记录当前最长的长度，并更新个数。
            if (max_len < len[i]) {
                max_len = len[i];
                res = cnt[i];
            }
        }
        return res;
    }

    @Test
    public void test1() {

    }
}
