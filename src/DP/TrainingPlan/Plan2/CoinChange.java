package DP.TrainingPlan.Plan2;

/**
 definition：
 dp[i][j] = the min # of coins used to make amount j using coin[0....i]
 --> 即使用前i个coins，组成amount = j，需要用到的最少的钱的个数是多少(!!!前i个，而不是idx i，off by 1的定义)
 ​
 induction rule：
 case 1： coins[i-1]太大了(>j)，没法凑j
 --> dp[i][j] = dp[i - 1][j]
 case 2:  还不够amount j, 可能需要用到coins[i - 1]
 （这个递推公式的关键是，追溯回到上一个状态，其实也可能使用的是coins[i]）
 --> 1 + dp[i][j - coins[i]]
 ​
 filling order：
 i depend on i - 1,        --> i从左往右填写   [0, coins.length - 1]
 j depend on j - coins[i], --> j从左往右填写   [coins[i], amount]     (因为凡是小于coins[i]的都是无法work的)
 ​
 base case:
 dp[i][0] = 0; (不用写出来)
 dp[0][j] = Integer.MAX_VALUE;

 result:
 dp[coins.length][amount]

 TC:O(N^2)
 SC:O(N^2)

 过例子：
 Input: coins = [1,2,5], amount = 7
idx     0    1    2    3    i     j     dp[i][j]
             1    2    5    1     1         1
                            1     2         2
                            1     3         3
                            1     4         4
                            1     5         5
                            1     6         6
                            1     7         7
                            2     1         1
                                  2         1
                                  3




 dp[][]
       0   max   max   max   max  max  max  max
       0   1      2     3     4    5    6    7
       0   1      1
       0

 */

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        //base case
        for (int j = 1; j <= amount; j++) {
            dp[0][j] = Integer.MAX_VALUE;
        }
        //induction rule
        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= amount; j++) {
                if (coins[i - 1] > j || dp[i][j - coins[i - 1]] == Integer.MAX_VALUE) {  // coins[i-1]太大了(>j)，没法凑j
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1); //、
                }
            }
        }
        return dp[len][amount] == Integer.MAX_VALUE ? - 1 : dp[len][amount];
    }

    public static void main(String[] args) {
        CoinChange obj = new CoinChange();
        int[] coins = new int[]{1, 2, 5};
        int amount = 7;
        System.out.println(obj.coinChange(coins, amount));
    }
}
