package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。
 * 所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * <p>
 * 思路：分为两组，如AB和CD，把AB的和存入hashmap，key是和，value是和出现的次数（即不同组合）
 * 然后计算CD的和，查看map里有没有CD和的相反数。有的话，get出来加到res里
 */
public class Medium454 {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> mapAB = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                mapAB.put(a + b, mapAB.getOrDefault(a + b, 0) + 1);
            }
        }
        int res = 0;
        for (int c : C) {
            for (int d : D) {
                int cd = -(c + d);
                if (mapAB.containsKey(cd)) res += mapAB.get(cd);
            }
        }
        return res;
    }

    @Test
    public void test1() {

    }
}
