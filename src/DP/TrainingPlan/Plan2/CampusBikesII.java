package DP.TrainingPlan.Plan2;
import java.util.*;
/**Sol1.Backtracking + pruning
 * 直接使用BackTracking求出所有path sum，最后取出全局最小的即为答案
 *
 * ->优化
 * Sol2.Backtracking + memo
 * 使用return value优化backtraking， 返回最小的距离和
 * 用一个数state表示已经选取了的bike的组合。这样memo[state]就表示在当前选取的自行车组合下，之后能得到的最小和
 *使用一个bikeVisited来判断已经有多少bikes被分配了
 *
 * -》优化
 *Sol3.pure recursion
 *可以理解成dfs找到bike最小path sum分配方法，找到两辆bike有最小path sum分配方法。。。找到n辆bike有固定的最小path sum分配方法
 * 这样我们就可以从bottom 到 up构建出最终的solution
 *
 * -》优化
 * Sol4：pure recursion + memo
 * 因为一辆bike最小path sum分配方法，两辆bike最小path sum分配方法。。。n辆bike最小path sum分配方法  都是固定的方式（memo所记录下来）
 *
 * -》优化
 * Sol5：DP
 * 在Sol4基础上，就可以尝试优化成DP方法了
 * For ith worker, the min distance = ith worker uses jth bike + min distance all i - 1 workers to use i -1 others bike from m.
 * so this is dp problem .
 * */

/**最终Sol：DP解法
 *definition : dp[i][j] = the min distance for first i workers to build the state j ,
 * induction rule: dp[i][j] = min(dp[i][j], dp[i - 1][prev] + dis(w[i -1], bike[j)) | 0 < j < m, prev = s ^ (1 << j)
 * init:dp[0][0] = 0;
 * result: dp[n][s] s should have n bit
 * */




/**Sol1.BFS2
 * 这是一道典型的state来定义的graph题目，里面的每一个不同的state是一个graph node
 * 假设这里有m个worker，我们用一个int值，每一个bit代表一个work是否被分配自行车，0代表没有分配，1代表已分配，那么
 * init node：0000........000（m个0）
 * goal node：1111........111（m个1）
 * */

/**
 * <<左移运算符：i << j = i * 2^j （原本二进制左移j位，低位补0）
 *   e.g.2的二进制为：00000010 ，向左移动1位变成：00000100
 * >>右移运算符：i << j = i / 2^j  （原本二进制右移j位，高位补0）
 *   e.g.4的二进制为：00000100 ，向右移动2位变成：00000001
 *
 *   题目中: mask = 1 << j,相当于1左移j位，即原本000000....1,现在变成0000...1...00（从右往左数第j位是1）--》作为mask使用
 *          state：00101001001110..010(共m位，idx表示workerIdx，已经分配过自行车的workerIdx为1，没有分配的为0)
 *       -》所以，当且仅当mask & state == 1时候，此时state才转变成全1（11111....11），这时候才是所有worker都分配完，否则就要继续分配
 *
 *
 * */
public class CampusBikesII {
    class Node {
        int workerId;
        int state;
        int cost;
        Node(int workerId, int state, int cost) {
            this.workerId = workerId;
            this.state = state;
            this.cost = cost;
        }
    }

    public int assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length;
        int n = bikes.length;

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        minHeap.offer(new Node(0, 0, 0));
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            int id = cur.workerId;
            int state = cur.state;
            int cost = cur.cost;
            if (!visited.add(state)) {
                continue;
            }
            if (id == m) { //pruning:此时说明所有worker已经分配结束了
                res = cost;
                break;
            }
            for (int j = 0; j < n; j++) {
                if ((state & (1 << j)) == 0) { /**1右移j位，即m个0的int中，第j位*/
                    int newState = state | (1 << j);
                    int newCost = cost + getDistance(workers[id], bikes[j]);
                    minHeap.offer(new Node(id + 1, newState, newCost));
                }
            }
        }
        return res;
    }

    private int getDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}

/**Sol2.DP
 * 由"状态转化"的graph而来，所以DP也很特殊，第二维度代表的是state
 * dp[i][j] : 在为i个workers分配了自行车，并且当前状态是j的情况下，min path distance sum
 * （所以后面的prev，指的就是在转变成state j之前的一个状态）
 * */


