package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
 * <p>
 * 例如，
 * <p>
 * 给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
 * <p>
 * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
 * 思路：十叉树先序遍历
 * 方法1：非递归
 * 方法2：dfs
 */
public class Medium386 {
    public List<Integer> lexicalOrder1(int n) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<Integer> tree = new LinkedList<>();
        if (n < 10) {//从大到小入栈
            for (int i = n; i > 0; i--) tree.push(i);
        } else for (int i = 9; i > 0; i--) tree.push(i);
        int t, m;
        while (!tree.isEmpty()) {
            t = tree.pop();//从小的开始弹出
            res.add(t);//加入结果集
            if (t * 10 > n) continue;//证明不用处理以t开头的多位数
            else {
                m = n - t * 10;//m为t开头的多位数和n的差距
                if (m > 9) m = 9;//如果差超过9，意味着下一回还得增加一位
            }
            for (int i = m; i >= 0; i--)//同样从大到小入栈
                if (t * 10 + i <= n)
                    tree.push(t * 10 + i);
        }
        return res;
    }

    //*******************************************************************************
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; ++i) {
            dfs(i, n, res);
        }
        return res;
    }

    public void dfs(int cur, int n, List<Integer> res) {
        if (cur > n) return;
        else {
            res.add(cur);//加入结果集
            for (int i = 0; i < 10; i++) {
                if (10 * cur + i > n) return;
                dfs(10 * cur + i, n, res);
            }
        }
    }

    @Test
    public void test1() {
        System.out.println(lexicalOrder(13));
    }
}
