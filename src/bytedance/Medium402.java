package bytedance;

import org.junit.Test;

/**
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 * <p>
 * 思路：StringBuilder重新构建结果，原则就是，从头开始遍历，只留下小的。
 * 下一个若比当前sb里最后一个小，则用小的替换sb里这个。可以替换K次，注意0不能开头
 */
public class Medium402 {
    public String removeKdigits(String num, int k) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (k > 0 && res.length() > 0 && res.charAt(res.length() - 1) > c) {//如果下一个元素比sb里的小，删sb里的
                res.deleteCharAt(res.length() - 1);
                k--;//剩余操作数-1
            }
            if (res.length() == 0 && c == '0') continue;//添加之前的保险，以0开头
            res.append(c);//添加当前这个小的
        }
        //此处解决一种特殊情况，操作次数K没用完，因为num在某一段保持了升序，后面数一个比一个大。此时就从后面删除就好了
        //如12345，k=2,你只能删除45，结果为123最小
        while (k-- > 0 && res.length() > 0) res.deleteCharAt(res.length() - 1);
        return res.length() == 0 ? "0" : res.toString();
    }

    //**************************************************************************
    @Test
    public void test1() {
        System.out.println(removeKdigits("10200", 1));
    }

}
