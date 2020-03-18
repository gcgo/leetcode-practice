package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。
 *
 * 示例 1:
 * 输入:
 * s = "aaabb", k = 3
 * 输出:
 * 3
 * 最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2:
 * 输入:
 * s = "ababbc", k = 2
 * 输出:
 * 5
 * 最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *
 * 思路：
 * 分治：对于一个字符串来说，如果要求子串最少出现k次，那么如果某些字母出现的次数小于k,
 * //这些字母一定不会出现在最长的子串中，并且这些字母将整个字符子串分割成小段，这些小段有可能是最长的
 * //但是由于被分割了，还是要检查这一小段，如果某些字母出现的次数小于k,会将小段继续分割下去,
 * //比如字符串"aacbbbdc"，要求最少出现2次,我们记录左右闭区间，，
 * //第一轮[0,7]，处理"aacbbbdc"，d只出现了一次不满足，于是递归解决区间[0,5]、[7,7]
 * //第二轮[0,5]，处理"aacbbb"，  c只出现了一次不满足，于是递归解决区间[0,1]、[3,4]
 * //第二轮[7,7]，处理"c"，       c只出现了一次不满足，不继续递归
 * //第三轮[0,1]，处理"aa"，      满足出现次数>=2,ret=2
 * //第三轮[3,4]，处理"aaa"，     满足出现次数>=2 ret=3;
 */
public class Medium395 {
    public int longestSubstring(String s, int k) {
        if (s.length() == 0) return 0;
        return dfs(s, 0, s.length() - 1, k);
    }

    private int dfs(String s, int l, int r, int k) {
        if (l > r) return 0;
        int[] chars = new int[26];
        for (int i = l; i <= r; i++) {
            chars[s.charAt(i) - 'a']++;
        }
        List<Integer> tmp = new ArrayList<>();
        /*tmp记录小于k的位置，同时把首位添加进去，方便后面构成小区间*/
        tmp.add(l - 1);
        for (int i = l; i <= r; i++) {
            if (0 < chars[s.charAt(i) - 'a'] && chars[s.charAt(i) - 'a'] < k) tmp.add(i);
        }
        tmp.add(r + 1);
        int res = 0;
        if (tmp.size() == 2) return r - l + 1;
        for (int i = 0; i < tmp.size() - 1; i++) {
            res = Math.max(res, dfs(s, tmp.get(i) + 1, tmp.get(i + 1) - 1, k));
        }
        return res;
    }

    @Test
    public void test1() {
        System.out.println(longestSubstring("abcdedghijklmnopqrstuvwxyz", 2));
    }
}
