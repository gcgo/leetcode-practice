package crackingcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 在老式手机上，用户通过数字键盘输入，手机将提供与这些数字相匹配的单词列表。
 * 每个数字映射到0至4个字母。给定一个数字序列，实现一个算法来返回匹配单词的列表。
 * 你会得到一张含有有效单词的列表。
 *
 * 示例 1:
 *
 * 输入: num = "8733", words = ["tree", "used"]
 * 输出: ["tree", "used"]
 * 示例 2:
 *
 * 输入: num = "2", words = ["a", "b", "c", "d"]
 * 输出: ["a", "b", "c"]
 * 提示：
 *
 * num.length <= 1000
 * words.length <= 500
 * words[i].length == num.length
 * num中不会出现 0, 1 这两个数字
 *
 * 思路：对26个字母映射到数字
 *
 */
public class Medium1620 {
	public List<String> getValidT9Words(String num, String[] words) {
		List<String> res = new ArrayList<>();
		if (num.length() == 0 || words.length == 0) return res;
		//初始化键盘对照表
		int[] char2num = new int[26];//a-z
		int k = 0, n = 2;
		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 3; j++) {
				char2num[k] = n;
				k++;
			}
			if (i == 6 || i == 8) {//遇到7和9，则再添加一个字母
				char2num[k] = n;
				k++;
			}
			n++;
		}
		//考察每一个单词
		for (String word : words) {
			if (word.length() != num.length()) continue;
			int i;
			for (i = 0; i < word.length(); i++) {
				if (char2num[word.charAt(i) - 'a'] != num.charAt(i) - '0') break;
			}
			/*说明匹配成功，添加结果*/
			if (i == word.length()) res.add(word);
		}
		return res;
	}

	@Test
	public void test1() {
		List<String> res = getValidT9Words("2", new String[]{"a", "b", "c", "d"});
		System.out.println(res);
	}
}
