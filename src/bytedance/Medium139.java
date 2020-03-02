package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * <p>
 * 说明：
 * <p>
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 * <p>
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 * <p>
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 * <p>
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 * <p>
 * <p>
 * 思路：BFS构建图，节点就是以i为开头的字符
 * 想象构建邻接矩阵，第一个字符过来
 */
public class Medium139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.contains(s)) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        //使用一个Set记录确认过的下标，剪枝
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int curIdx = queue.poll();//固定一个开头
            for (int i = curIdx + 1; i <= s.length(); i++) {
                //为什么这里可以剪枝，因为我们目的是向后找，所以遇到同样的结尾的话，对于最终目的没有推进，所以不用看了
                if (visited.contains(i)) continue;
                if (wordDict.contains(s.substring(curIdx, i))) {//若字典里有这个开头和结尾的子串，则把这个结尾也给存进队里
                    if (i == s.length()) return true;
                    queue.offer(i);
                    visited.add(i);
                }
            }
        }
        return false;
    }

    @Test
    public void test1() {
        List<String> words = new ArrayList<>();
        words.add("cats");
        words.add("dog");
        words.add("sand");
        words.add("and");
        words.add("cat");

        System.out.println(wordBreak("catsandog",words));
    }

}
