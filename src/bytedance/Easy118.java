package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 示例:
 * <p>
 * 输入: 5
 * 输出:
 * [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class Easy118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = null;
        for (int i = 0; i < numRows; i++) {//i表示行
            tmp = new ArrayList<>();
            for (int j = 0; j <= i; j++) {//j表示该行第几个数
                if (j == 0 || j == i)//边界都是1
                    tmp.add(1);
                else {
                    int a = res.get(i - 1).get(j - 1);
                    int b = res.get(i - 1).get(j);
                    tmp.add(a + b);
                }
            }
            res.add(tmp);
        }
        return res;
    }

    @Test
    public void test1() {
        System.out.println(Arrays.toString(generate(5).toArray()));
    }
}
