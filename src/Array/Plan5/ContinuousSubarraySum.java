package Array.Plan5;

/**
 Sol1.
 这道题我们要操作的是(Presum[j] - presum[i]) % k， 即使input全部正数，操作对象也不再有sorted性质
 所以使用arr记录PreSumi意义不大
 所以使用BF + MEMO ：
 因为要求 max length
 preSum DS选用map<preSum % k, leftMost idx>
 */

import java.util.HashMap;
import java.util.Map;

/**
 input正数，但是k可能是负数，0，正数
 special case:
 1.k = 0, 只有input也是全0时候，才返回true
 */

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return false;
        }

        //preSum ds
        Map<Integer, Integer> map = new HashMap<>(); //<preSum % k, leftMostIdx>
        //init
        map.put(0, -1);

        //fix j, iterate
        int glbLongest = 0;
        int sum = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j]; //update preSum
            int remainder = k != 0 ? sum % k : sum;
            Integer leftMostIdx = map.get(remainder);
            if (leftMostIdx != null) {
                glbLongest = Math.max(glbLongest, j - leftMostIdx);
            }
            map.putIfAbsent(remainder, j);
        }
        return glbLongest  >= 2 ? true : false;
    }
}
