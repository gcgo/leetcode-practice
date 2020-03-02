package bytedance;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

/**
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。
 * <p>
 * 原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * <p>
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * <p>
 * 两个化学式连在一起是新的化学式。例如 H2O2He3Mg4 也是化学式。
 * <p>
 * 一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * <p>
 * 给定一个化学式，输出所有原子的数量。格式为：第一个（按字典序）原子的名子，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * formula = "H2O"
 * 输出: "H2O"
 * 解释:
 * 原子的数量是 {'H': 2, 'O': 1}。
 * 示例 2:
 * <p>
 * 输入:
 * formula = "Mg(OH)2"
 * 输出: "H2MgO2"
 * 解释:
 * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
 * 示例 3:
 * <p>
 * 输入:
 * formula = "K4(ON(SO3)2)2"
 * 输出: "K4N2O14S4"
 * 解释:
 * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
 * 注意:
 * <p>
 * 所有原子的第一个字母为大写，剩余字母都是小写。
 * formula的长度在[1, 1000]之间。
 * formula只包含字母、数字和圆括号，并且题目中给定的是合法的化学式。
 * <p>
 * 思路：大写字符后面可以跟：
 * 1》数字
 * 2》小写字符
 * 3》括号，括号又分开始和结束
 */
public class Hard726 {

    public String countOfAtoms(String formula) {
        //TreeMap是按照Key的自然顺序或者Comprator的顺序进行排序。
        // 在实现原理上LinkedHashMap是双向链表，TreeMap是红黑树。
        // TreeMap还有个好兄弟叫TreeSet，实现原理是一样的。
        TreeMap<String, Integer> map = new TreeMap<>();
        Deque<TreeMap> stack = new ArrayDeque<>();//模拟递归栈

        int i = 0, len = formula.length();
        while (i < len) {
            if (formula.charAt(i) == '(') {//如果是左括号，就要开始新一轮计数，倍数
                stack.push(map);
                map = new TreeMap<>();
                i++;
            } else if (formula.charAt(i) == ')') {
                int val = 0;//记录倍数
                i++;
                while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                    val = val * 10 + formula.charAt(i++) - '0';//这个式子比较牛逼
                }
                val = val == 0 ? 1 : val;

                if (!stack.isEmpty()) {
                    TreeMap<String, Integer> temp = map;//此时map指向记录原子出现次数的集合
                    map = stack.pop();//栈中集合代表递归上一层暂存的状态，而temp是现在新记录的，所以需要合并

                    for (String atom : temp.keySet()) {
                        map.put(atom, temp.get(atom) * val + map.getOrDefault(atom, 0));
                    }
                }
            } else {//不是（），即一般情况，大写字母
                int j = i + 1;
                while (j < formula.length() && Character.isLowerCase(formula.charAt(j))) {
                    j++;
                }

                String atom = formula.substring(i, j);//提取元素名字

                int val = 0;//倍数，出现在元素名字结束后
                while (j < formula.length() && Character.isDigit(formula.charAt(j))) {
                    val = val * 10 + (formula.charAt(j++) - '0');
                }
                val = val == 0 ? 1 : val;
                map.put(atom, map.getOrDefault(atom, 0) + val);
                i = j;
            }
        }
        //遍历完所有元素数量都应该存储在map里
        StringBuilder sb = new StringBuilder();

        for (String atom : map.keySet()) {
            sb.append(atom);
            sb.append(map.get(atom) == 1 ? "" : map.get(atom));
        }
        return sb.toString();
    }

    //*********************************************************************************
    @Test
    public void test1() {
        System.out.println(countOfAtoms("K4(ON(SO3)2)2"));
    }
}
