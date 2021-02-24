package Graph.GraphTraversal.Plan2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Sol1.travers vertex, may cylic --> 可DFS1
 * 题目本质：flood fill
 * 依然是检测连通区域，但是这次还有一个要求，就是互为nei必须能够分区，我们可以将nei染成不同颜色
 * 一旦发现互为nei（即connected），但是染色是相同的，那么说明不是partitie
 *
 * 难点一：
 * need V ds？
 * 需要，因为虽然是0 到 graph.length - 1 个node的一维graph，但是比那里方向未知，由input提供
 * 并且，这里需要给nei染两种颜色，所以boolean[]不再够，我们可以使用int[] visited携带更多信息
 * 0: unvisited, 1:visited, red, -1:visited, black
 */
public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        //sanity check
        if (graph == null || graph.length == 0) { /**列信息如果是0，可能是true的，在工业上这个检查无意义*/
            return false;
        }
        int[] visited = new int[graph.length]; //0: unvisited, 1:visited, red, -1:visited, black
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0 && !dfs(graph, i, 1, visited)) {  /**第一次遇到的都染色为1，如果不成立，说明不是partite*/
                return false;  //一个是false就要返回false
            }
        }
        return true;
    }

    //color: 当前node i应该被染的颜色
    private boolean dfs(int[][] graph, int node, int color, int[] visited) {
        //base case
        if (visited[node] != 0) {
            return visited[node] == color;
        }
        //mark visited
        visited[node] = color; /**mark visit at expansion*/
        //recursion
        for (int nei : graph[node]) {
            if (!dfs(graph, nei, -color, visited)) {
                return false;
            }
        }
        return true;
    }
}


/**
 * Sol2. BFS
 */
class SolIGB2 {
    public boolean isBipartite(int[][] graph) {
        //sanity check
        if (graph == null || graph.length == 0) { /**列信息如果是0，可能是true的，在工业上这个检查无意义*/
            return false;
        }
        int[] visited = new int[graph.length]; //0: unvisited, 1:visited, red, -1:visited, black
        for (int i = 0; i < graph.length; i++) {
            if (visited[i] == 0 && !bfs(graph, i, visited)) {
                return false;  //一个是false就要返回false
            }
        }
        return true;
    }

    //color: 当前node i应该被染的颜色
    private boolean bfs(int[][] graph, int node, int[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);   //init root
        visited[node] = 1;   //单独mark visited root
        while (!queue.isEmpty()) {
            //expansion
            int cur = queue.poll();
            int color = visited[cur];
            //generation
            for (int nei : graph[cur]) {
                if (visited[nei] == color) {
                    return false;
                }
                if (visited[nei] == 0) {
                    queue.offer(nei);
                    visited[nei] = -color;    //mark visited at generation
                }
            }
        }
        return true;
    }
}
