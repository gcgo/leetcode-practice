package bytedance;

import org.junit.Test;

/**
 * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，每步可以删除任意一个字符串中的一个字符。
 *
 * 示例 1:
 *
 * 输入: "sea", "eat"
 * 输出: 2
 * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
 * 说明:
 *
 * 给定单词的长度不超过500。
 * 给定单词中的字符只含有小写字母。
 *
 *思路：问题转化为求Word1和Word2的最长公共子串，求得后只要两个字符串都删除其他的就行了。
 * 自顶向下分析，dp[i][j]定义为Word1以i结尾Word2以j结尾的最长公共子串长度。
 * 那么dp[i][j]=
 * 若Word1[i]==Word2[j],则dp(i, j) = dp(i - 1, j - 1) + 1
 * 若Word1[i]！=Word2[j],则dp(i, j) = max(dp(i - 1, j ),dp(i, j - 1 ))
 *
 */
public class Medium583 {
    public int minDistance(String word1, String word2) {
        int l1 = word1.length(), l2 = word2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];//多申请一个，方便计算，dp[0][0]=0为了方便计算
        for (int i = 1; i <= l1; i++) {
            for (int j = 1; j <= l2; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1))//dp[1][1]对应Word1[0],Word2[0]
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return l1 + l2 - 2 * dp[l1][l2];//最小步数等于两个字符串长度都减去最长公共子串后剩下的字符数
    }

    @Test
    public void test1() {
    }
}
