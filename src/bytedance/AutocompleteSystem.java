package bytedance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * leetcode hard 642
 * 为搜索引擎设计一个搜索自动补全系统。
 * 用户会输入一条语句（最少包含一个字母，以特殊字符 '#' 结尾）。
 * 除 '#' 以外用户输入的每个字符，返回历史中热度前三并以当前输入部分为前缀的句子。
 * 下面是详细规则：
 * <p>
 * 一条句子的热度定义为历史上用户输入这个句子的总次数。
 * 返回前三的句子需要按照热度从高到低排序（第一个是最热门的）。
 * 如果有多条热度相同的句子，请按照 ASCII 码的顺序输出（ASCII 码越小排名越前）。
 * 如果满足条件的句子个数少于 3，将它们全部输出。
 * 如果输入了特殊字符，意味着句子结束了，请返回一个空集合。
 * 你的工作是实现以下功能：
 * <p>
 * 构造函数：
 * <p>
 * AutocompleteSystem(String[] sentences, int[] times): 这是构造函数，输入的是历史数据。 
 * Sentences 是之前输入过的所有句子，Times 是每条句子输入的次数，你的系统需要记录这些历史信息。
 * <p>
 * 现在，用户输入一条新的句子，下面的函数会提供用户输入的下一个字符：
 * <p>
 * List<String> input(char c): 其中 c 是用户输入的下一个字符。
 * 字符只会是小写英文字母（'a' 到 'z' ），空格（' '）和特殊字符（'#'）。
 * 输出历史热度前三的具有相同前缀的句子。
 * <p>
 *  
 * <p>
 * 样例 ：
 * 操作 ： AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
 * 系统记录下所有的句子和出现的次数：
 * "i love you" : 5 次
 * "island" : 3 次
 * "ironman" : 2 次
 * "i love leetcode" : 2 次
 * 现在，用户开始新的键入：
 * <p>
 * <p>
 * 输入 ： input('i')
 * 输出 ： ["i love you", "island","i love leetcode"]
 * 解释 ：
 * 有四个句子含有前缀 "i"。
 * 其中 "ironman" 和 "i love leetcode" 有相同的热度，
 * 由于 ' ' 的 ASCII 码是 32 而 'r' 的 ASCII 码是 114，所以 "i love leetcode" 在 "ironman" 前面。
 * 同时我们只输出前三的句子，所以 "ironman" 被舍弃。
 * <p>
 * 思路：
 * Trie单词查找树
 */
public class AutocompleteSystem {

    class Node {
        Node(String st, int t) {
            sentence = st;
            times = t;
        }

        String sentence;
        int times;
    }

    class Trie {
        int times;
        Trie[] branches = new Trie[27];
    }

    public int int_(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    public void insert(Trie t, String s, int times) {
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[int_(s.charAt(i))] == null)
                t.branches[int_(s.charAt(i))] = new Trie();
            t = t.branches[int_(s.charAt(i))];//往下一层走
        }
        t.times += times;//叶子节点处time+1
    }

    public List<Node> lookup(Trie t, String s) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[int_(s.charAt(i))] == null)
                return new ArrayList<Node>();
            t = t.branches[int_(s.charAt(i))];//走到字符对应节点
        }
        traverse(s, t, list);
        return list;
    }

    public void traverse(String s, Trie t, List<Node> list) {//从s的最后一个字母作为开头，记录所有分支
        if (t.times > 0)//非叶子节点的time都为0
            list.add(new Node(s, t.times));//s到最后一层了，也写完了，同时叶子节点还保存了times,将其添加到list中返回。
        for (char i = 'a'; i <= 'z'; i++) {//从a开始找，把所有分支都找到，
            if (t.branches[i - 'a'] != null)
                traverse(s + i, t.branches[i - 'a'], list);//递归调用，就是先序遍历，s每一层都添加一个字母
        }
        if (t.branches[26] != null)
            traverse(s + ' ', t.branches[26], list);
    }

    Trie root;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }

    String cur_sent = "";

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {//结尾标识符
            insert(root, cur_sent, 1);
            cur_sent = "";
        } else {
            cur_sent += c;
            List<Node> list = lookup(root, cur_sent);//返回所有c开头的字典树中的单词分支
			//对返回的c开头的所有短语按照times排序：
            Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            for (int i = 0; i < Math.min(3, list.size()); i++)//返回前三个
                res.add(list.get(i).sentence);
        }
        return res;
    }


	public static void main(String[] args) {
		AutocompleteSystem as = new AutocompleteSystem(new String[]{"i love you", "island", "ironman", "i love leetcode"}
				,new int[]{5,3,2,2} );

		List<String> res= as.input('i');
		System.out.println(res.toString());
	}
}
