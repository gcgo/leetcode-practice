package crackingcode;

import org.junit.Test;

/**
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子"I reset the computer. It still didn’t boot!"已经变成了"iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典dictionary，不过，有些词没在词典里。
 * 假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 注意：本题相对原题稍作改动，只需返回未识别的字符数
 *
 * 示例：
 * 输入：
 * dictionary = ["looked","just","like","her","brother"]
 * sentence = "jesslookedjustliketimherbrother"
 * 输出： 7
 * 解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
 * 提示：
 *
 * 0 <= len(sentence) <= 1000
 * dictionary中总字符数不超过 150000。
 * 你可以认为dictionary和sentence中只包含小写字母。
 *
 * 思路：动态规划+字典树
 *
 */
public class Medium1713 {

    private class Node {
        Node[] childs = new Node[26];
        boolean isLeaf = false;
    }

    private Node root;

    public int respace(String[] dictionary, String sentence) {
        root = new Node();
        for (String word : dictionary) {
            addWord(word);
        }
        int n = sentence.length();
        //dp[i]表示以i开头的字符串没有匹配的最小长度，所以从后向前考察
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            Node node = root;
            dp[i] = n - i;
            //j每次都从i开始一直考察到结尾
            for (int j = i; j < n; j++) {
                char c = sentence.charAt(j);
                if (node.childs[c - 'a'] == null) {
                    dp[i] = Math.min(dp[i], j - i + 1 + dp[j + 1]);
                    break;
                }
                //不为空就继续找
                node = node.childs[c - 'a'];
                //是叶子节点那么后面的就是不匹配的，即dp[j+1],不是叶子节点先按照没匹配统计，反正最后取的是最小值
                dp[i] = Math.min(dp[i], node.isLeaf ? dp[j + 1] : j - i + 1 + dp[j + 1]);
            }
        }
        return dp[0];
    }

    private void addWord(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            if (node.childs[c - 'a'] == null) {
                node.childs[c - 'a'] = new Node();
            }
            node = node.childs[c - 'a'];
        }
        node.isLeaf = true;
    }

    //*********************************************************************************
    @Test
    public void test1() {
        int res = respace(new String[]{"looked", "just", "like", "her", "brother"},
                "jesslookedjustliketimherbrother");
        System.out.println(res);
    }
}
