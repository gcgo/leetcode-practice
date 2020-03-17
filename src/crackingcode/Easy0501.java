package crackingcode;

import org.junit.Test;

/**
 *插入。给定两个32位的整数N与M，以及表示比特位置的i与j。
 * 编写一种方法，将M插入N，使得M从N的第j位开始，到第i位结束。
 * 假定从j位到i位足以容纳M，也即若M = 10 011，那么j和i之间至少可容纳5个位。
 * 例如，不可能出现j = 3和i = 2的情况，因为第3位和第2位之间放不下M。
 *
 * 示例1:
 *  输入：N = 10000000000, M = 10011, i = 2, j = 6
 *  输出：N = 10001001100
 *
 * 示例2:
 *  输入： N = 0, M = 11111, i = 0, j = 4
 *  输出：N = 11111
 *
 * 思路：ij表示的是32位的比特位
 * 网友思路：牛逼
 */
public class Easy0501 {
	public int insertBits(int N, int M, int i, int j) {
		int ans = 0, bit;
		// m右移i位和要插入的位置对应上
		M <<= i;
		for (int k = 0; k < 32; k++) {
			/*k从0到31位开始填充每一位
			当k在[i,j]范围内时在m中取值，不在范围时就去n中取值*/
			bit = (k >= i && k <= j) ? M & (1 << k) : N & (1 << k);
			// 按位动态构造就完事
			ans += bit;
		}
		return ans;
	}

	@Test
	public void test1() {
		System.out.println(insertBits(1024,19,2,6));
	}
}
