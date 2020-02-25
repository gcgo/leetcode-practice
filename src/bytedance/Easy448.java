package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 *
 * 思路：因为[1,n]存了n个数，所以可以用下标与数映射，也就是0对应1,1对应2，。。。，
 * 如果该下标出现就标记它为负数。最后是正数的则证明数组里没有该下标这个数
 */
public class Easy448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;//映射下标
            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {//为正数证明该下标没有映射到，下标与数映射关系为n=index+1
                list.add(i + 1);
            }
        }
        return list;

    }

    @Test
    public void test1() {
        System.out.println(findDisappearedNumbers(new int[]{8, 6, 6, 5, 4, 4, 2, 1}));
    }
}
