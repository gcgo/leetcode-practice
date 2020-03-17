package crackingcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 假设你有两个数组，一个长一个短，短的元素均不相同。找到长数组中包含短数组所有的元素的最短子数组，其出现顺序无关紧要。
 *
 * 返回最短子数组的左端点和右端点，如有多个满足条件的子数组，返回左端点最小的一个。若不存在，返回空数组。
 *
 * 示例 1:
 *
 * 输入:
 * big = [7,5,9,0,2,1,3,5,7,9,1,1,5,8,8,9,7]
 * small = [1,5,9]
 * 输出: [7,10]
 * 示例 2:
 *
 * 输入:
 * big = [1,2,3]
 * small = [4]
 * 输出: []
 * 提示：
 *
 * big.length <= 100000
 * 1 <= small.length <= 100000
 *
 * 思路：滑动窗口，用变量count记录还剩几个small没有被覆盖，用一个map记录覆盖了的small在big中的位置
 *
 */
public class Medium1718 {

    public int[] shortestSeq(int[] big, int[] small) {
        if (small.length > big.length)
            return new int[0];
        //map用于存储small元素在big中出现的位置，初始值都是-1
        Map<Integer, Integer> map = new HashMap<>();
        int count = small.length;
        int[] ans = {0, big.length};

        for (int i : small)
            map.put(i, -1);

        for (int i = 0; i < big.length; i++) {//遍历big
            if (map.containsKey(big[i])) {//如果是small中的字符
                if (map.get(big[i]) == -1)//且之前未出现过
                    count--;//记录还剩几个未出现
                map.put(big[i], i);//更新字符在big中的位置
            }
            if (count <= 0) {//表示截止到i,目前所有small都在当前范围的big中了
                Object[] obj = map.values().toArray();
                int minNum = getMin(obj);//得到最小下标
                if (i - minNum + 1 < ans[1] - ans[0] + 1) {//如果当前这个子串范围小于之前计算的，则更新结果
                    ans[0] = minNum;
                    ans[1] = i;
                }
            }
        }
        if (count > 0)//如果最后small没有覆盖完全，则返回空
            return new int[0];
        return ans;
    }

    int getMin(Object[] obj) {
        int minNum = Integer.MAX_VALUE;
        for (Object i : obj) {
            minNum = Math.min((int) i, minNum);
        }
        return minNum;
    }

    //*********************************************************************************
    @Test
    public void test1() {
        int[] big = new int[]{7, 5, 9, 0, 2, 1, 3, 5, 7, 9, 1, 1, 5, 8, 8, 9, 7};
        int[] small = new int[]{1,5,9};

        System.out.println(Arrays.toString(shortestSeq(big, small)));
    }
}
