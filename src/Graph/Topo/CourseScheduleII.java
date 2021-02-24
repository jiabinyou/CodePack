package Graph.Topo;
import java.util.*;
/**
 * Sol1. DFS2  -->解法仍然有问题
 * 这道题在1的基础上，还需要recover path
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
        result.add(node);
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
