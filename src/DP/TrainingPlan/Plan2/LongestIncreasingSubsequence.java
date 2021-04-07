package DP.TrainingPlan.Plan2;
import java.util.*;

/**
 * SOL1.pure recursion
 */
class SolLIS1 {
    public int lengthOfLIS(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int longest = 1;
        for (int i = 1; i < nums.length; i++) {
            longest = Math.max(longest, pureRecursion(nums, i)); //以每个点为起点组dfs，unconnected graph
        }
        return longest;
    }

    private int pureRecursion(int[] nums, int idx) {
        //base case: init len即可
        int curLen = 1;
        //induction rule
        for (int i = idx - 1; i >= 0; i--) {
            if (nums[idx] > nums[i]) {
                curLen = Math.max(curLen, pureRecursion(nums, i) + 1);
            }
        }
        //return val
        return curLen;
    }
}

/**
 * Sol2. pure recursion + memo
 */
class SolLIS2{
    public int lengthOfLIS(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int longest = 1;
        int[] mem = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            longest = Math.max(longest, pureRecursion(nums, i, mem));
        }
        return longest;
    }

    private int pureRecursion(int[] nums, int idx, int[] mem) {
        //base case: check mem
        if (mem[idx] != 0) {
            return mem[idx];
        }
        int curLen = 1;
        //induction rule
        for (int i = idx - 1; i >= 0; i--) {
            if (nums[idx] > nums[i]) {
                curLen = Math.max(curLen, pureRecursion(nums, i, mem) + 1);
            }
        }
        mem[idx] = curLen;
        //return val
        return curLen;
    }
}





/**
 * Sol3. DP
 * 注意：这里如果写
 * if (nums[j] < nums[i]) {
 *     dp[i] = Math.max(dp[i], dp[j] + 1);
 *     longest = Math.max(longest, dp[i]);
 * } else {
 *     dp[i] = 1;
 * }
 * 这样的逻辑是不对的，因为dp[i]会被更新多次，当第2， 3次更新的时候，
 * 当前dp[i]如果没有之前长，应该保留之前的长度，不能再更细成1
 */

/**
dp[i] : the longest increasing subsequence from index 0 to index i
induction rule:
   each index j from 0 to i, if (nums[i] > nums[j])
    dp[i] = Math.max(dp[j] + 1, dp[i]);
base case:
    dp[i] = 1  （所有位置的base case都是1）
res:
    max(dp[i])
*/
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int longest = 1;
        //dp ds
        int[] dp = new int[nums.length];
        //base case
        Arrays.fill(dp, 1);
        //induction rule
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    longest = Math.max(longest, dp[i]);
                }
            }
        }
        return longest;
    }
}

//或者写成
class Solution {
    public int lengthOfLIS2b(int[] nums) {//sanity check
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
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    longest = Math.max(longest, dp[i]);
                }
            }
        }
        return longest;
    }
}

/**
 * Sol4. DP recover any one path
 */
class SolLIS4 {
    public List<Integer> lengthOfLIS(int[] nums) {//sanity check
        int longest = 1;
        //dp ds
        int[] dp = new int[nums.length];
        int[] prev = new int[nums.length];
        Arrays.fill(prev, -1);
        int endIdx = -1;
        //base case
        dp[0] = 1;
        //induction rule
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    longest = Math.max(longest, dp[i]);
                    if (dp[j] + 1 > dp[i]) {
                        prev[i] = j;
                        endIdx = j;
                    }
                }
            }
        }
        //倒着recover path
        List<Integer> res = new ArrayList<>();
        for (int jump = 0; jump < dp[endIdx]; jump++) {  //dp[endIdx]就是这里要跳跃的次数
            res.add(nums[endIdx]);  //加入path的是原输入中的值 / idx，根据题目要求来写
            endIdx = prev[endIdx];
        }
        Collections.reverse(res);
        return res;
    }
}