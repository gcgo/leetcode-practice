package javaoffer;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 珠玑妙算游戏（the game of master mind）的玩法如下。
 *
 * 计算机有4个槽，每个槽放一个球，颜色可能是红色（R）、黄色（Y）、绿色（G）或蓝色（B）。
 * 例如，计算机可能有RGGB 4种（槽1为红色，槽2、3为绿色，槽4为蓝色）。
 * 作为用户，你试图猜出颜色组合。打个比方，你可能会猜YRGB。
 * 要是猜对某个槽的颜色，则算一次“猜中”；要是只猜对颜色但槽位猜错了，则算一次“伪猜中”。注意，“猜中”不能算入“伪猜中”。
 *
 * 给定一种颜色组合solution和一个猜测guess，编写一个方法，返回猜中和伪猜中的次数answer，
 * 其中answer[0]为猜中的次数，answer[1]为伪猜中的次数。
 *
 * 示例：
 *
 * 输入： solution="RGBY",guess="GGRR"
 * 输出： [1,1]
 * 解释： 猜中1次，伪猜中1次。
 * 提示：
 *
 * len(solution) = len(guess) = 4
 * solution和guess仅包含"R","G","B","Y"这4种字符
 *
 * 思路：伪命中=可能命中的总数-实际命中数
 * 可能命中数就是取solution和guess里出现次数最少的颜色数量之和，
 * 比如solution中有2个红色，guess只有一个红色，所以只可能命中一个。取小的。
 */
public class Hard1615 {
	public int[] masterMind(String solution, String guess) {
		int allPossible = 0, hit = 0;
		char[] colors = new char[]{'R', 'Y', 'G', 'B'};
		Map<Character, Integer> color_solution = new HashMap<>();
		Map<Character, Integer> color_guess = new HashMap<>();

		for (int i = 0; i < 4; i++) {
			char s = solution.charAt(i);
			char g = guess.charAt(i);
			if (s == g) hit++;
			color_solution.put(s, color_solution.getOrDefault(s, 0) + 1);
			color_guess.put(g, color_guess.getOrDefault(g, 0) + 1);
		}
		for (char color : colors) {
			allPossible += Math.min(color_solution.getOrDefault(color, 0)
					, color_guess.getOrDefault(color, 0));
		}
		return new int[]{hit, allPossible - hit};
	}

	@Test
	public void test1() {
		Arrays.stream(masterMind("BGBG", "RGBR")).forEach(System.out::println);//2，0
		Arrays.stream(masterMind("GGBB", "RBYB")).forEach(System.out::println);//1，1
	}
}
