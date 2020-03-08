package javaoffer;

import org.junit.Test;

/**
 *给定一个32位整数 num，你可以将一个数位从0变为1。请编写一个程序，找出你能够获得的最长的一串1的长度。
 *
 * 示例 1：
 * 输入: num = 1775(11011101111)
 * 输出: 8
 *
 * 示例 2：
 * 输入: num = 7(0111)
 * 输出: 4
 *
 * 思路：用一个变量记录last上一个连续1的个数，如果两串连续1的块中间的0超过了一个，则last设置位0
 *
 */
public class Easy0503 {
	public int reverseBits(int num) {
		int count = 0;//统计1的个数
		int max = 0;//保存两段1的总数最大值
		int last = 0;//保存上一段1的个数
		while (num != 0) {
			if ((num & 1) == 1) {//证明该位是1
				count++;//记录长度
			} else {//如果遇到该位是0
				if (count > 0) {//看一下前面是否记录了1
					if (count + last > max) {//更新最大值
						max = count + last;
					}
					last = count;//由last记录这个0前面的1数量
					count = 0;//count准备记录下一段1的个数
				} else {//如果该位是0，可能是一开头就是0，也可能是它的前一位也是0所以count清零了。
					//这种情况相当于两端1中间有多个零，所以无法连续。则抛弃前面记录的1的长度。重新记录
					last = 0;
				}
			}
			num >>= 1;//num右移一位
		}
		/*最后num所有位都遍历完了，在统计最后一次count*/
		if (count > 0 && count + last > max) {
			max = count + last;
		}
		//max是中间至多有一个0间隔的连续1的个数，加上那个要反过来的0，就是最终答案。
		return max + 1;
	}

	@Test
	public void test1() {

	}
}
