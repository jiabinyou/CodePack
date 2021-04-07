package Graph.GraphTraversal.Plan4;

import java.util.LinkedList;
import java.util.Queue;

public class FriendCircles {
    /** Sol。unconnected graph, DFS1, mark visit boolean[]
     本质：find number of all ’1‘ island变形,但更简单，input是adjacent matrix graph，
     直接检查一行/一列即包括所有node。
     e.g.😍按一行检查所有node，node还没被visit过,说明是一个新的circle。
     此时以node作为row，以此为起点，用dfs遍历与这个node的所有1的nei
     （注意：这里遍历一行/一列都可以，等价的，只是我们要检查的坐标是以node为row idx，nei为col idx），
     并且mark visited

     note:adjacent matrix graph，不建议在matrix上直接mark visited，因为
     adjacent matrix graph每个位置都掺杂了两个node的信息，并非一个单独的graph node
     直接mark visit语义不明

     */
    public int findCircleNum(int[][] M) {
        // sanity check
        if (M == null || M.length == 0 || M[0].length == 0) {
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

    private void dfs(int[][] M, int node, boolean[] visited) { //此时将j作为r idx来检查，因为是查node y
        //base case
        if (visited[node]) {
            return;
        }
        visited[node] = true;; //mark visit at expansion
        for (int j = 0; j < M[0].length; j++) {
            if (M[node][j] == 1) {
                dfs(M, j, visited);
            }
        }
    }
}

/**
 * SOl2. BFS
 * */
class FriendCirclessOL2 {
    public int findCircleNum(int[][] M) {
        // Write your code here
        int n = M.length;
        int ans = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                ans += 1;
                visited[i] = true;

                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);

                while (!queue.isEmpty()) {
                    int now = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (visited[j] == false && M[now][j] == 1) {
                            visited[j] = true;
                            queue.add(j);
                        }
                    }
                }
            }
        }
        return ans;
    }
}
