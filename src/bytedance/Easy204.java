package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * 示例:
 *
 * 输入: 10
 * 输出: 4
 * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 *
 * 思路：判断质数很简单，难的是如何高效筛选。
 * 可以从2开始，它的倍数一定都不是质数，同理3的倍数也不是，这样一次判断到n
 * 但其实不用判断到n，比如n为12=2*6，=3*4，我就不用去判断4和6了，分界点在根号n。
 * 同样内部循环也可以从i*i开始判断。
 */
public class Easy204 {
    public int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;
        //统计有多少个质数
        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }

    @Test
    public void test1() {

    }

}
