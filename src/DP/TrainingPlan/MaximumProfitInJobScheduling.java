package DP.TrainingPlan;

/**
 * Sol:DP + binary search/使用treeMap
 * 这种interval加上求极值的问题，大多数都是DP的解法
 * 定义上有两种方式：
 * 1.dp[i] : 表示到end time i为止，能够拿到的max profit，并且end at i这一段的必须是work的
 *   那么按照这个definition，induction rule应该是：
 *   dp[i] = max(dp[j] + val[i]),
 *   （i是end time，假设i所在的那一段interval的start time是startI，
 *    那么j是所有end time从0到largestSmallerThan startI的interval，线性用O(N)的时间计算一遍。
 *    即j代表所有在i结尾的interval之前，与该interval没有overlap的，都要计算一遍）
 * 2.dp[i] : 表示到end time i为止，能够拿到的max profit，并且end at i这一段不一定work
 *   那么按照这个definition，induction rule应该是：
 *   dp[i] = max(dp[j] + val[i] , dp[i - 1])
 *   dp[j] + val[i] 指的是i结尾的interval work
 *    dp[i- 1] 指的是i结尾的interval 不work
 *
 *   （i是end time，假设i所在的那一段interval的start time是startI，
 *  *    那么j是end time largestSmallerThan startI的那一个interval）
 *   -----j
 *     -------
 *           ---------
 *         startI     i
 *   【难点1：为什么只要计算j这一个就够了？因为往前面数叠加，profit只会越来越大，所以最后一个j出的dp累计值一定是最大的，所以计算一个即可】
 *   --》所以显然定义2是更好的
 *   【难点2：怎样快速找到j？
 *     方法一：可以以i所在的interval的start time，即startI为基准，使用binary search o(logn)找到largestSmallerThan startI的那个end time
 *     方法二：使用treeMap，将end time作为key，o(logn)找到largestSmallerThan startI的那个end time
 *     】
 * */

import java.util.Arrays;
import java.util.Comparator;

/**
 * 题目逻辑解释：
 * // dp approach + binary search
 * // sort the jobs by endTime
 * // for job i, consider two scenarios: 1)  job i is used; 2) job i is not used
 * // dp[i] = max(dp[i - 1], dp[j] + profit[i]) where j is the largest index of the job not overlapping with job i
 * // to search for j efficiently, use binary search to reduce time complexity
 *
 * TC:O(nlogn)
 * SC:O(N）
 * */
public class MaximumProfitInJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3]; //每行表示的是[startTime, endTime, profit]

        for (int i = 0; i < n; i++) {
            jobs[i][0] = startTime[i];
            jobs[i][1] = endTime[i];
            jobs[i][2] = profit[i];
        }
        Arrays.sort(jobs, new jobComparator()); //按照endtime排序

        int res = 0;
        int[] dp = new int[n];
        dp[0] = jobs[0][2];
        for (int i = 1; i < n; i++) {
            int left = 0;
            int right = i - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (jobs[mid][1] > jobs[i][0]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // left is the index of the 1st job that overlaps with current job i, so the actual index we need is (left - 1)
            // if (left - 1 < 0), it means such a valid job does not exist
            if (left - 1 < 0) {
                dp[i] = Math.max(dp[i - 1], jobs[i][2]);
            } else {
                dp[i] = Math.max(dp[i - 1], jobs[i][2] + dp[left - 1]);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    class jobComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b) {
            return a[1] - b[1];
        }
    }
}
