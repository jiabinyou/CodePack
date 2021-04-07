package DP.TrainingPlan.Plan2;

/**
 int[i][j]: the #paths from[0][0] to [i][j]
 //base case
 [0][0]第一列只能往下走，所以一种
 [0][0]到第一行只能往右走，所以一种
 //induction rule
 站在[i][j],上一步可以来自于left或者top方向
 即
 dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
 //order
 只能t->b， l->r
 //Res
 return dp[m - 1][n - 1];
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        // sanity check
        if (m == 0 || n == 0) {
            return 0;
        }
        //Dp
        int[][] dp = new int[m][n];
        //base case
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;  //第一列只能往下走到目标点，所以一种
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;  //最后一行只能往右走到目标点，所以一种
        }
        //induction rule
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        //Res
        return dp[m - 1][n - 1];
    }
}

/**简化空间：
 * 因为列方向只看同一列上面的元素，（所以列方向用新元素覆盖自己就足够，不需要单独记录）
 * 所以可以将空间优化到一行，行方向left to right填表即可
 * */
class UniquePathsSol2 {
    public int uniquePaths(int m, int n) {
        //sanity check
        if (m == 0 || n == 0) {
            return 0;
        }
        //dp
        int[] dp = new int[m];
        //base case
        for (int i = 0; i < m; i++) {
            dp[i] = 1;
        }
        //induction
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j] = dp[j] + dp[j-1];
            }
        }
        //res
        return dp[m - 1];
    }
}

