package bytedance;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Easy1 {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i <nums.length ; i++) {
			if (map.containsKey(target-nums[i])){
				return new int[]{map.get(target-nums[i]),i};
			}else {
				map.put(nums[i],i);
			}
		}
		return null;
	}

	@Test
	public void test1() {
		System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
	}
}
