package Array.Plan1;

import java.util.HashMap;
import java.util.Map;

/**
 * input is unsorted
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();  //<value, idx>
        for (int i = 0; i < nums.length; i++) {
            Integer idx = map.get(target - nums[i]);
            if (idx != null) {
                return new int[]{idx, i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}

