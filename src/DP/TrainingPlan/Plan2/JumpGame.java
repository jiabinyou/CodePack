package DP.TrainingPlan.Plan2;

import java.util.LinkedList;
import java.util.Queue;

/**Sol1.BFS1
 init point: index 0
 goal point: index A.length - 1

 TC: O(V + E) = O(N), N -> input length
 SC: O(N)
 */
public class JumpGame {
    public boolean canJump(int[] A) {
        // sanity check
        if (A == null || A.length <= 1) {
            return true;
        }
        Queue<int[]> q = new LinkedList<>(); //int[]{idx, val}
        q.offer(new int[]{0, A[0]});
        while (!q.isEmpty()) {
            //expansion
            int[] cur = q.poll();
            if (cur[0] >= A.length - 1) {  //check res
                return true;
            }
            //generation
            for (int i = 1; i <= cur[1]; i++) {
                if (cur[1] == 0) {  /**易错点：这里检查某位=0不能直接return false，因为别的path可能还能到终点*/
                    continue;
                }
                int newIdx = cur[0] + i;
                if (newIdx < A.length) {
                    int[] nei = new int[]{newIdx, A[newIdx]};
                    q.offer(nei);
                }
            }
        }
        return false;
    }
}

/**Sol2. DP
 booean[] dp : dp[i] --> 从inex = i出发，是否能够跳到终点

 inductin rule:
 case 1:    i + nums[i] >= nums.length - 1      -->    dp[i] = true
 case 2:    step表示站在i位置能跳的步数,   dp[i + step] = true  --> dp[i] = true
 res: dp[0]
 base case:    dd[nums.length - 1] = true
 filling order : 只能从右到左
 * */
/**
 * TC: O(N^2)  -->时间复杂度反而高
 * SC:O(N)
 * */
class JumpGameSol2 {
    public boolean canJump(int[] A) {
        // sanity check
        if (A == null || A.length <= 1) {
            return true;
        }
        boolean[] dp = new boolean[A.length];
        //base case
        dp[A.length - 1] = true;
        //induction rule
        for (int i = A.length - 2; i >= 0; i--) {  //i-> cur idx
            int totalStep = A[i];  //如果A[i] = 0，压根进入不了下面for循环，不用处理此情况
            for (int step = 1; step <= totalStep; step++) {
                int nextIdx = i + step;
                if (nextIdx >= A.length - 1 || dp[nextIdx]) {  //case: nextIdx >= A.length - 1其实就是step = totalStep情况，所以是可省略不写的
                    dp[i] = true;
                }
            }
        }
        return dp[0];
    }
}

/**这一题最优的解法其实是greedy
 * 依次遍历每个位置i， 利用greedy思想实时维护最远可达的位置rightmost
 * 如果i在rightmost范围内，说明i可达，我们将rightmost更新为max(rightmost, i + A[i])
 * 如果rightmost大于等于数组中的最后一个位置，那就说明最后一个位置可达，我们就可以直接返回True。
 * 如果在遍历结束后，最后一个位置仍然不可达，我们就返回 False 。
 *
 * 时间复杂度：O(n)，n为数组长度。一次遍历。
 * 空间复杂度：O(1)。
 * */
class JumpGameSol3 {
    public boolean canJump(int[] A) {
        // sanity check
        if (A == null || A.length <= 1) {
            return true;
        }
        int n = A.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= rightmost) {
                /**关键：index = i + A[i]是站在每个index上最远能走到的地方，而rightMost就是所有i + A[i]中最大的那个，
                 所有i + [0, A[i] - 1]就可以直接丢弃不计算了，一下少了O(N)的时间复杂度
                 */
                rightmost = Math.max(rightmost, i + A[i]);
                if (rightmost >= n - 1) { //一旦发现rightMost比input长度大，就可返回true了
                    return true;
                }
            }
        }
        return false;
    }
}





