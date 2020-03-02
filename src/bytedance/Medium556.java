package bytedance;

import org.junit.Test;

/**
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的数字完全相同，并且其值大于n。
 * 如果不存在这样的32位整数，则返回-1。
 * <p>
 * 示例 1:
 * 输入: 12
 * 输出: 21
 * <p>
 * 示例 2:
 * 输入: 21
 * 输出: -1
 * <p>
 * 思路：就是求自然序的下一个数，思路和31题一样
 */
public class Medium556 {
    public int nextGreaterElement(int n) {
        char[] a = ("" + n).toCharArray();
        int i = a.length - 2;
        while (i >= 0 && a[i + 1] <= a[i]) i--; //从后往前找那个突然变小的数a[i]
        if (i < 0) return -1;//没找到就证明当前这个数是最大的了，返回-1
        int j = a.length - 1;
        while (j >= 0 && a[j] <= a[i]) j--;//从后向前找第一个大于a[i]的数
        swap(a, i, j);//交换他们俩
        j = a.length - 1;
        i++;//把a[i]后面所有数字逆转一下就可以了
        while (i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
        long res = Long.parseLong(new String(a));//防止1999999999这种int越界
        return res <= Integer.MAX_VALUE ? (int) res : -1;
    }

    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    @Test
    public void test1() {
        System.out.println(Integer.MAX_VALUE);
//        System.out.println(nextGreaterElement(1999999999));
    }

}
