package Array.Plan5;

import java.util.HashMap;
import java.util.Map;

/**实质：minimum subarray sum equals to k II
 */
public class SubarraySumEqualsTokII {
    /**
     Sol1:
     subsarray sum == k, unsorted-->memo方法*/
    public int subarraySumEqualsKII(int[] nums, int k) {
        // sanity check
        if (nums == null || nums.length == 0 || k == 0) {
            return 0;
        }
        int glbShortest = Integer.MAX_VALUE;
        int preSum = 0;
        Map<Integer, Integer> map = new HashMap<>(); //<preSum, rightMostIdx>
        map.put(0, -1);  //init preSum memo
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];  //cal preSum
            //check Res
            if (map.containsKey(preSum - k)) {
                glbShortest = Math.min(glbShortest,i - map.get(preSum - k));
            }
            //update memo
            map.put(preSum, i);
        }
        return glbShortest == Integer.MAX_VALUE ? -1 : glbShortest; //因为可能没有Sum == k, 要check一下
    }
}
