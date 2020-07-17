package Array.Plan5;

import java.util.HashSet;
import java.util.Set;

/**
 加了一个维度的preSum，类似3Sum
 O(n ^ 2)
 */
public class SplitArrayWithEqualSum {
    public boolean splitArray(int[] nums) {
        if (nums == null || nums.length < 7) {
            return false;
        }
        //中心开花
        int[] prefixSum = getPrefixSum(nums);
        for (int j = 3; j < nums.length - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (prefixSum[i] == prefixSum[j] - prefixSum[i + 1]) {
                    set.add(prefixSum[i]);
                }
            }
            for (int k = j + 2; k < nums.length - 1; k++) {
                if (prefixSum[nums.length] - prefixSum[k + 1] == prefixSum[k] - prefixSum[j + 1] &&
                        set.contains(prefixSum[k] - prefixSum[j + 1])) {
                    return true;
                }
            }
        }
        return false;
    }

    private int[] getPrefixSum(int[] nums) {
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        return prefixSum;
    }
}
