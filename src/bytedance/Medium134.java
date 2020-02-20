package bytedance;

import org.junit.Test;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明: 
 * <p>
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 * <p>
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * 输出: 3
 * <p>
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 * <p>
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * <p>
 * 输出: -1
 * <p>
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * <p>
 * 思路：暴力法，逐个起点遍历；
 * 网友巧妙法：https://leetcode-cn.com/problems/gas-station/solution/shi-yong-tu-de-si-xiang-fen-xi-gai-wen-ti-by-cyayc/
 */
public class Medium134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int des = 0; des < gas.length; des++) {//终点，也是起点
            int gasnow;
            int i = des + 1;//环形跑道，先走一步；
            gasnow = gas[i - 1] - cost[i - 1];//起步所剩的油
            if (gasnow < 0) continue;//一上来油就不够，那就换起点
            while (i != des) {//如果起点没有回到终点就继续跑
                if (i == gas.length) {//如果数组越界，就重置i，因为是环形跑道
                    i = 0;
                    continue;
                }
                gasnow = gasnow + gas[i] - cost[i];//到一个新站算一下要去下一站所剩的油
                if (gasnow < 0) break;//如果油不够，就跳出
                i++;//油够就继续下一站
            }
            if (i == des)//最后如果来到了终点
                return i;//证明i为起点可以跑一圈
        }
        return -1;//即遍历完起点也没有能跑玩一圈的
    }

    @Test
    public void test1() {
        int[] gas = new int[]{2, 3, 4};
        int[] gas1 = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 3};
        int[] cost1 = new int[]{3, 4, 5, 1, 2};
        int res = canCompleteCircuit(gas1, cost1);
        System.out.println(res);
    }
}
