package DP.TrainingPlan;

/**
 * dp[i] : the largest sum of subarray !!! ending at index i (including i) !!!
 * induction rule:
 *               dp[i] = nums[i] + dp[i-1]      (if dp[i-1] >= 0)
 * else          dp[i] = nums[i]
 * result:       glbMax from all dp[i]
 * base case:    dp[0] = nums[0]
 *
 * SC:O(1)
 * TC:O(N)
 * */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        //base case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int glbMax = nums[0];
        int[] dp = new int[nums.length];
        int preMax = nums[0];         //base case
        for (int i = 1; i < nums.length; i++) {  //induction rule
            preMax = preMax >= 0 ? nums[i] + preMax : nums[i];
            glbMax = Math.max(preMax, glbMax);
        }
        return glbMax;
    }
}



