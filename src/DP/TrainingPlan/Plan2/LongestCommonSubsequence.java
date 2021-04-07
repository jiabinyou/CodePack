package DP.TrainingPlan.Plan2;

/**解法：DP
 definition：dp[i][j]: A的前i个元素，B的前j个元素，LCS(longest common subsequence)长度

 induction rule:
 if (A[i] == B[j]) dp[i][j] = dp[i-1][j-1] + 1
 else              dp[i][j] = dp[i][j]=max(dp[i-1][j],dp[i][j-1],dp[i-1][j-1])

 base case: 需要off by 1， 不然DP[0][0]需要判断，所以定义中为”前i个，前j个“
 dp[0][0] = 0;
 dp[0][j] = 0;
 dp[i][0] = 0;

 res:
 dp[A.length][B.length]

 filling order:(row -> i, col->j)
 dp[i][j] depend on dp[i-1][j-1]，p[i-1][j],dp[i][j-1]
 row： 从左向右
 col: 从上到下

 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String A, String B) {
        // sanity check
        if (A == null || A.length() == 0 || B == null || B.length() == 0) {
            return 0;
        }
        int m = A.length();
        int n = B.length();
        int[][] dp = new int[m + 1][n + 1];
        //base case
        for (int i = 0; i < m ; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 0;
        }
        //induction rule
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {  /**off by 1, i,j表示长度*/
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(Math.max(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
