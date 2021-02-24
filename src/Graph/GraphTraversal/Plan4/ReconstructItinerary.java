package Graph.GraphTraversal.Plan4;

import java.util.*;

/**
 * Sol1. Traverse all path --> Graph.DFS3
 * 难点1: ！！
 * 这道题的graph node是一维的各个地点名称，edge是["MUC", "LHR"]这一对，
 * 但是，仔细想一下就会明白，这一题中：
 * 地点名称，比如JFK是可以重复使用的，但是["MUC", "LHR"]这一条信息不可以重复使用
 * （即dfs3本质：traverse path，所以node可以重复使用），但这一题中edge不可以重复走，只能走一遍
 * 所以我们要对走过的edge信息进行mark visited
 * 方法：
 * 我们制作Map<String, List<String>> graph， 其中表示信息为:Map<node, List<node>>,每一对key value pair就代表一条edge信息
 * ！！！我们只要将visit过的node，从value中去掉，实际上就完成了mark visit
 *
 * 难点2：
 * 这道题有个隐含条件，就是需要检查是否connected，如果发现不是，那么这道题就直接返回false了
 * 这也是为什么我们设计的dfs3返回boolean值的原因
 */
public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        //sanity check
        if (tickets == null || tickets.size() == 0) {
            return res;
        }
        Map<String, List<String>> graph = buildGraph(tickets);
        String begin = "JFK";
        int targetLen = tickets.size() + 1;
        res.add(begin); /**单独process root*/

        if (dfs(graph, begin, targetLen, res)) { /**这里返回一条path即可，所以res即为dfs的path信息*/
            return res;
        }
        return new ArrayList<>();
    }

    /**Graph.DFS3: boolean: if can find succedd itinerary*/
    private boolean dfs(Map<String, List<String>> graph, String begin, int targetLen, List<String> path) {
        //base case
        if (path.size() == targetLen) {
            return true;
        }
        if (!graph.containsKey(begin)) {
            return false;
        }
        //recursion
        List<String> neighbors = graph.get(begin);
        for (int i = 0; i < neighbors.size(); i++) {
            String nei = neighbors.get(i);
            neighbors.remove(nei); /**mark visited at generation, 难点！！不需要单独mark visit root，因为那时还没出现edge信息*/
            path.add(nei);   /**process at generation*/
            if (dfs(graph, nei, targetLen, path)) {  //只要找到一条，就是true
                return true;
            }
            path.remove(path.size() - 1);  /**recover*/
            neighbors.add(i, nei);  /**recover, 难点！！因为要保证sort顺序，所以必须还在原来index加入i,所以i必须记录*/

        }
        return false;
    }

    private Map<String, List<String>> buildGraph(List<List<String>> tickets) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> ticket : tickets) {
            graph.putIfAbsent(ticket.get(0), new ArrayList<>());
            graph.get(ticket.get(0)).add(ticket.get(1));
        }
        for (List<String> neighbors : graph.values()) {
            Collections.sort(neighbors);
        }
        return graph;
    }
}

