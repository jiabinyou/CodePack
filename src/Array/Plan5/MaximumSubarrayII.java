package Array.Plan5;

import java.util.List;

/**
 Non-overlapping subarray：需要分割
 这里要求两段，所以我们分成两段left[], right[]分别记录左右两边与preSum相关的信息即可
 这里要求的特征值是：最大值，所以我们可以想到
 left[]:including cur idx,往左边的max subarray sum
 right[]: including cur idx,往右边的max subarray sum

 难点：这道题虽然是subarray sum相关，但要求non-overlapping subarray，所以
 需要分隔。并且仔细思考，因为prefixSum信息我们不用回头查看，所以边遍历边计算即可，
 真正需要使用空间单独记录的信息是特征值信息。
 */
public class MaximumSubarrayII {
    public int maxTwoSubArrays(List<Integer> nums) {
        // sanity check
        if (nums == null || nums.size() == 0) {
            return 0;
        }
        //计算including cur idx,往左边的max subarray sum
        int[] left = new int[nums.size()];
        int preSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int minSum = 0;
        for (int i = 0; i < nums.size(); i++) {
            preSum += nums.get(i);
            maxSum = Math.max(maxSum, preSum - minSum);
            minSum = Math.min(minSum, preSum);
            left[i] = maxSum;
        }

        //计算including cur idx,往右边的max subarray sum
        int[] right = new int[nums.size()];
        preSum = 0;
        maxSum = Integer.MIN_VALUE;
        minSum = 0;    /**这里minSum初始必须是0，不能是Interger.MAX_VALUE*/
        /**因为这里涉及到preSum - minSum，有减法运算，如果是preSum - Interger.MAX_VALUE，
         由于越界保护，差值可能又从0开始重新算了，导致差值反而比maxSum小，导致出错。
         实际上如果minSum小于0时候也是没什么用处了，所以可以从0开始
         */
        for (int i = nums.size() - 1; i >= 0; i--) {
            preSum += nums.get(i);
            maxSum = Math.max(maxSum, preSum - minSum);
            minSum = Math.min(minSum, preSum);
            right[i] = maxSum;
        }

        //拼合左右两边看是否有合适的解
        int glbMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size() - 1; i++) {
            glbMax = Math.max(glbMax, left[i] + right[i + 1]);
        }
        return glbMax;
    }
}
