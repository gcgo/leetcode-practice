package crackingcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 给定一个布尔表达式和一个期望的布尔结果 result，布尔表达式由 0 (false)、1 (true)、& (AND)、 | (OR) 和 ^ (XOR) 符号组成。
 * 实现一个函数，算出有几种可使该表达式得出 result 值的括号方法。
 *
 * 示例 1:
 * 输入: s = "1^0|0|1", result = 0
 * 输出: 2
 * 解释: 两种可能的括号方法是
 * 1^(0|(0|1))
 * 1^((0|0)|1)
 *
 * 示例 2:
 * 输入: s = "0&0&0&1^1|0", result = 1
 * 输出: 10
 * 提示：
 * 运算符的数量不超过 19 个
 *
 * 思路：一般这种有几种方法、几种组合，都可以用动态规划考虑
 * 比如0&0&0&1^1|0=1，我们若以最中间分隔(0&0&0)&(1^1|0)=1，则问题变成考虑左右括号里结果为1的组合数，很明显是递归。
 * 网友实现：牛逼
 */
public class Medium0814 {
	public int countEval(String s, int result) {
		/*map存的是一段01运算后结果为0或是为1的组合数*/
		/*List结构是（一段01，结果是1还是0）*/
		HashMap<List, Integer> map = new HashMap<>();//这条语句在方法内就超时。。。。。。。。。
		int len = s.length();
		/*递归终止条件，若就剩1个字符，就看它和结果等不等了，等就1种，不能就0种*/
		if (len == 1) return result == Integer.parseInt(s) ? 1 : 0;
		/*正片开始*/
		int res = 0;
		for (int i = 0; i < len; ++i) {
			//遇到'0'或'1'，跳过
			if (s.charAt(i) == '0' || s.charAt(i) == '1') continue;
			//分割成左半部分 和 右半部分
			String left = s.substring(0, i), right = s.substring(i + 1);
			/*Arrays.asList(left, 0)，表示left计算结果为0*/
			if (!map.containsKey(Arrays.asList(left, 0)))
				map.put(Arrays.asList(left, 0), countEval(left, 0));
			if (!map.containsKey(Arrays.asList(left, 1)))
				map.put(Arrays.asList(left, 1), countEval(left, 1));
			if (!map.containsKey(Arrays.asList(right, 0)))
				map.put(Arrays.asList(right, 0), countEval(right, 0));
			if (!map.containsKey(Arrays.asList(right, 1)))
				map.put(Arrays.asList(right, 1), countEval(right, 1));
			/*计算以i为最后一步结果为result的组合数*/
			int left0 = map.get(Arrays.asList(left, 0)), left1 = map.get(Arrays.asList(left, 1));
			int right0 = map.get(Arrays.asList(right, 0)), right1 = map.get(Arrays.asList(right, 1));
			switch (s.charAt(i)) {
				case '&'://如果最后一步是与运算，按照result是否等于0，分两种情况讨论
					res += result == 0 ? left0 * right0 + left0 * right1 + left1 * right0 : left1 * right1;
					break;
				case '|':
					res += result == 0 ? left0 * right0 : left1 * right0 + left0 * right1 + left1 * right1;
					break;
				default:
					res += result == 0 ? left0 * right0 + left1 * right1 : left0 * right1 + left1 * right0;
			}
		}
		return res;
	}

	@Test
	public void test1() {

	}
}
