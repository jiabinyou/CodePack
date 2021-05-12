package Graph.Topo;
import java.util.*;

/**
 * Sol1. DFS2'V'
 * This is a graph problem, we can regard each course as a graph node. There is a directed edge between the course and its
 * prerequisites course. To solve this problem, first we can build the graph, then use the depth first search
 * to check if there is cycle in the graph. If there is ni cycle, ,means all course could be finished.
 *
 * TC: O(∣E∣+∣V∣) where |V| is the number of courses, and |E|is the number of dependencies.
 * SC: O(∣E∣+∣V∣)
 *
 * 过例子：
 * 6
 * [[1,0],[2,3],[4,5],[1,4]]
 * build graph:
 * map: <0, 1><2,3><4,5><1,4>
 * graph node:          0    2
 *                    /     /
 *                   1     3
 *                  /
 *                 4
 *                /
 *               5
 *
 */
public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return false;
        }
        List<List<Integer>> graph = buildGraph(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) { /**以每一门课程编号作为起点检查，如果有环，说明无法顺利完成所有*/
            if (!dfs(i, graph, visited, visiting)) {
                return false;
            }
        }
        return true;
    }

    /**DFS2'V'*/
    private boolean dfs(int node, List<List<Integer>> graph, boolean[] visited, boolean[] visiting) {
        if(visited[node]) {
            return true;
        }
        if (visiting[node]) {
            return false;
        }
        visiting[node] = true; /**mark visiting*/
        for (int nei : graph.get(node)) {
            if (!dfs(nei, graph, visited, visiting)) {
                return false;
            }
        }
        visited[node] = true; /**mark visited*/
        return true;
    }

    private List<List<Integer>> buildGraph(int numCourses, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {   /**input:[0, 1], 0 depend on 1,这里反向建图，将1作为0的nei，先遍历到0*/
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }
}

/**
 * Sol2. BFS TOPO
 */
class SolCS2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //sanity check
        if (numCourses == 0) {
            return false;
        }
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = buildMap(numCourses, prerequisites, indegree);
        return bfs(numCourses, graph, indegree);
    }

    /**BFS1'V'*/
    private boolean bfs(int nodes, List<List<Integer>> graph, int[] indegree) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        /**!! topo init root (all degree = 0 is root)*/
        for (int i = 0; i < indegree.length; i++) {
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
        return result.size() == nodes;
    }

    private List<List<Integer>> buildMap(int nodes, int[][] edges, int[] indegree) {
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

