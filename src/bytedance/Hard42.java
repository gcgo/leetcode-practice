package bytedance;

import org.junit.Test;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 思路：
 * 直接按问题描述进行。对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值。
 * 从第二列开始，找其左边最高的墙和右边最高的墙，取较小的，然后减去当前列的高度。
 * 可以用动态规划优化：当前列左边最高的墙=max(当前列左边的墙，当前列左边的左边最高的墙)。
 * 注意：当前列左边最高的墙不包括当前列！！！！
 */
public class Hard42 {
    public int trap(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++) {//计算每个元素左边最高的墙有多高，从第二个元素开始
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }
        for (int i = height.length - 2; i >= 0; i--) {//计算每个元素右边最高的墙有多高，从倒数第二个元素开始
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }
        for (int i = 1; i < height.length - 1; i++) {//从第二个墙开始
            int min = Math.min(max_left[i], max_right[i]);//取左右两边高度比较矮的墙
            if (min > height[i]) {//若他高于当前的墙，则当前墙和左右墙形成一个凹槽，可以积水
                sum = sum + (min - height[i]);//计算积水
            }
        }
        return sum;
    }

    @Test
    public void test1() {
    }
}
