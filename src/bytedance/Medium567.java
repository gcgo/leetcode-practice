package bytedance;

import org.junit.Test;

/**
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 *思路：就是求证s2中是否存在1个区间，该区间长度为s1.length()(即s1字符串的长度)，
 * 且该区间内 各个字符 的个数和s1各个字符 的个数 都一一相等。
 * 既然区间长度是固定的(s1.length())，则将该区间从s2的最左端滑动到最右端即可。
 */
public class Medium567 {
    public boolean checkInclusion(String s1, String s2) {
        int s1_len = s1.length(), s2_len = s2.length();
        if (s1_len > s2_len) return false;
        //s1_ch存储s1中出现字母的个数、s2_ch存储s2中出现字母的个数
        int[] s1_ch = new int[26], s2_ch = new int[26];
        for (int i = 0; i < s1_len; ++i) {
            s1_ch[s1.charAt(i) - 'a']++;
            s2_ch[s2.charAt(i) - 'a']++;
        }
        for (int i = s1_len; i < s2_len; ++i) {
            if (isEqual(s1_ch, s2_ch)) return true;
            //如果s2的当前区间不行，则往右边平移一个位置
            --s2_ch[s2.charAt(i - s1_len) - 'a'];
            ++s2_ch[s2.charAt(i) - 'a'];
        }
        return isEqual(s1_ch, s2_ch);
    }

    /*对应字母数量是否相等*/
    private boolean isEqual(int[] s1_ch, int[] s2_ch) {
        for (int i = 0; i < 26; ++i)
            if (s1_ch[i] != s2_ch[i])
                return false;
        return true;
    }

    @Test
    public void test1() {
    }
}
