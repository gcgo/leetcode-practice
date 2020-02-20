package bytedance;

import org.junit.Test;

import java.util.Arrays;

/**
 * 实现 strStr() 函数。strstr(str1,str2) 函数用于判断字符串str2是否是str1的子串。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。
 * 如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class Easy28 {
    //******************************字符串匹配之sunday算法，看笔记！！******************************************
    public int strStr(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        int[] occ = getOCC(needle);//记录needle字母在needle中最右一次出现的位置，数组下标为字母表ASCII值。默认值为-1
        int jump;
        for (int i = 0; i <= m - n; i += jump) {//haystack从i开始匹配
            int j = 0;//needle从头开始
            while (j < n && haystack.charAt(i + j) == needle.charAt(j))
                j++;
            if (j == n)//needle遍历完了，即找到了
                return i;
            //若没找到，则查找的起始点跳转。
            //当前位置+needle长度<haystack长度？needle长度-
            // 字母表中所查的haystack中needle长度的下一个字母在needle中出现的最右位置：否则长度已经超出加1即可，循环会结束
            jump = i + n < m ? n - occ[haystack.charAt(i + n)] : 1;
        }
        return -1;
    }

    public int[] getOCC(String needle) {
        int[] occ = new int[128];//对应ASCII码0-127的字符在needle中最右一次出现的位置，若没出现，则为-1。
        Arrays.fill(occ, -1);
        for (int i = 0; i < needle.length(); i++)
            occ[needle.charAt(i)] = i;
        return occ;
    }

    //***************************一般方法，特点是好理解，但是慢********************************************
    public int strStr1(String haystack, String needle) {
        if (needle == null || needle.equals("")) return 0;
        for (int i = 0; i < haystack.length(); i++) {
            int j = 0;
            if (haystack.charAt(i) == needle.charAt(j)) {
                int k = i + 1;
                j++;
                while (j < needle.length() &&
                        k < haystack.length() &&
                        haystack.charAt(k) == needle.charAt(j)) {
                    k++;
                    j++;
                }
                if (j == needle.length()) return i;

            }
        }
        return -1;
    }

    @Test
    public void test1() {
        System.out.println(strStr("hello", "hello"));
    }
}
