package crackingcode;

import org.junit.Test;

/**
 * 请实现整数数字的乘法、减法和除法运算，运算结果均为整数数字，程序中只允许使用加法运算符和逻辑运算符，
 * 允许程序中出现正负常数，不允许使用位运算。
 *
 * 你的实现应该支持如下操作：
 *
 * Operations() 构造函数
 * minus(a, b) 减法，返回a - b
 * multiply(a, b) 乘法，返回a * b
 * divide(a, b) 除法，返回a / b
 * 示例：
 *
 * Operations operations = new Operations();
 * operations.minus(1, 2); //返回-1
 * operations.multiply(3, 4); //返回12
 * operations.divide(5, -2); //返回-2
 *
 *思路：只允许使用加法，实现加减乘除
 *
 * 对于减法，因为 a - b = a + (-1) * b， 换句话说，我们只要得到 b 的相反数，就可以用加法实现 a - b。
 *
 * 对于乘法，a * b 相当于是 对 |a| 做 |b| 次连加， 或者对 |b| 做 |a| 次连加，但是，这里我们必须要注意，a 和 b 都有可能是负数。
 *
 * 对于除法，a / b 相当于是让|b| 自己加自己，然后我们设置一个计数器，当|b| 加自己大于 |a| 的时候，计数器停。
 *
 */
public class Medium1609 {
	class Operations {

		public Operations() {

		}

		public int minus(int a, int b) {
			return a + negative(b);
		}

		/*实现返回b的相反数，方法：如果b大于零，就让0加b次-1，b小于零，就让0加b次1*/
		private int negative(int b) {
			int c = b > 0 ? -1 : 1;
			int res = 0;
			while (b != 0) {
				res += c;
				b += c;
			}
			return res;
		}

		/*先求ab的绝对值，累加得到乘积
		 * 再根据ab符号判断乘积正负*/
		public int multiply(int a, int b) {
			//先计算绝对值乘积res
			int abs_a = abs(a);
			int abs_b = abs(b);
			int res = 0;
			//避免超时，我们让小数在前面
			if (abs_a > abs_b) res = multiply(abs_b, abs_a);
			for (int i = 1; i <= abs_a; i++) {
				res += abs_b;
			}
			//再判断res符号
			if ((a > 0 && b > 0) || (a < 0 && b < 0)) return res;
			return negative(res);
		}

		/*求n的绝对值*/
		private int abs(int n) {
			if (n < 0) return negative(n);
			return n;
		}

		/*除法同样先用绝对值计算结果，再判断符号
		 * 除法就是看看a由有几个b相加，所以让a一直减b，记录次数，直到a小于b*/
		public int divide(int a, int b) {
			int abs_a = abs(a);
			int abs_b = abs(b);

			int res = 0;
			int negative_abs_b = negative(b);
			while (abs_a >= abs_b) {
				abs_a += negative_abs_b;
				res++;
			}
			//再考虑res的符号
			if ((a > 0 && b > 0) || (a < 0 && b < 0)) return res;
			return negative(res);
		}
	}

	@Test
	public void test1() {

	}
}
