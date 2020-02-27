package bytedance;

import org.junit.Test;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 思路：
 * 动态规划
 * 1<=i<=n，我们分别以i为根，计算形成的树，最后加起来，就是结果
 * 而对于i为根的数，左子树就是右[1,i-1]组成，右子树就是由[i+1,n]组成。
 * 且总数是两边数目相乘。
 * 所以设长度为n的序列的二叉搜索树个数有G（n）种，以i为根节点的搜索树个数为f(i)
 * 则f(i)=G(i-1)·G(n-i)，其中G(0)=1,G(1)=1。
 *
 */
public class Medium96 {

	public int numTrees(int n) {
		int[] G = new int[n + 1];//G[i]表示以1 ... n 为节点组成的二叉搜索树有多少种？
		G[0] = 1;//空树
		G[1] = 1;//就一个根节点

		for (int i = 2; i <= n; ++i) {
			for (int j = 1; j <= i; ++j) {//分别以j为根统计
				G[i] += G[j - 1] * G[i - j];
			}
		}
		return G[n];
	}

	@Test
	public void test1() {
		System.out.println(numTrees(5));
	}

}
