package bytedance;

import org.junit.Test;

import java.util.*;

/**
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；
 * 此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
 * 两者都可以是复杂相对路径的组成部分。
 * 更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。
 * 最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * <p>
 * 示例 1：
 * 输入："/home/"
 * 输出："/home"
 * 解释：注意，最后一个目录名后面没有斜杠。
 * <p>
 * 示例 2：
 * 输入："/../"
 * 输出："/"
 * 解释：从根目录向上一级是不可行的，因为根是你可以到达的最高级。
 * <p>
 * 示例 3：
 * 输入："/home//foo/"
 * 输出："/home/foo"
 * 解释：在规范路径中，多个连续斜杠需要用一个斜杠替换。
 * <p>
 * 示例 4：
 * 输入："/a/./b/../../c/"
 * 输出："/c"
 * <p>
 * 示例 5：
 * 输入："/a/../../b/../c//.//"
 * 输出："/c"
 * <p>
 * 示例 6：
 * 输入："/a//b////c/d//././/.."
 * 输出："/a/b/c"
 * <p>
 * 思路：把合法的目录名按出现顺序入栈，最后弹出来，用stringbuilder头插法输出
 * 官方建议栈使用Deque双端队列，实现类有linkedList和ArrayDeque
 */
public class Medium71 {
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..", ".", ""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
        }
        StringBuilder res = new StringBuilder();
        for (String dir : stack) res.insert(0, "/" + dir);
        return (res.length() == 0) ? "/" : res.toString();
    }

    @Test
    public void test1() {
    }

}
