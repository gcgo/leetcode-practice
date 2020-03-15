package bytedance;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 思路：双指针作为窗口，固定左边界i,右边界j不断扩张考察，用一个集合set来记录窗口内的字符集。
 * j每走一步，判断下当前字符在不在set里，若不在则添加该字符，更新最大长度；
 * 若在，则需要更新左边界i了，为了让当前的j能够算入字符集，我们需要从i开始不断从set中删除字符，每删除一个i对应的字符
 * 判断下set中还有没有j,若还有则继续删。直到set中可以添加j了，则添加进去，并更新一下长度。
 * 这样保证ij这个窗口是不断平移扫过所有元素的，并且只需要一次遍历，时间复杂度O(n)
 */
public class Medium3 {

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            while (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
                i++;
            }
            set.add(s.charAt(j));
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    /*优化算法，更新i时，不用从i一步一步挪过来了*/
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        //map存的是某字符上一次出现的位置
        Map<Character, Integer> map = new HashMap<>();
        //i为左区间，j为右区间，右边界移动
        for (int j = 0, i = 0; j < n; j++) {
            // 如果map中包含当前字符
            if (map.containsKey(s.charAt(j))) {
                //更新左边界，左边界应该移动到“map中记录的该元素上一次出现的位置+1”和“当前i”更大的那个位置
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            //更新当前字符位置与结果
            map.put(s.charAt(j), j);
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    @Test
    public void test1() {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
    }
}
