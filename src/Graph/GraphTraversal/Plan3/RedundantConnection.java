package Graph.GraphTraversal.Plan3;

import java.util.ArrayList;
import java.util.List;

/**
 * DFS1
 * 最有效的解法：一边做dfs一边检查
 * 在"已经建好graph"上面跑DFS1,检查建好的graph上面，是否已经有一条edge能够从edges[i][0] (起点start)到edges[i][1] （终点end）
 * 如果这条edge已经存在，说明当前正在检查的edge是在环上的，直接返回即可
 */
public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        //sanity check
        if (edges == null || edges.length == 0) {
            return new int[0];
        }
        /**build graph*/
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            if (dfs(graph, edge[0], edge[1], 0)) { /**check if there is edge between u & v already*/
                return edge;
            } else {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
        }
        return new int[0];
    }

    private boolean dfs(List<List<Integer>> graph, int start, int end, int prev) {
        /**base case:难点！！when u & v at the same pos*/
        if (start == end) {
            return true;
        }
        //recursion
        for (int nei : graph.get(start)) {
            if (nei != prev && dfs(graph, nei, end, start)) {
                return true;
            }
        }
        return false;
    }
}
