package javaoffer;

import org.junit.Test;

import java.util.*;

/**
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * 注意：本题相对原题稍作修改
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 思路：变位词，排序后肯定都是一个单词，所以用一个map来记录变位词种类
 * 用到一个技巧，这里map存的是该类变位词在res列表中的位置，
 * 如果map里有该变位词，直接获取res对应位置添加该词
 * 如果没有，则新建一个key，同时res里新建一个list。
 */
public class Medium1002 {
	public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> res = new ArrayList<>();
		/*Map存的是这一类字符串对应res里的编号*/
		Map<String, Integer> map = new HashMap<>();
		for (String str : strs) {
			char[] chars = str.toCharArray();
			Arrays.sort(chars);
			String key = Arrays.toString(chars);
			if (!map.containsKey(key)) {
				map.put(key, map.size());
				res.add(new ArrayList<>());
			}
			res.get(map.get(key)).add(str);
		}
		return res;
	}

	@Test
	public void test1() {
		String[] s = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagrams(s));
	}
}
