package DP.TrainingPlan.Plan2;

/**解法：DP
 */

import java.util.HashMap;
import java.util.Map;

/**
 dp[i][j]:在index = j处的数字，可由第i个数以及数组内i之前的另一个数相加求得(i < j， preIdx < i),
 则[i,j]内的最长子串的长度（如果不行，值为0）

 induction rule:
 难点：使用map <val, idx>检查是否有A[x],使得 A[x] + A[i] == A[j]成立
 （j为cur idx）
 如果存在val：map.containsKey(A[j] - A[i]),并且它的preIdx = map.get(A[j] - A[i]) < i,
 说明j所在数值可以由idx i， j之前某一位置数值组成fibonaci
 如果dp[preIdx][i] == 0,说明j这个位置刚开始有fibonacci，则dp[i][j] == 3;
 否则，dp[i][j] == dp[preIdx][i] + 1;

 base case:
 dp[0][0,1] = 0; (此时无论这样组合只有两个数，其实没有base case，就是默认值0 )

 Res:
 glbLongest = max(dp[i][j])
 2 <= j  < A.length
 1 <= i  < j    (因为定义i < j)

 填表顺序：row：i  col：j
 [i][j] depend on [preIdx][i], preIdx < j, i < j
 P--prev, c--cur
 p  p  p  p
 p  p  c  x
 x  x  x  x
 所以：row：l - 》r， col: t --> b
 */
public class LengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] arr) {
        // sanity check
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int[][] dp = new int[n][n];

        //prepare map<val, idx>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }

        //Dp
        int glbLongest = 0;
        for (int j = 2; j < n; j++) {
            for (int i = 1; i < j; i++) {
                if (map.containsKey(arr[j] - arr[i])) {
                    int preIdx = map.get(arr[j] - arr[i]);
                    if (preIdx < i) {
                        if (dp[preIdx][i] == 0) {
                            dp[i][j] = 3;
                            System.out.println(dp[i][j]);
                        } else {
                            dp[i][j] = 1 + dp[preIdx][i];
                        }
                        glbLongest = Math.max(glbLongest, dp[i][j]);
                    }
                }
            }
        }
        return glbLongest;
    }
}



