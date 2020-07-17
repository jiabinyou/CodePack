package Array.Plan5;

import java.util.HashMap;
import java.util.Map;

/**
 Sol. preSum
 重要！！题目给出"The range of numbers in the array is [-1000, 1000]"
 因为input有负数，所以preSum无sort性质，需使用
 BF + MEMO优化 转化成look up count
 --》求# pairs subarray sum == k， preSum DS可使用map
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //preSum DS
        Map<Integer, Integer> map = new HashMap<>(); //<distince preSum val, fre>
        //init preSum
        map.put(0, 1);

        //固定j, iterate nums
        int count = 0;
        int sum = 0; //preSum
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j]; //caculate cur pos preSum
            count += map.getOrDefault(sum - k, 0);  //check res
            map.put(sum, map.getOrDefault(sum, 0) + 1);//update sum into preSum ds
        }
        return count;
    }
}
