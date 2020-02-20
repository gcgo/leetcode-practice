package bytedance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，
 * 判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *  
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 */
public class Medium15 {
	 public List<List<Integer>> threeSum(int[] nums) {
	       //1先排序
	        Arrays.sort(nums);
	        List<List<Integer>> res = new ArrayList<>();
	        int len = nums.length;
	        //2遍历数组
	        for (int i = 0; i < nums.length; i++) {
				if (nums[i]>0) {//因为已经排序了，后面肯定都大于0了
					break;
				}
				if (i>0&&nums[i]==nums[i-1]) {//排除重复组合！！
					continue;
				}
				int begin = i + 1;//在[i+1,end]中找和为0-nums[i]的数
				int end = len - 1;
				//滑动窗口查找
				while (begin<end) {
					int sum=nums[i]+nums[begin]+nums[end];
					if (sum==0) {//等于0，则保存结果
						res.add(Arrays.asList(nums[i],nums[begin],nums[end]));
						//接着找其他可能的组合
						begin++;
						end--;
						while (begin < end && nums[begin] == nums[begin - 1])//排除重复组合！！
							begin++;
						while (begin < end && nums[end] == nums[end + 1])//排除重复组合！！
							end--;

					}else if(sum>0){//大于零，证明最大值大了，往回退一格
						end--;
					}else {//小了，则是第二个数往前走一格
						begin++;
					}
				}
			}
	        return res;
	    }
	 
	 @Test
	 public void test1() {
		 System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
	 }
}
