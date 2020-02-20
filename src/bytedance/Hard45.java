package bytedance;

import org.junit.Test;

/**
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * <p>
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * <p>
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 说明:
 * 假设你总是可以到达数组的最后一个位置。
 * <p>
 * 思路：从后往前找：若想要跳到最后一个元素A，那就往前找可以一步跳过来且距离最远的那个元素B，
 * 然后同理，找B前面能够一步跳过来的距离最远的元素C。这就是一个递归过程。递归终止条件是跳到第一位置了。
 *
 *思路2：采用贪心算法：
 *  首先我在当前位置，如nums[0],我看看我从这都能去哪，由于nums[0]=2,所以我可以去nums[1]和nums[2]，那我到底要去哪呢？
 *  就得看nums[1]和nums[2]哪个更有前途，即哪个能跳的更远。因为nums[1]=3,nums[2]=1，所以我如果去了nums[1],
 *  我能跳的更远，即1+3>2+1，所以我决定去nums[1]了。然后同理继续往后走。。。。。
 */
public class Hard45 {

    public int jump(int[] nums) {
        int nextIndex=0;//初始位置
        int FarIndex=0;
        int steps=0;//记录步数
        for(int i=0;i<nums.length-1;i++){//为什么是nums.length-1呢？因为到了最后一步就不用跳了
            //在到达当前最远边界之前不断更新下一次最远边界,等到达当前最远边界后，就更新最远边界
            FarIndex=Math.max(FarIndex,i+nums[i]);//即当前位置能跳的最远边界是哪？
            if(i==nextIndex){//nextIndex就是目前我能跳的最远位置了，但我不一定跳到这，而是看这之间哪个点能跳的更远，默认跳到哪个点
                nextIndex=FarIndex;
                steps++;
            }
        }
        return steps;
    }

    //********************************贪心：从后往前递归******************************************************
    //从后往前找：贪心算法，每次都尽量往前跳
    public int jump2(int[] nums) {
        if (nums.length < 2) return 0;
        return backSearch(nums, nums.length - 1, 0);
    }

    private int backSearch(int[] nums, int start, int res) {
        if (start == 0) return res;
        int newStart = start;
        for (int i = start; i >= 0; i--) {
            if (nums[i] != 0 && nums[i] >= start - i) {
                newStart = Math.min(newStart, i);
            }
        }
        return backSearch(nums, newStart, ++res);
    }

    //*********dfs，相当于穷举，超时！！！不行************************************
    public int jump3(int[] nums) {
        return dfs(nums, 0, Integer.MAX_VALUE, 0);
    }

    private int dfs(int[] nums, int start, int res, int tmp) {
        if (start == nums.length - 1) {
            res = Math.min(res, tmp);
            return res;
        }
        if (start > nums.length - 1) return res;

        for (int i = 1; i <= nums[start]; i++) {
            tmp++;
            if (tmp > nums.length - 1) return res;
            if (nums[start] == 0) return res;
            int dfs = dfs(nums, start + i, res, tmp);
            res = Math.min(res, dfs);
            tmp--;
        }
        return res;
    }

    //*********************************************************************************
    @Test
    public void test1() {
//        System.out.println(jump(new int[]{2, 3, 1, 1, 4}));
//        System.out.println(jump(new int[]{1, 2, 3}));
        System.out.println(jump(new int[]{5, 6, 4, 4, 6, 9, 4, 4, 7, 4, 4, 8, 2, 6, 8, 1, 5, 9, 6
                , 5, 2, 7, 9, 7, 9, 6, 9, 4, 1, 6, 8, 8, 4, 4, 2, 0, 3, 8, 5}));
    }
}
