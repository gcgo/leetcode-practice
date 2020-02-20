package bytedance;

import org.junit.Test;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 *
 */
public class Medium5 {
	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1) return "";
		int n = s.length();
		String res = null;

		boolean[][] dp = new boolean[n][n];//dp意思是从i到j字符串是否是回文子串，
		// 一个长度大于3的子串可以通过它头尾各减去一个后的子串来判断。如判断baab，只需要判断aa是回文，且b==b，则baab是回文

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				//这个矩阵是从右下角开始一层一层往上从填充的，每一层是从左往右，矩阵只用到了对角线右半边。
				//dp状态转移用到的是它左下方的那个元素。
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

				if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
					res = s.substring(i, j + 1);
				}
			}
		}

		return res;
	}

    @Test
    public void test1() {
		System.out.println(longestPalindrome("babad"));
    }
}
