package bytedance;

import org.junit.Test;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 */
public class Easy14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
		for (int i = 0; i < strs[0].length(); i++) {//拿第一个字符串的每一位与其他进行比较
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				//如果第一个字符串长度超过了后面的某一个 或者 后面的字符串第i位不是c，则证明前缀不匹配了，
				// 返回strs[0].substring(0,i)。substring是前闭后开[0,i)
				if (i==strs[j].length()||strs[j].charAt(i)!=c){
					return strs[0].substring(0,i);
				}
			}
		}
		return strs[0];//否则证明第一个字符串就是最小公共前缀
    }

    @Test
    public void test1() {
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}
