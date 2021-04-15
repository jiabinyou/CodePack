package DP.TrainingPlan;

public class DungeonGame {
}
/**
 实质：也是shortest path问题，但是这里有两个特点：
 1.有权重，每条edge权重不同
 2.权重有负值
 所有这里dikistra算法不再适用

 1.BF: DFS
 可以反着从终点出发，找到所有path，最后到达start point时候，取得到的生命值最低的方法

 2.优化：其实对每一个位置到终点，他需要的最低生命值应该是固定的，这就意味着可以使用memorization
 去给recursion过程做优化
 又因为这里的方向只有两个，也就是说在我们的path中是不可能存在circle的，可以尝试使用DP的方法，尝试去找她的induction rule，如果能够找到，就说明可以用DP解决这个问题。

 definition:
 from (i, j) to (m - 1,n - 1), minimum initial health needed
 (0 <= i <= m - 1, 0 <= j <= n - 1)

 induction rule:
 standing at matrix[i][j], we can go either downward or rightward,
 we try to choose the one with minimum initial health
 i.e. dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) - matrix[i][j];
 Attention, （因为血值《=0都算是死掉了，所以至少>0,即至少要为1）
 to ensure at any point health point > 0, dp[i][j + 1] >= 1
 i.e. dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - matrix[i][j]);

 res: dp[0][0]

 init :
 会发现，如果按照现在的定义，沿着边界的地方，我们依然需要一个一个位置去判断，所以off by 1：
 We add dummy row to the bottommost and dummy column to the rightmost，
 so that the border position will not influence the inner position any more
 那么上面definition的定义就从index变成了长度length，并且init state为：
 for (int i = 0; i < dp.length; i++) {  //最后一列，可直接分配一定不可能取到的值
 dp[i][dp[0].length - 1] = Integer.MAX_VALUE;
 }
 for (int i = 0; i < dp[0].length; i++) { //最后一行
 dp[dp.length - 1][i] = Integer.MAX_VALUE;
 }

 fiiling order:
 row：right -> left
 col: botton -> up
 */
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length + 1][dungeon[0].length + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][dp[0].length - 1] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < dp[0].length; i++) {
            dp[dp.length - 1][i] = Integer.MAX_VALUE;
        }
        for (int i = dp.length - 2; i >= 0; i--) {
            for (int j = dp[0].length - 2; j >= 0; j--) {
                if (i == dp.length - 2 && j == dp[0].length - 2) {
                    dp[i][j] = -dungeon[i][j] <= 0 ? 1 : -dungeon[i][j] + 1;
                    continue;
                }
                dp[i][j] = Math.max(1, Math.min(dp[i + 1][j], dp[i][j + 1]) - dungeon[i][j]);
            }
        }
        return dp[0][0];
    }
}