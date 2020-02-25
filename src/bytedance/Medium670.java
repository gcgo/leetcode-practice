package bytedance;

import org.junit.Test;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 * <p>
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * <p>
 * 思路：从第一个数开始搜索，若后面有比他大的，找到那个最大的，交换位置即可。
 */
public class Medium670 {
    public int maximumSwap(int num) {
        char[] c = Integer.toString(num).toCharArray();
        for (int i = 0; i < c.length; i++) {
            int cur = Integer.parseInt(String.valueOf(c[i]));//当前参考值
            int max = cur;//记录后面比他大的最大值
            int l = i;
            int ind = -1;//用于记录后面“比当前数大的数”里最大的那个位置
            boolean done = false;//用于标记找没找到，没找到则进入下一次for循环
            while (l < c.length) {
                int tmp = Integer.parseInt(String.valueOf(c[l]));
				//tmp >= max这个等于号是为了如1993这样，99都比1大，此时ind应该指向最后面的9
				//tmp != cur是对于1111这种就不用了交换位置了。
                if (tmp >= max && tmp != cur) {
                    max = tmp;
                    done = true;
                    ind = l;
                }
                l++;
            }
            if (done) {//找到了就交换
                char tmp = c[i];
                c[i] = c[ind];
                c[ind] = tmp;
                return Integer.parseInt(String.valueOf(c));
            }
        }
        return num;
    }

    @Test
    public void test1() {
        int res2 = maximumSwap(1993);
        System.out.println(res2);
    }
}
