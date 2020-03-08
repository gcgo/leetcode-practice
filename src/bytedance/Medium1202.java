package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
 * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *  
 * 示例 1:
 * 输入：s = "dcab", pairs = [[0,3],[1,2]]
 * 输出："bacd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[1] 和 s[2], s = "bacd"
 *
 * 示例 2：
 * 输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * 输出："abcd"
 * 解释：
 * 交换 s[0] 和 s[3], s = "bcad"
 * 交换 s[0] 和 s[2], s = "acbd"
 * 交换 s[1] 和 s[2], s = "abcd"
 *
 * 示例 3：
 * 输入：s = "cba", pairs = [[0,1],[1,2]]
 * 输出："abc"
 * 解释：
 * 交换 s[0] 和 s[1], s = "bca"
 * 交换 s[1] 和 s[2], s = "bac"
 * 交换 s[0] 和 s[1], s = "abc"
 *  
 *
 * 提示：
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s 中只含有小写英文字母
 *
 * 思路：并查集
 */
public class Medium1202 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int N = s.length();
        UnionFind uf = new UnionFind(N);//新建并查集
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));//填充数据，自动合并，从union方法中可见是以前面的字符为上级
        }

        Map<Integer, List<Character>> graphs = new HashMap<>();//<帮派，成员列表>
        for (int i = 0; i < N; i++) {//遍历所有字符
            int head = uf.find(i);//查看当前字符关联的顶层字符
            //head有对应value则返回，没有则执行括号内方法，方法返回值会作为value添加到map中且返回
            List<Character> characters = graphs.computeIfAbsent(head, (dummy) -> new ArrayList<>());
            characters.add(s.charAt(i));//也就是字符s.charAt(i)是属于head这个帮派的
        }
        for (List<Character> characters : graphs.values()) {//遍历各个帮派的成员，按照字母表顺序排序
            //characters里的字符表示他们都可以通过若干次和head字符交换
            //所以将字母表顺序最靠前的换过来正是题目要求
            Collections.sort(characters);
        }
        StringBuilder sb = new StringBuilder(N);//准备输出结果
        /*目前为止，map里存有所有帮派，及其成员，成员还按大小个排好了队*/
        for (int i = 0; i < N; i++) {//遍历所有字符，找它所在的帮派的成员列表
            List<Character> characters = graphs.get(uf.find(i));
            char currentMin = characters.remove(0);//只要当前列表里个最小的那个，即字典序最靠前的那个
            sb.append(currentMin);//加入结果集
        }
        /*因为所有字符都存在帮派，可能三五成群，可能自成一派
        * 我们遍历所有字符，无论他帮派构成如何，每次我们都能取到当前帮派里最靠前的那个*/
        return sb.toString();
    }

    private class UnionFind {

        public int[] size;//帮派i的大小
        public int[] parent;//i的上级是谁

        UnionFind(int count) {
            size = new int[count];
            parent = new int[count];
            for (int i = 0; i < count; i++) {//初始化并查集，大家都还没有关系
                size[i] = 1;//所有组织都只有自己一人
                parent[i] = i;//所有人的上级都是自己
            }
        }

        /*查找p的顶层上司*/
        int find(int p) {
            while (p != parent[p]) {//如果当前p的上级不是自己，即还没找到顶层上司
                parent[p] = parent[parent[p]];//则让p的上级等于p上级的上级，往上查找
                p = parent[p];//p迭代到自己的上级，再看是不是自己
            }
            return p;//直到p的上级是自己，则找打了原始p的顶层上级
        }

        /*两个大侠狭路相逢，合并两个大侠所在的帮派，谁人少就归顺于人多的帮派门下*/
        int union(int p, int q) {
            int pRoot = find(p);//找大侠p的顶层上司
            int qRoot = find(q);//找大侠q的顶层上司
            if (pRoot == qRoot) {//如果上司一样，那就是同一帮派，返回帮派人数
                return size[pRoot];
            }
            if (size[pRoot] > size[qRoot]) {//p所在帮派人数多
                parent[qRoot] = pRoot;//则q的顶级上司直接归顺到p的顶级上司门下
                size[pRoot] += size[qRoot];//帮派q的人数也都算到帮派p里
                return size[pRoot];//从今以后就只认帮派p
            } else {//反之亦然
                parent[pRoot] = qRoot;
                size[qRoot] += size[pRoot];
                return size[qRoot];
            }

        }
    }

    @Test
    public void test1() {

    }
}
