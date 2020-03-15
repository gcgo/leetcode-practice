package javaoffer;

import org.junit.Test;

/**
 *稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 *
 * 示例1:
 *  输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 *  输出：-1
 *  说明: 不存在返回-1。
 * 示例2:
 *  输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 *  输出：4
 * 提示:
 * words的长度在[1, 1000000]之间
 *
 * 思路：这道题实际上在常规的二分查找的基础上只需要添加一部分：在mid是空字符串的时候该如何处理
 * 当mid时空字符串的时候，我们只需要向右搜索找到最近的非空字符串即可。
 * 如果在右边找不到字符串呢？右边界right = mid。
 */
public class Easy1005 {
	public int findString(String[] words, String s) {
		int left = 0, right = words.length;
		while (left < right) {
			int mid = left + (right - left) >> 1;
			//处理mid为空的情况
			while (mid < right && words[mid].equals("")) {
				mid++;
			}
			if (mid == right) {//如果到头了，那就右半边都不用搜索了
				right = left + (right - left) >> 1;
				continue;
			}
			//二分查找模板
			if (words[mid].equals(s)) {
				return mid;
			} else if (words[mid].compareTo(s) > 0) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

	@Test
	public void test1() {

	}
}
