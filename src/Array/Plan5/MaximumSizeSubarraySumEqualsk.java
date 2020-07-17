package Array.Plan5;

import java.util.HashMap;
import java.util.Map;

/**
 Sol. preSum
 重要！！
 因为input有负数，所以preSum无sort性质，需使用
 BF + MEMO优化 转化成look up leftMost
 --》求# pairs subarray sum == k， preSum DS可使用map
 */
public class MaximumSizeSubarraySumEqualsk {
    public int maxSubArrayLen(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //preSum ds
        Map<Integer, Integer> map = new HashMap<>(); //<preSum val, leftMost idx>
        //init
        map.put(0, -1);

        //fix j, iterate nums
        int glbLongest = 0;
        int sum = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j]; //update preSum
            Integer leftMost = map.get(sum - k);
            if (leftMost != null) {  //check res
                glbLongest = Math.max(glbLongest, j - leftMost);
            }
            map.putIfAbsent(sum, j);
        }
        return glbLongest;
    }
}

