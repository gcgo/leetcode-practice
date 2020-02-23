package bytedance;

import org.junit.Test;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 */
public class Medium208 {
    class Trie {
        class TrieNode {
            public boolean isWord;//是否在此结束
            public TrieNode[] children = new TrieNode[26];//对应26个字母

            public TrieNode() {
            }
        }

        private TrieNode root;//属性，根节点

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            this.root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TrieNode ws = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (ws.children[c - 'a'] == null) {//若字典树之前没有存过，则新建一个节点。
                    ws.children[c - 'a'] = new TrieNode();
                }
                ws = ws.children[c - 'a'];//向前走
            }
            ws.isWord = true;//所有字母遍历完了，标记这是一个单词。
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode ws = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (ws.children[c - 'a'] == null) return false;//如果过程中有一个字母没有，则返回否
                ws = ws.children[c - 'a'];//有的话就继续深入
            }
            return ws.isWord;//来到最后一个字母，返回他的标记。因为这条分支可能是更长的单词的前缀。
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode ws = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (ws.children[c - 'a'] == null) return false;
                ws = ws.children[c - 'a'];
            }
            return true;//和search的区别是能遍历完就返回true
        }
    }

    //**************************************************************************
    @Test
    public void test1() {

    }

}
