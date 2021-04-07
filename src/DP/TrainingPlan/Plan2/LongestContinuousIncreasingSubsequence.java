package DP.TrainingPlan.Plan2;

/**
 * 这道题要求index连续，所以实质上就是求increasing subarray
 * 用一个维度就足够了
 * nums[i] > nums[i - 1]    --> dp[i] = dp[i - 1] + 1;
 * else
 *                          --> dp[i] = 1;
 */

import java.util.Arrays;

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
        Arrays.fill(dp, 1); /**对于每个位置，实际上一开始符合要求的长度就是1*/
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

/**LintCode版本：
 * 要求连续上升/连续下降中的最大值
 * 脑子放活一点，其实在dp里面为连续上升/连续下降
 * 分别遍历一遍即可，谁得到的值大取谁。别想复杂了。
 * */
class LintCodefindLengthOfLCI {
    /**
     * @param A: An array of Integer
     * @return: an integer
     */
    /**难点：可以是连续上升/连续下降*/
    public int longestIncreasingContinuousSubsequence(int[] A) {
        // sanity check
        if (A == null || A.length == 0) {
            return 0;
        }
        int[] dp = new int[A.length];
        int glbLongest = 1;
        //base case
        Arrays.fill(dp, 1);
        //induction rule（连续上升）
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] < A[i]) {
                dp[i] = dp[i - 1] + 1;
                //update glb
                glbLongest = Math.max(glbLongest, dp[i]);
            } else {
                dp[i] = 1;
            }
        }
        //induction rule（连续下降）
        Arrays.fill(dp, 1);  //重置
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] > A[i]) {
                dp[i] = dp[i - 1] + 1;
                //update glb
                glbLongest = Math.max(glbLongest, dp[i]);
            } else {
                dp[i] = 1;
            }
        }
        return glbLongest;
    }
}

