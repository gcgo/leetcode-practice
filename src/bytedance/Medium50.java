package bytedance;

import org.junit.Test;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * <p>
 * 思路：快速幂算法,我们知道A=x^(n)，想求x^(2n)，不需要再计算一遍x^(n)了，而是直接复用结果:
 * x^(2n)=A*A
 * x^(2n+1)=A*A*x;
 */
public class Medium50 {
    public double myPow(double x, int n) {
        long N=n;
        if (n == 0) return 1.0;
        if (n > 0) return helper(x, N);
        else return helper(1/x, -N);//大坑！，这块n如果等于-integet.MAX，则-n就越界了，所以应该先把n存为long型
    }

    private double helper(double x, long n) {
        if (n == 1) return x;
        double tmp = helper(x, n >> 1);
        if (n % 2 != 0) {
            return tmp * tmp * x;//n是奇数情况
        } else {
            return tmp * tmp;//n是偶数情况
        }
    }

    //**************************************************************************
    @Test
    public void test1() {
        System.out.println(myPow(1, -2147483647));
    }

}
