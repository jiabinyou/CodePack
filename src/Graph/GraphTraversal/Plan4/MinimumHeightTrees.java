package Graph.GraphTraversal.Plan4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Sol: BFS TOPO (queue版本)
 * 这道题的本质是按照topo logical order，使用bfs进行layer遍历，最后一层的node即为最终的答案
 * 原因：
 * Basically code starts from the leaf nodes.
 * For leaf nodes, their degree = 1, which means each of them is only connected to one node.
 * In our loop, each time we delete the leaf nodes from our graph(just by putting their degrees to 0),
 * and meanwhile we add the new leaf nodes after deleting them(just add their connected nodes with degree as 2) to the queue.
 * So basically in the end, the nodes in the queue would be connected to no other nodes but each other. They should be the answer.
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (n < 1) {
            return res;
        }
        if (n == 1) {
            res.add(0);
            return res;
        }
        /**build graph for BFS topo*/
        int[] indegree = new int[n];
        List<List<Integer>> graph = buildGraph(n, edges, indegree);

        /**BFS TOPO: queue版本*/
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            /**pruning: 发现indgree = 0, 即说明全部遍历完成了，indgree走到i位置为止全部拆完，返回即可*/
            if (indegree[i] == 0) {
                return res;
            }
            /**init root: 所有indgree为1的root，作为bfs topo 起始root*/
            if (indegree[i] == 1) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            res = new ArrayList<Integer>(); /**每一次后面一层的res，都会覆盖掉前面一层的，直到res装的是最后一层的node*/
            int size = queue.size();
            for(int i = 0; i < size; i++){
                //expansion
                int curr = queue.poll();
                res.add(curr);  /**process at expansion*/
                indegree[curr]--;
                //generation
                for(int j = 0; j < graph.get(curr).size(); j++) {
                    int nei = graph.get(curr).get(j);
                    if (indegree[nei] == 0) {
                        continue;
                    }
                    indegree[nei]--;
                    if (indegree[nei] == 1) {
                        queue.offer(nei);
                    }
                }
            }
        }
        return res;
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges, int[] indegree) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }
        return graph;
    }
}

/**
 * Sol: BFS TOPO (level list版本)
 */
class SolMHT1b {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }
        /**build graph for BFS topo*/
        int[] indegree = new int[n];
        List<List<Integer>> graph = buildGraph(n, edges, indegree);

        /**BFS TOPO: level list版本*/
        List<Integer> cur = new ArrayList<>(); /**bfs cur layer list*/
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            //init root
            if (indegree[i] == 1) {
                cur.add(i);
                visited[i] = true;
            }
            if (indegree[i] == 0) {
                cur.add(i);
                return cur;
            }
        }

        while (!cur.isEmpty()) {
            List<Integer> next = new ArrayList<>();  /**bfs next layer list*/
            int size = cur.size();
            for (int i = 0; i < size; i++) {
                //expansion
                int curNode = cur.get(i);
                //generation
                for (int nei : graph.get(curNode)) {
                    if (!visited[nei]) {
                        indegree[nei]--;
                        if (indegree[nei] == 1) {
                            next.add(nei);
                        }
                    }
                }
            }
            if (next.isEmpty()) {
                return cur;
            }
            cur = next;
        }
        return cur;
    }

    private List<List<Integer>> buildGraph(int n, int[][] edges, int[] indegree) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }
        return graph;
    }
}
