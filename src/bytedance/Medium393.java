package bytedance;

import org.junit.Test;

/**
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。
 * 剩下的没有提及的二进制位，全部为这个符号的unicode码。
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 * <p>
 * 注意:
 * 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * 示例 1:
 * <p>
 * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
 * <p>
 * 返回 true 。
 * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
 * 示例 2:
 * <p>
 * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
 * <p>
 * 返回 false 。
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 * <p>
 * 思路：题的意思是，给一个数组，看看这个数组是否是一个合法的UTF-8。规则实际有三个：
 * 1.UTF-8 中的一个字符可能的长度为 1 到 4 字节
 * 2.对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 3.对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。
 * <p>
 * 这就意味着我们判断时需要做这两件事：
 * 1.如果在处理一个 UTF-8 字符的开始，我们需要提取一个字节的前 N 比特，其中 N 不会超过 4，之后的比特就不需要处理了。
 * 2.如果是在处理一个 UTF-8 字符的过程中，我们只需要检查前两位是不是 10 就可以了。
 */
public class Medium393 {
    public boolean validUtf8(int[] data) {
        // Number of bytes in the current UTF-8 character
        int numberOfBytesToProcess = 0;

        // Masks to check two most significant bits in a byte.
        int mask1 = 1 << 7;//验证高位是不是1
        int mask2 = 1 << 6;//验证次高位是不是0

        // For each integer in the data array.
        for (int datum : data) {
            // If this is the case then we are to start processing a new UTF-8 character.
            if (numberOfBytesToProcess == 0) {
                int mask = 1 << 7;
                while ((mask & datum) != 0) {
                    numberOfBytesToProcess++;
                    mask = mask >> 1;
                }

                // 等于0证明一开始的字符就是单字节
                if (numberOfBytesToProcess == 0) {
                    continue;
                }

                // 长度超过4，或者等于1，都是非法的
                if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1) {
                    return false;
                }

            } else {//进到这说明要处理的肯定不是单字节字符，而是n>1字节的。接下来规则要变

                //使用两个Mark来判断，验证前两位是不是10
                if (!((datum & mask1) != 0 && (mask2 & datum) == 0)) {
                    return false;
                }
            }

            // 若else里没返回则证明是有效字节，从总字节里减去一个。
            numberOfBytesToProcess--;
        }

        //用于排除格式都正确，但是缺少字节的情况。
        return numberOfBytesToProcess == 0;
    }

    @Test
    public void test1() {

    }
}
