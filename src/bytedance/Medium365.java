package bytedance;

import org.junit.Test;

/**
 * 有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
 * <p>
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
 * <p>
 * 你允许：
 * <p>
 * 装满任意一个水壶
 * 清空任意一个水壶
 * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 * <p>
 * 示例 1: (From the famous "Die Hard" example)
 * 输入: x = 3, y = 5, z = 4
 * 输出: True
 * <p>
 * 示例 2:
 * 输入: x = 2, y = 6, z = 5
 * 输出: False
 * <p>
 * 思路：
 * 比如，x = 3, y = 5, z = 4，步骤：
 * 1.y装满，往x里倒，即5-3=2；y有水2,x满
 * 2.x倒掉，把y中水倒入x;x有水2余空位1，y空
 * 3.y装满，往x里倒，即5-1=4;bingo
 * <p>
 * 数学定理：若a,b是整数,且gcd(a,b)=d，那么对于任意的整数x,y,ax+by都一定是d的倍数，特别地，一定存在整数x,y，使ax+by=d成立。
 * <p>
 */
public class Medium365 {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;
        return z % GCD(x, y) == 0;
    }

    public int GCD(int a, int b) {//返回a和b的最大公约数
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Test
    public void test1() {
        System.out.println(canMeasureWater(3, 5, 4));
        System.out.println(canMeasureWater(2, 6, 5));
    }
}
