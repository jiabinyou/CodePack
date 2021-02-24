package Graph.GraphTraversal.Plan2;

import java.util.*;

/**
 * Sol.DFS
 * 难点：难看出题目想要求的本质
 * 本质：可以将name单独抛开，只看email，实际上就是求以每个email为一个graph node，将connected area用sort方式还原出来
 *       最后再加上前面的name即可（name是迷惑项）
 * 所以实质：unconnected，undirected ， may cylic graph， 求connected area相关
 * --> traverse vertex
 * --> DFS1
 * 需要build 2G graph, extra mark visited ds
 */

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        //sanity check
        if (accounts == null || accounts.size() == 0) {
            return res;
        }
        Map<String, List<String>> graph = buildGraph(accounts);  //<email, all neighbor emails>
        /**难点：unconnected graph，以原input信息为起点，在自己建的graph上跑dfs*/
        Set<String> visited = new HashSet<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            String node = account.get(1);  //第一个email作为curNode
            if (account.size() > 1 && visited.add(node)) {  /**单独mark visit root*/
                List<String> curPath = new ArrayList<>();  //dfs寻找与curNode相连的xonnected area中所有email
                dfs(graph, node, curPath, visited);
                Collections.sort(curPath);
                curPath.add(0, name);    //首位add name
                res.add(new ArrayList<>(curPath));  //add emails
            }
        }
        return res;
    }

    private void dfs(Map<String, List<String>> graph, String node, List<String> curPath, Set<String> visited) {
        //base case (root中检查过了)
        //update curPath
        curPath.add(node);
        //recursion
        for (String nei : graph.get(node)) {
            if (visited.add(nei)) {  /**mark visited at generation*/
                dfs(graph, nei, curPath, visited);
            }
        }

    }

    private Map<String, List<String>> buildGraph(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> account : accounts) {
            if (account.size() > 1) {
                for (int i = 1; i < account.size(); i++) {
                    graph.putIfAbsent(account.get(i), new ArrayList<>());
                    if (i >= 2) {   //避开name，只放email
                        graph.get(account.get(i)).add(account.get(i - 1));  //前后相邻的两个email互相指向edge
                        graph.get(account.get(i - 1)).add(account.get(i));
                    }
                }
            }
        }
        return graph;
    }
}

