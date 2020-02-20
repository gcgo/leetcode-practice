package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * 思路：dfs+回溯
 */
public class Medium93 {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        doRestore(result, "", s, 0);
        return result;
    }

    /**
     * @param result 结果集
     * @param path   临时结果
     * @param s      所给字符串
     * @param k      记录当前保存了几段
     */
    private void doRestore(List<String> result, String path, String s, int k) {
        if (s.isEmpty() || k == 4) {//他俩有一个满足，停止计算，祛除了所给字符串本身不合法的情况以及dfs不合法的结果
            if (s.isEmpty() && k == 4)//他俩同时满足，才是一个正确结果
                result.add(path.substring(1));//因为每次dfs是.+part，所以最后结果开头是.从index=1开始截取返回。
            return;
        }
        //若当前s的第一个字符是0，则它独占一个IP段位。不能出现0xx.xxx.xxx.xxx，这种0开头的情况。
        for (int i = 1; i <= (s.charAt(0) == '0' ? 1 : 3) && i <= s.length(); i++) { // Avoid leading 0
            String part = s.substring(0, i);
            if (Integer.parseInt(part) <= 255)
                doRestore(result, path + "." + part, s.substring(i), k + 1);
        }
    }


    @Test
    public void test1() {
        System.out.println(restoreIpAddresses("25525511135"));
    }
}
