package Graph.Topo;
import java.util.*;
/**
 * Sol1. DFS2  -->解法仍然有问题
 * 这道题在1的基础上，还需要recover path
 *
 * This is a graph problem, we can regard each course as a graph node. There is a directed edge between the course and its
 * prerequisites course. To solve this problem, first we can build the graph, then use the depth first search
 * to check if there is cycle in the graph. If there is ni cycle, ,means all course could be finished.
 * recover path:
 * when the graph node has no cycle, we record this graph node as one step in our path. When depth first search finished,
 * recover the whole path from our recorded memo.
 *
 *  TC: O(∣E∣+∣V∣) where |V| is the number of courses, and |E|is the number of dependencies.
 *  SC: O(∣E∣+∣V∣)
 *
 * 过例子：
 * 6
 * [[0,1],[2,3],[4,5],[1,4]]
 * build graph:
 * map: <0, 1><2,3><4,5><1,4>
 * graph node:         0     2
 *                    /     /
 *                   1     3
 *                  /
 *                 4
 *                /
 *               5
 * dfs node         hasCycle?     res
 *    0
 *    1    2
 *    4    3
 *    5
 *   (return)
 *    5                 N          5
 *    4                 N          54
 *    1                 N          541
 *    0                 N          5410
 *
 *    3                 N          54103
 *    2                 N          541032
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //sanity check
        if (numCourses == 0) {
            return new int[0];
        }
        /**build graph*/
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, visited, visiting, result)) {
                return new int[0];
            }
        }
        /**recover topo path*/
        int[] courses = new int[result.size()];
        for (int i = 0; i < courses.length; i++) {
            courses[i] = result.get(i);
        }
        return courses;
    }

    /**DFS2 check if has cycle*/
    private boolean dfs(int node, List<List<Integer>> graph, boolean[] visited, boolean[] visiting, List<Integer> result) {
        if (visited[node]) {
            return true;
        }
        if (visiting[node]) {
            return false;
        }
        visiting[node] = true;
        for (int nei : graph.get(node)) {
            if (!dfs(nei, graph, visited, visiting, result)) {
                return false;
            }
        }
        visited[node] = true;
        result.add(node);  /**当且仅当符合node符合topo logical，才加入path中*/
        return true;
    }

    private List<List<Integer>> buildGraph(int nodes, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }
}

/**
 * Sol2. BFS TOPO
 */
class SolCSII2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //sanity check
        if (numCourses == 0) {
            return new int[0];
        }
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites, indegree);
        List<Integer> result = bfs(numCourses, graph, indegree);
        int[] courses = new int[result.size()];
        for (int i = 0; i < courses.length; i++) {
            courses[i] = result.get(i);
        }
        return courses;
    }

    /**BFS TOPO*/
    private List<Integer> bfs(int nodes, List<List<Integer>> graph, int[] indegree) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        /**!! topo init root (all degree = 0 is root)*/
        for (int i = 0; i < nodes; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            /**expansion*/
            int cur = queue.poll();
            result.add(cur);
            /**generation*/
            for (int nei : graph.get(cur)) {
                indegree[nei]--;
                if (indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }
        return result.size() == nodes ? result : new ArrayList<>();
    }

    private List<List<Integer>> buildGraph(int nodes, int[][] edges, int[] indegree) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[1]).add(edge[0]);
            indegree[edge[0]]++;
        }
        return graph;
    }
}

