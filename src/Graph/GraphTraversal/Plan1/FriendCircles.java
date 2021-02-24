package Graph.GraphTraversal.Plan1;

/**
 * 本质：一维graph上的Number of islands
 */

/**
 * Traverse vertex, acylic --> DFS1
 * graph node: 编号 [0, M.length - 1],共M.length个node
 * --》本质：找到这些node中，有多少个connected area即可
 *
 * need V ds？
 * 虽然一维数据，但是遍历方向未知（根据input所包含的graph信息决定,所以需要一维V ds）
 */
public class FriendCircles {
    public int findCircleNum(int[][] M) {
        //sanity check
        if (M == null || M.length == 0) {
            return 0;
        }
        int count = 0;
        boolean[] visited = new boolean[M.length];
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                count++;
                dfs(M, i, visited);
            }
        }
        return count;
    }

    private void dfs(int[][] M, int i, boolean[] visited) {
        //base case
        if (i < 0 || i >= M.length) {
            return;
        }
        if (visited[i]) {
            return;
        }
        //mark visited
        visited[i] = true;
        //recursion
        for (int j = 0; j < M[0].length; j++) {
            if (M[j][i] == 1) {   //检查那一行的每一列，说明是1两个node有双向edge，是friend
                dfs(M, j, visited);
            }
        }
    }
}
