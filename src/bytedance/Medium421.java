package bytedance;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 2^31 。
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 * 你能在O(n)的时间解决这个问题吗？
 *
 * 示例:
 * 输入: [3, 10, 5, 25, 2, 8]
 * 输出: 28
 *
 * 解释: 最大的结果是 5 ^ 25 = 28.
 * <p>
 * 思路：前缀树
 */
public class Medium421 {
    class Trie {
        Trie[] children;

        public Trie() {
            children = new Trie[2];//0和1
        }
    }

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 初始化前缀树
        Trie root = new Trie();
        for (int num : nums) {
            Trie curNode = root;
            for (int i = 31; i >= 0; i--) {
                int curBit = (num >>> i) & 1;//从高位开始存入前缀树
                if (curNode.children[curBit] == null) {
                    curNode.children[curBit] = new Trie();
                }
                curNode = curNode.children[curBit];
            }
        }
        /*逐个取出元素
         * 按位从高到低在前缀树中找能和自己异或等于1的，因为1越多数越大*/
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            Trie curNode = root;
            int curSum = 0;
            for (int i = 31; i >= 0; i--) {
                int curBit = (num >>> i) & 1;
                if (curNode.children[curBit ^ 1] != null) {//异或交换律，curBit ^ （curBit ^ 1）=1
                    curSum += (1 << i);//当前和累加
                    curNode = curNode.children[curBit ^ 1];
                } else {//若没有则该位异或结果只能是0了，看下一位
                    curNode = curNode.children[curBit];
                }
            }
            max = Math.max(curSum, max);//更新每一次计算的最大值
        }
        return max;
    }

    //******************************前缀树模板********************************
    class Trie1 {
        class TrieNode {
            public boolean isWord;
            public Map<Character, TrieNode> childrenMap = new HashMap<>();//map实现，也可以数组实现
        }

        private TrieNode root;

        /** 初始化 */
        public Trie1() {
            root = new TrieNode();
        }

        /** 插入一个单词 */
        public void insert(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.childrenMap.get(c) == null) {
                    // 如果没有该字符，就插入一个新字符分支
                    cur.childrenMap.put(c, new TrieNode());
                }
                cur = cur.childrenMap.get(c);
            }
            cur.isWord = true;
        }

        /** 返回单词是否在前缀树里 */
        public boolean search(String word) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.childrenMap.get(c) == null) {
                    return false;
                }
                cur = cur.childrenMap.get(c);
            }
            return cur.isWord;
        }

        /** 返回所给单词是否是前缀 */
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (cur.childrenMap.get(c) == null) {
                    return false;
                }
                cur = cur.childrenMap.get(c);
            }
            return true;
        }
    }

    @Test
    public void test1() {

    }
}
