package Graph.UnionFind.Plan1;
import java.util.*;
/**
 * SOL1.DFS1'V'
 */
public class SentenceSimilarityII {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        //sanity check
        if (words1 == null && words2 == null || words1.length == 0 && words2.length == 0) {
            return true;
        }
        if (words1 == null || words2 == null || words1.length == 0 && words2.length == 0) {
            return false;
        }
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Set<String>> graph = buildGraph(words1, words2, pairs);
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                continue;
            }
            if (!graph.containsKey(words1[i])) {
                return false;
            }
            Set visited = new HashSet<>();
            if (!dfs(graph, words1[i], words2[i], visited)) {
                return false;
            }
        }
        return true;
    }

    /**DFS1 check if connected, 难点：这里以每一个word[i]做起点进行dfs，所以mark nei visited，不要mark root*/
    private boolean dfs(Map<String, Set<String>> graph, String one, String two, Set<String> visited) {
        if (graph.get(one).contains(two)) {
            return true;
        }
        for (String nei : graph.get(one)) {
            if (visited.add(nei) && dfs(graph, nei, two, visited)) {
                return true;
            }
        }
        return false;
    }

    /**build undirected graph*/
    private Map<String, Set<String>> buildGraph(String[] words1, String[] words2, List<List<String>> pairs) {
        Map<String, Set<String>> map = new HashMap<>();
        for (List<String> pair : pairs) {
            String first =  pair.get(0);
            String second = pair.get(1);
            map.putIfAbsent(first, new HashSet<>());
            map.putIfAbsent(second, new HashSet<>());
            map.get(first).add(second);
            map.get(second).add(first);
        }
        return map;
    }
}

/**
 * SOL2.Union Find
 */
class SolSSII2 {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        //sanity check
        if (words1 == null && words2 == null || words1.length == 0 && words2.length == 0) {
            return true;
        }
        if (words1 == null || words2 == null || words1.length == 0 && words2.length == 0) {
            return false;
        }
        if (words1.length != words2.length) {
            return false;
        }
        /**buil root idx ds + Uion*/
        Map<String, String> roots = new HashMap<>();
        for (List<String> pair : pairs) {
            String root1 = getRoot(roots, pair.get(0));
            String root2 = getRoot(roots, pair.get(1));
            if (!root1.equals(root2)) {
                roots.put(root1, root2); //将[A, B]中，B当做A的初始rootIdx放入
            }
        }

        for (int i = 0; i < words1.length; i++) {
            String one = words1[i];
            String two = words2[i];
            if (!one.equals(two) && !getRoot(roots, one).equals(getRoot(roots, two))) {
                return false;
            }
        }
        return true;
    }

    /**Find + Union*/
    private String getRoot(Map<String, String> roots, String s) {
        if (!roots.containsKey(s)) {
            roots.put(s, s);
        }
        if (!roots.get(s).equals(s)) {
            roots.put(s, getRoot(roots, roots.get(s)));  //Union过程，先更新S的rootIdx（逐步上移idx）
        }
        return roots.get(s); //最后再返回
    }
}


