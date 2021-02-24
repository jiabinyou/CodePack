package DP.TrainingPlan.Plan2;

/**
 * 这道题要求index连续，所以实质上就是求increasing subarray
 */

/**
 * Sol1.
 * tc:o(n), sc:0(n)
 */
public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int longest = 1;
        //dp ds
        int[] dp = new int[nums.length];
        //base case
        dp[0] = 1;
        //induction rule
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                longest = Math.max(longest, dp[i]);
            } else {
                dp[i] = 1;
            }

        }
        return longest;
    }
}

/**
 * Sol1b
 * 优化空间
 */
class SolLCIS1b {
    public int findLengthOfLCIS(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int longest = 1;
        //dp ds + base case
        int curLen = 1;
        //induction rule
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                curLen++;
                longest = Math.max(longest, curLen);
            } else {
                curLen = 1;
            }

        }
        return longest;
    }
}
