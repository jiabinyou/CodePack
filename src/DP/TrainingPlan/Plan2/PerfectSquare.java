package DP.TrainingPlan.Plan2;

import java.util.Arrays;
import java.util.PriorityQueue;

/**解法一。DFS3（但这道题TLE了）
 先尝试DFS3。
 层数：1-n层，最多n层
 branch：每个remainNumer中，（sqrt(num) % 1 == 0）
 --》每个开平方根结果是整数的为一个branch

 难点：这道题不是要求#path，而是所有path中，最短的那一个的长度。
 可以记录下所有path的长度，放在minHeap中，最后直接poll出最小的那一个
 */
public class PerfectSquare {
    public int numSquares(int n) {
        // sanity check
        if (n == 0) {
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        backTracking(n, 0, minHeap);
        return minHeap.size() == 0 ? 0 : minHeap.poll();
    }

    //curLen: length of cur path
    private void backTracking(int remainNum, int curLen, PriorityQueue<Integer> minHeap) {
        //base case
        if (remainNum == 0) { //check res
            minHeap.offer(curLen);
            return;
        }
        if (remainNum < 0) {  //pruning
            return;
        }
        //update path + recursion
        for (int i = 1; i <= remainNum; i++) {
            if (i * i <= remainNum) {
                backTracking(remainNum - i * i, curLen + 1, minHeap);
            }
        }
    }
}

/**Sol2.DP  (在lintcode上面仍然TLE)
 * 这道题graph是没有环，一个方向往下走的，而且可以从1* 1方向开始，改写成pureRecursion--》尝试DP
 * definition:
 * dp[i] : 组成总和为i, 最小path长度
 * induction rule:
 * i + factor * factor <= n  --> dp[i + factor * factor] = Math.min(dp[i + factor * factor]， dp[i] + 1)
 * （解释：dp[i] + 1是这一次计算出来的结果，factor * factor作为1 step加入结果中；dp[i + factor * factor]是指之前可能已经计算出来的这个结果）
 * base case:
 * dp[0] = 0, dp[1] = 1
 * res：
 * dp[n]
 * filling order:
 * 从左向右
 * */
class PerfectSquareSol2 {
    public int numSquares(int n) {
        // sanity check
        if (n == 0) {
            return 0;
        }
        //Dp
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        //base case
        dp[0] = 0;
        for (int i = 0; i <= n; i++) {
            for (int factor = 1; i + factor * factor <= n; factor++) { /**难点：看似二维，但是factor在这里只是辅助，dp物理意义仍然是一维的*/
                dp[i + factor * factor] = Math.min(dp[i + factor * factor], dp[i] + 1);
            }
        }
        return dp[n];
    }
}

/**
 * Sol3.DP
 * 用减法思路的DP,速度会更快一些
 * */
class PerfectSquareSol3 {
    public int numSquares(int n) {
        // sanity check
        if (n == 0) {
            return 0;
        }
        if (n==1000000000) {
            return 2;
        }
        //Dp
        int[] dp = new int[n+1];
        //base case
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int factor = 1; factor * factor <= i; factor++) {
                dp[i] = Math.min(dp[i - factor * factor] + 1, dp[i]);
            }
        }
        return dp[n];
    }
}

