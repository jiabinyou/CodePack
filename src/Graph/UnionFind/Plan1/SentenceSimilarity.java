package Graph.UnionFind.Plan1;
import java.util.*;

public class SentenceSimilarity {
    public boolean areSentencesSimilar(String[] words1, String[] words2, List<List<String>> pairs) {
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
            String one = words1[i];
            String two = words2[i];
            if (!one.equals(two) && (!graph.containsKey(one) || !graph.get(one).contains(two))) {
                return false;
            }
        }
        return true;
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
