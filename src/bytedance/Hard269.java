package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 现有一种使用字母的全新语言，这门语言的字母顺序与英语顺序不同。
 *
 * 假设，您并不知道其中字母之间的先后顺序。
 * 但是，会收到词典中获得一个 不为空的 单词列表。
 * 因为是从词典中获得的，所以该单词列表内的单词已经 按这门新语言的字母顺序进行了排序。
 *
 * 您需要根据这个输入的列表，还原出此语言中已知的字母顺序。
 *
 * 示例 1：
 *
 * 输入:
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 *
 * 输出: "wertf"
 * 示例 2：
 *
 * 输入:
 * [
 *   "z",
 *   "x"
 * ]
 *
 * 输出: "zx"
 * 示例 3：
 *
 * 输入:
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 *
 * 输出: "" 
 *
 * 解释: 此顺序是非法的，因此返回 ""。
 * 注意：
 *
 * 你可以默认输入的全部都是小写字母
 * 假如，a 的字母排列顺序优先于 b，那么在给定的词典当中 a 定先出现在 b 前面
 * 若给定的顺序是不合法的，则返回空字符串即可
 * 若存在多种可能的合法字母顺序，请返回其中任意一种顺序即可
 *
 * 思路：传统字符串比较顺序是，一个字母一个字母比较，相同则看下一个字母
 * 这道题分两步，根据输入构建有向图，对有向图拓扑排序
 */
public class Hard269 {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return null;
        if (words.length == 1) return words[0];
        //邻接表表示图
        Map<Character, List<Character>> adjList = new HashMap<>();
        //构建邻接表
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            int l1 = word1.length();
            int l2 = word2.length();
            //表示找到了顺序,即两个字符不相同
            boolean found = false;

            for (int j = 0; j < Math.max(l1, l2); j++) {
                Character c1 = j < l1 ? word1.charAt(j) : null;
                Character c2 = j < l2 ? word2.charAt(j) : null;
                if (c1 != null && !adjList.containsKey(c1)) {
                    adjList.put(c1, new ArrayList<>());
                }
                if (c2 != null && !adjList.containsKey(c2)) {
                    adjList.put(c2, new ArrayList<>());
                }
                if (c1 != null && c2 != null && c1 != c2 && !found) {
                    adjList.get(c1).add(c2);
                    found = true;
                }
            }
        }
        /*拓扑排序*/
        Set<Character> isVisited = new HashSet<>();
        Set<Character> loop = new HashSet<>();//检查是否有环
        Deque<Character> stack = new ArrayDeque<>();

        for (Character key : adjList.keySet()) {
            if (!isVisited.contains(key)) {
                if (!topoSort(adjList, key, isVisited, loop, stack)) {
                    return "";
                }
            }
        }
        /*输出结果*/
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /*拓扑排序实现*/
    private boolean topoSort(Map<Character, List<Character>> adjList,
                             Character key, Set<Character> isVisited,
                             Set<Character> loop, Deque<Character> stack) {
        isVisited.add(key);
        loop.add(key);

        if (adjList.containsKey(key)) {
            for (int i = 0; i < adjList.get(key).size(); i++) {
                Character v = adjList.get(key).get(i);

                //若有环路，则表明字典乱序，返回false
                if (loop.contains(v)) return false;

                //如果没访问过，就dfs它的邻居
                if (!isVisited.contains(v)) {
                    if (!topoSort(adjList, v, isVisited, loop, stack)) {
                        return false;
                    }
                }
            }
        }
        //即dfs完key，添加到栈中
        loop.remove(key);
        stack.push(key);
        return true;
    }

    @Test
    public void test1() {
        String[] s = new String[]{
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };
        System.out.println(alienOrder(s));//wertf
    }

}
