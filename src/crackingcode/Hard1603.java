package crackingcode;

import org.junit.Test;

/**
 * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
 *
 * 要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回X值最小的点，X坐标相同则返回Y值最小的点。
 * 示例 1：
 * 输入：
 * line1 = {0, 0}, {1, 0}
 * line2 = {1, 1}, {0, -1}
 * 输出： {0.5, 0}
 * 示例 2：
 * 输入：
 * line1 = {0, 0}, {3, 3}
 * line2 = {1, 1}, {2, 2}
 * 输出： {1, 1}
 * 示例 3：
 * 输入：
 * line1 = {0, 0}, {1, 1}
 * line2 = {1, 0}, {2, 1}
 * 输出： {}，两条线段没有交点
 * 提示：
 *
 * 坐标绝对值不会超过2^7
 * 输入的坐标均是有效的二维坐标
 *
 * 思路：给的是两条线段，计算两条直线的交点，看一看交点坐标是否在其中一个线段范围内，是就线段相交，不是就不相交
 * 斜率计算不能用整形，但是double计算会带来误差，需要注意
 * 还有斜率为无穷的特殊情况，需要单独考察
 */
public class Hard1603 {
	public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
		if (start1[0] > end1[0] || (start1[0] == end1[0] && start1[1] > end1[1])) {     //交换端点，保证start1在end1左边，两点横坐标相同时，保证start1在end1下面
			int[] temp = start1;
			start1 = end1;
			end1 = temp;
		}
		if (start2[0] > end2[0] || (start2[0] == end2[0] && start2[1] > end2[1])) {     //交换端点，保证start22在end2左边
			int[] temp = start2;
			start2 = end2;
			end2 = temp;
		}
		/*先考察斜率为0的情况*/
		int delta_x1 = end1[0] - start1[0];
		int delta_y1 = end1[1] - start1[1];
		int delta_x2 = end2[0] - start2[0];
		int delta_y2 = end2[1] - start2[1];
		if (delta_x1 == 0 && delta_x2 == 0) {       //斜率均为无穷
			if (start1[0] == start2[0]) {       //一条直线上的两个线段
				if (end1[1] < start2[1] || end2[1] < start1[1]) return new double[0];      //判断是否相交
				else return new double[]{start1[0], Math.max(start1[1], start2[1])};
			} else {      //平行线
				return new double[0];
			}
		}
		if (delta_x1 == 0 || delta_x2 == 0) {       //其中一条线段斜率为无穷
			if (delta_x1 == 0) {        //让第二条斜率为无穷（第一条斜率为零时交换两条线）
				int[] temp_arr = start1;
				start1 = start2;
				start2 = temp_arr;
				temp_arr = end1;
				end1 = end2;
				end2 = temp_arr;
				int temp = delta_x1;
				delta_x1 = delta_x2;
				delta_x2 = temp;
				temp = delta_y1;
				delta_y1 = delta_y2;
				delta_y2 = temp;
			}
			double k = 1.0 * delta_y1 / delta_x1;
			double b = start1[1] - k * start1[0];
			double intersection_y = k * start2[0] + b;
			if (Math.min(start1[1], start2[1]) <= intersection_y && intersection_y <= Math.max(end1[1], end2[1])) {  //判断交点是否在两条线段内
				return new double[]{start2[0], intersection_y};
			} else {
				return new double[0];
			}
		}
		/*计算斜率*/
		double k1 = 1.0 * (start1[1] - end1[1]) / (start1[0] - end1[0]);
		double k2 = 1.0 * (start2[1] - end2[1]) / (start2[0] - end2[0]);
		double b1 = start1[1] - k1 * start1[0];
		double b2 = start2[1] - k2 * start2[0];
		//如果斜率相等，看看是否重叠
		if (k1 == k2) {
			if (b1 == b2) {//b也相等，说明在一条直线上
				if (end1[0] < start2[0] || end2[0] < start1[0]) return new double[0];
				else {
					double intersection_x = Math.max(start1[0], start2[0]);
					return new double[]{intersection_x, k1 * intersection_x + b1};
				}
			} else {//平行线
				return new double[0];
			}
		}
		/*k1!=k2,即有交点*/
		double intersection_x = (b2 - b1) / (k1 - k2);
		double intersection_y = k1 * intersection_x + b1;
		if (start1[0] <= intersection_x && intersection_x <= end1[0] &&
				start2[0] <= intersection_x && intersection_x <= end2[0]) {//判断交点X坐标是否在线段区间内
			return new double[]{intersection_x, intersection_y};
		} else {
			return new double[0];
		}
	}

	@Test
	public void test1() {

	}
}
