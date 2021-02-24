package Graph.GraphTraversal.Plan1;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
/**
 题目解释：
 graph[i]这一行中的每个元素graph[i][j]表示：有一条i指向j的edge
 */

/**
 * DFS1
 * #DFS: 因为是Traverse Vertex， 并且无环，使用DFS1足够
 *
 mark visited:
 这里的维度是0 - N-1的node，虽然是一维的数据，但是实际上没有办法按照顺序依次遍历每个node，（实质上仍然是二维信息：N个node，加上遍历方向。
 所以相当于我们要对这N个node进行mark visited，防止重复遍历
 */

public class NumberNumberOfConnectedComponentsInAnUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        //sanity check
        if (n == 0) {
            return 0;
        }
        /**build graph*/
        List<List<Integer>> map = buildMap(n, edges);
        boolean[] visited = new boolean[n];
        /**DFS1V, V at expansion*/
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(i, map, visited);
            }
        }
        return count;
    }

    private void dfs(int cur, List<List<Integer>> map, boolean[] visited) {
        //base case
        if (visited[cur]) {
            return;
        }
        //mark visited, at expansion
        visited[cur] = true;
        //recursion
        for (int nei : map.get(cur)) {
            dfs(nei, map, visited);
        }
    }

    private List<List<Integer>> buildMap(int n, int[][] edges) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        return map;
    }
}

/**
 * 疑问：尝试写了DFS1V ， V at generation，但是没写出来，无法设置base case
 */

/**BFSV:
 init root
 V at expansion
 */
class SolNNOC2 {
    public int countComponents(int n, int[][] edges) {
        //sanity check
        if (n == 0) {
            return 0;
        }
        /**build graph*/
        List<List<Integer>> map = buildMap(n, edges);
        boolean[] visited = new boolean[n];
        /**unconnected BFS*/
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                bfs(i, map, visited);
            }
        }
        return count;
    }

    /**BFSV:
     init root
     V at expansion
     */
    private void bfs(int node, List<List<Integer>> map, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);   /**init root*/
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            visited[cur] = true; /**V at expansion */
            for (Integer nei : map.get(cur)) {
                if (!visited[nei]) {
                    queue.offer(nei);
                }
            }
        }
    }

    private List<List<Integer>> buildMap(int n, int[][] edges) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        return map;
    }
}




/**BFSV: -->这种方法最快
 init root
 V at generation -->单独mark visited root
 */
class SolNNOC2b {
    public int countComponents(int n, int[][] edges) {
        //sanity check
        if (n == 0) {
            return 0;
        }
        /**build graph*/
        List<List<Integer>> map = buildMap(n, edges);
        boolean[] visited = new boolean[n];
        /**DFS1V, V at generation*/
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                visited[i] = true;  //单独mark visited root
                bfs(i, map, visited);
            }
        }
        return count;
    }

    /**BFSV:
     init root
     V at generation
     */
    private void bfs(int node, List<List<Integer>> map, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(node);   /**init root*/
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            for (Integer nei : map.get(cur)) {
                if (!visited[nei]) {
                    visited[nei] = true; /**V at egeneration */
                    queue.offer(nei);
                }
            }
        }
    }

    private List<List<Integer>> buildMap(int n, int[][] edges) {
        List<List<Integer>> map = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        return map;
    }
}



