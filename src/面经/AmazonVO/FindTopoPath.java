package 面经.AmazonVO;

import java.util.*;

public class FindTopoPath {
    public String findOrder(List<List<String>> input) {
        //sanity check
        if (input == null || input.size() == 0) {
            return "";
        }
        /**build graph*/
        Map<Character, List<Character>> graph = buildGraph(input);
        int numNode = graph.size();
        List<Integer> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[numNode ];
        boolean[] visiting = new boolean[numNode];
        for (int i = 0; i < numNode ; i++) {
            String nodeS = input.get(i).get(0);
            char node = nodeS.charAt(0);
            if (!dfs(node, graph, visited, visiting, sb)) {
                return "";
            }
        }
        /**recover topo path*/
//        int[] courses = new int[result.size()];
//        for (int i = 0; i < sb.length(); i++) {
//            courses[i] = result.get(i);
//        }
        return sb.toString();
    }

    /**DFS2 check if has cycle*/
    private boolean dfs(char node, Map<Character, List<Character>> graph, boolean[] visited, boolean[] visiting, StringBuilder sb) {
        if (visited[node - '0']) {
            return true;
        }
        if (visiting[node - '0']) {
            return false;
        }
        visiting[node - '0'] = true;
        for (char nei : graph.get(node)) {
            if (!dfs(nei, graph, visited, visiting, sb)) {
                return false;
            }
        }
        visited[node] = true;
        sb.append(node);  /**当且仅当符合node符合topo logical，才加入path中*/
        return true;
    }

    private Map<Character, List<Character>> buildGraph(List<List<String>> input) {
        Map<Character, List<Character>> graph = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            String nodeS = input.get(i).get(0);
            char node = nodeS.charAt(0);
            String depenS = input.get(i).get(1);
            if (!graph.containsKey(node)) {
                graph.put(node, new ArrayList<>());
            }
            if (depenS != "") {
                for (char c : depenS.toCharArray()) {
                    graph.get(node).add(c);
                }
            }
        }
        return graph;
    }
}

