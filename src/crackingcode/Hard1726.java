package crackingcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。
 * 例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。
 * 给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。
 * 它们的相似度非常“稀疏”，也就是说任选 2 个文档，相似度都很接近 0。
 * 请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。
 * 请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。
 *
 * 输入为一个二维数组 docs，docs[i] 表示 id 为 i 的文档。
 * 返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，
 * 其格式为 {id1},{id2}: {similarity}，其中 id1 为两个文档中较小的 id，similarity 为相似度，
 * 精确到小数点后 4 位。以任意顺序返回数组均可。
 *
 * 示例:
 * 输入:
 * [
 *   [14, 15, 100, 9, 3],
 *   [32, 1, 9, 3, 5],
 *   [15, 29, 2, 6, 8, 7],
 *   [7, 10]
 * ]
 * 输出:
 * [
 *   "0,1: 0.2500",
 *   "0,2: 0.1000",
 *   "2,3: 0.1429"
 * ]
 * 提示：
 * docs.length <= 500
 * docs[i].length <= 500
 * 相似度大于 0 的文档对数不会超过 1000
 *
 * 思路：思维转变一下，不是把两个数组比较，看有没有重复的。而是每次的数字出现，都去检查一下是否出现过，在哪个数组里出现的
 * 遍历docs[][]，将数字【在哪个数组中出现的】保存在map中，map的key就是遍历到的数字，value是一个由数组index组成的list
 * 用一个help矩阵来存储数组与数组之间有多少交集
 * 最后遍历help数组，按指定格式输出
 */
public class Hard1726 {
	public List<String> computeSimilarities(int[][] docs) {
		List<String> ans = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		int[][] help = new int[docs.length][docs.length];
		for (int i = 0; i < docs.length; i++) {
			for (int j = 0; j < docs[i].length; j++) {
				List<Integer> list = map.get(docs[i][j]);
				if (list == null) {
					list = new ArrayList<>();
					map.put(docs[i][j], list);
				} else {
					for (Integer k : list) {
						help[i][k]++;//数组i和数组k的交集数++
					}
				}
				list.add(i);
			}
			for (int k = 0; k < docs.length; k++) {
				if (help[i][k] > 0) {
					ans.add(k + "," + i + ": " +
							String.format("%.4f",
									(double) help[i][k] / (docs[i].length + docs[k].length - help[i][k])));
				}
			}
		}
		return ans;
	}

	@Test
	public void test1() {

	}
}
