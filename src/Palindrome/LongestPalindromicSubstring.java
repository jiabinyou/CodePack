package Palindrome;

/**解法：DP*/
/**因为这道题求的是substring，是连续的，所以我们只需要记录下glbLongest的start idx即可恢复path，不需要用到DP recover path*/
/** boolean dp[i][j] :Substring within arr[i, j] is panlindrome or not
 *
 * induction rule:
 * if (A[i] == A[j] && dp[i + 1][j - 1])         dp[i][j] = true
 *
 * base case:
 * if i == j  --> dp[0][0] = 0;
 * if j - i = 1  --> dp[i][j] = A[i] == A[j]
 *
 * res: 这道题要返回path，所以记录glbLongest和startIdx即可
 *
 * filling order:
 * (i)从右到左，(j)从上到下
 */


public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        // sanity check
        if (s == null || s.length() == 0) {
            return " ";
        }
        int len = s.length();
        int glbLongest = 0;
        int startIdx = 0;

        boolean[][] dp = new boolean[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                //base case
                if (i == j) {
                    dp[i][j] = true;
                } else if (j - i == 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) { //induction rule
                    dp[i][j] = true;
                }
                //check res
                if (dp[i][j] && j - i + 1 > glbLongest) {
                    glbLongest = j - i + 1;
                    startIdx = i;
                }
            }
        }
        return s.substring(startIdx, startIdx + glbLongest);
    }
}
