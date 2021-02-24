package Graph.DFS3.Plan4;

/**
 题目解释：
 graph[i]这一行中的每个元素graph[i][j]表示：有一条i指向j的edge
 */

import java.util.ArrayList;
import java.util.List;

/**
 mark visited:
 这里的维度是0 - N-1的node，虽然是一维的数据，但是实际上没有办法按照顺序依次遍历每个node，（实质上仍然是二维信息：N个node，加上遍历方向。所以相当于我们要对这N个node进行mark visited，防止重复遍历

 backTracking on N node：
 起点：node 0
 终点：node N - 1
 node范围： the nodes are 0, 1, ..., graph.length - 1
 */
public class AllPathsFromSourceToTarget  {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (graph == null || graph.length == 0 || graph[0].length == 0) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        backTracking(0, graph, visited, path, res);
        return res;
    }

    private void backTracking(int curNode, int[][] graph, boolean[] visited, List<Integer> path, List<List<Integer>> res) {
        //base case
        if(visited[curNode]) {
            return;
        }
        //update cur path
        path.add(curNode);          /**path: exclude cur Node,所以要先更新path信息，才能得到当前层cur Path*/
        if (curNode == graph.length - 1) {       //check res
            res.add(new ArrayList<>(path));
        }
        //recursion
        visited[curNode] = true;
        for (int nei : graph[curNode]) {
            backTracking(nei, graph, visited, path, res);
        }
        path.remove(path.size() - 1);
        visited[curNode] = false;
    }
}

