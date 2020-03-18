package bytedance;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 示例 1:
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 * 示例 2:
 * 输入:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 * 输出: 0
 *
 * 解释: endWord "cog" 不在字典中，所以无法进行转换。
 *
 * 思路：我们将问题抽象在一个无向无权图中，每个单词作为节点，差距只有一个字母的两个单词之间连一条边。
 * 问题变成找到从起点到终点的最短路径，如果存在的话。因此可以使用广度优先搜索方法。
 *
 * beginWord入队，出队，将beginWord能够转换的所有Word入队，如果Word不等于endWord且没有被访问过，则标记它已访问，然后入队
 */
public class Medium127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        boolean[] visited = new boolean[wordList.size()];
        //如果beginWord在list里就先标记它访问过
        int idx = wordList.indexOf(beginWord);
        if (idx != -1) {
            visited[idx] = true;
        }
        //beginWord开始入队
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int count = 0;
        int currentLevel = 1;
        int nextLevel = 0;
        while (queue.size() > 0) {
            count++;
            while (currentLevel > 0) {
                String start = queue.poll();
                currentLevel--;
                for (int i = 0; i < wordList.size(); i++) {
                    // 通过index判断是否已经访问
                    if (visited[i]) continue;
                    String s = wordList.get(i);
                    //判断beginWord能不能转换为s，不能就下一个
                    if (!canConvert(start, s)) continue;
                    //如果找到了就直接返回
                    if (s.equals(endWord)) {
                        return count + 1;
                    }
                    visited[i] = true;
                    queue.offer(s);
                    nextLevel++;
                }
            }
            currentLevel = nextLevel;
            nextLevel = 0;
        }
        return 0;
    }

    public boolean canConvert(String s1, String s2) {
        // 因为题目说了单词长度相同，可以不考虑长度问题
        // if (s1.length() != s2.length()) return false;
        int count = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ++count;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }

    @Test
    public void test1() {

    }
}
