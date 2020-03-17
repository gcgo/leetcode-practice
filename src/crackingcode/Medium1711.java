package crackingcode;

import org.junit.Test;

/**
 * 有个内含单词的超大文本文件，给定任意两个单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 *
 * 示例：
 *
 * 输入：words = ["I","am","a","student","from","a","university","in","a","city"], word1 = "a", word2 = "student"
 * 输出：1
 * 提示：
 *
 * words.length <= 100000
 *
 *
 * 思路：双指针，遍历一遍，更新最小距离
 *
 */
public class Medium1711 {

    public int findClosest(String[] words, String word1, String word2) {
        int idx1 = -1;
        int idx2 = -1;
        int res = Integer.MAX_VALUE;
        String prev = null;
        for (int i = 0; i < words.length; i++) {
            if (word1.equals(words[i])) {
                idx1 = i;
                if (idx2 != -1 && word2.equals(prev)) {
                    res = Math.min(res, Math.abs(idx1 - idx2));
                }
                prev = word1;
            } else if (word2.equals(words[i])) {
                idx2 = i;
                if (idx1 != -1 && word1.equals(prev)) {
                    res = Math.min(res, Math.abs(idx1 - idx2));
                }
                prev = word2;
            }
        }
        return res;
    }

    //*********************************************************************************
    @Test
    public void test1() {

    }
}
