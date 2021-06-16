package DP.XIAOBAN.ClassOne;

import java.util.Arrays;

/**
 * 给了我们一个NxN的数组，表示城市i是否有飞机直达城市j，
 * 又给了我们一个NxK的数组days，表示在第j周能在城市i休假的天数，
 * 让我们找出一个行程能使我们休假的天数最大化
 * 解法：DP
 *
 * dp[i]:在当前周，并且在此时在城市i，总共已经获得休假的总日子数  （本来week应该是DP中一个维度，但是week没有depend，所以可以省略此维度）

 * induction rule:
 *  k = ds[0].length;  //k -- 共有多少周
 * int n = flights.length;   //n - # of cities
 * 从第一周往后遍历，先找到当前这一周能拿到的最大vacation days：
 * 遍历each pair of cities (i, j)，查看是否有flight能够直达，
 *
 * if (i == p || flights[p][i]) {
 *       //如果是当前周，或者有飞机从城市i到j，the updated days info of dp[i] should be dp[p] + days[i][j]
 *       temp[i] = max(t[i], dp[p] + days[i][j]);
 * }
 *
 * base case:
 *  dp[0] = 0;
 *
 *  Time complexity : O(n^2*k) ,
 *                    n represents the number of cities and k is the total number of weeks.

    Space complexity : O(n)
 * */
public class MaximumVacationDays {
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = flights.length;
        int k = days[0].length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        int max = 0;
        for (int j = 0; j < k; j++) {  //遍历每一周
            int[] tmp = new int[n];
            Arrays.fill(tmp, Integer.MIN_VALUE);
            for (int i = 0; i < n; ++i) {
                for (int p = 0; p < n; p++) {
                    if (p == i || flights[p][i] == 1) {
                        tmp[i] = Math.max(tmp[i], days[i][j] + dp[p]);
                    }
                }
            }
            dp = tmp;  //将t整个赋值给dp，然后进行下一周的更新
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
