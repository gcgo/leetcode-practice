package javaoffer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 一只蚂蚁坐在由白色和黑色方格构成的无限网格上。开始时，网格全白，蚂蚁面向右侧。蚂蚁执行以下操作。
 *
 * (1) 如果在白色方格上，则翻转方格的颜色，向右(顺时针)转 90 度，并向前移动一个单位。
 * (2) 如果在黑色方格上，则翻转方格的颜色，向左(逆时针方向)转 90 度，并向前移动一个单位。
 *
 * 编写程序来模拟蚂蚁执行的前 K 个动作，并返回最终的网格。
 *
 * 网格由数组表示，每个元素是一个字符串，代表网格中的一行，
 * 黑色方格由 'X' 表示，白色方格由 '_' 表示，蚂蚁所在的位置由 'L', 'U', 'R', 'D' 表示，
 * 分别表示蚂蚁 左、上、右、下 的朝向。只需要返回能够包含蚂蚁走过的所有方格的最小矩形。
 *
 * 示例 1:
 * 输入: 0
 * 输出: ["R"]
 * 示例 2:
 * 输入: 2
 * 输出:
 * [
 *   "_X",
 *   "LX"
 * ]
 * 示例 3:
 * 输入: 5
 * 输出:
 * [
 *   "_U",
 *   "X_",
 *   "XX"
 * ]
 * 说明：
 * K <= 100000
 *
 * 思路：K是蚂蚁走的步数。
 *
 */
public class Medium1622 {
	public List<String> printKMoves(int K) {
		Map<String, Character> map = new HashMap<>();
		//游标
		int i = 0, j = 0;
		//记录边界位置
		int left = 0, right = 0, bottom = 0, top = 0;

		StringBuilder sb = new StringBuilder();
		sb.append(i).append(',').append(j);
		char direction = 'R';
		map.put(sb.toString(), direction);
		sb.delete(0, sb.length());
		for (int k = 0; k < K; k++) {
			sb.append(i).append(',').append(j);
			char status = map.computeIfAbsent(sb.toString(), c -> '_');
			if (status != '_' && status != 'X') {
				direction = status;
				status = '_';
			}
			/*如果当前为白色格子*/
			if (status == '_') {
				map.put(sb.toString(), 'X');
				if (direction == 'R') {
					i = i - 1;
					direction = 'D';
				} else if (direction == 'D') {
					j = j - 1;
					direction = 'L';
				} else if (direction == 'L') {
					i = i + 1;
					direction = 'U';
				} else if (direction == 'U') {
					j = j + 1;
					direction = 'R';
				}
			}
			/*如果当前为黑色格子*/
			if (status == 'X') {
				map.put(sb.toString(), '_');
				if (direction == 'R') {
					i = i + 1;
					direction = 'U';
				} else if (direction == 'U') {
					j = j - 1;
					direction = 'L';
				} else if (direction == 'L') {
					i = i - 1;
					direction = 'D';
				} else if (direction == 'D') {
					j = j + 1;
					direction = 'R';
				}
			}
			//更新一下边界
			left = Math.min(left, j);
			bottom = Math.min(bottom, i);
			right = Math.max(right, j);
			top = Math.max(top, i);

			sb.delete(0, sb.length());
			if (k == K - 1) {
				sb.append(i).append(',').append(j);
				map.put(sb.toString(), direction);
				sb.delete(0, sb.length());
			}

		}
		/*开始组合结果*/
		sb = new StringBuilder();
		StringBuilder tmp = new StringBuilder();
		List<String> res = new ArrayList<>(top - bottom + 1);
		for (int k = top; k >= bottom; k--) {
			for (int l = left; l <= right; l++) {
				sb.append(k).append(',').append(l);
				char r = map.getOrDefault(sb.toString(), '_');
				tmp.append(r);
				sb.delete(0, sb.length());
			}
			res.add(tmp.toString());
			tmp.delete(0, tmp.length());
		}
		return res;
	}

	@Test
	public void test1() {
		System.out.println(printKMoves(4));
	}
}
