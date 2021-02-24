package Graph.GraphTraversal.Plan2;
import java.util.*;

/**
 * traverse vertex, acylic --> DFS1
 * need V ds?
 * 需要。我们做的dfs是在自己build的双向graph上，Map<String, Map<String, Double>> graph
 * 虽然graph node是a,b,c....A，B...等一维Character信息，但是我们并不知道遍历的方向(是由input所包含的信息决定的)，
 * 所以需要使用一维mark visited DS帮助我们避免重复遍历graph node
 *
 * 难点：
 * 这一题mark visit难点在于，应该是每一个query单独mark visit（更宽松），而不是所有的在一起mark visit
 * 因为e.g.
 *       计算a / c; 中间path  a/b -> b/c
 *       计算a / d; 中间path  a/b -> b/c ->c/d
 *  其中a/b，b/c edges完全可以重复使用，所以会发现不同query之间path是可以重复使用，但同一个query中不行
 */
public class EvaluateDivision {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //build graph
        Map<String, Map<String, Double>> map = buildGraph(equations, values);
        //unconnected
        double[] res = new double[queries.size()];
        int idx = 0;   //用于填写res的pointer
        for (List<String> query : queries) {
            Set<String> visited = new HashSet<>();
            res[idx++] = dfs(query.get(0), query.get(1), map, visited);
        }
        return res;
    }

    private double dfs(String begin, String end, Map<String, Map<String, Double>> map, Set<String> visited) {
        //base case
        if (!map.containsKey(begin)) {
            return -1;
        }
        if (map.get(begin).containsKey(end)) {
            return map.get(begin).get(end);
        }
        visited.add(begin);  //单独mark visited root
        //recursion
        for (String nei : map.get(begin).keySet()) {
            if (visited.add(nei)) {   //mark visited at generation & valid branch condition
                double product = dfs(nei, end, map, visited);
                //return res, product != -1, 才说明是能继续往下找的，才能用孩子返回的值，否则直接返回-1
                if (product != -1) {
                    return product * map.get(begin).get(nei);
                }
            }
        }
        return -1;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            map.putIfAbsent(equation.get(0), new HashMap<>());
            map.putIfAbsent(equation.get(1), new HashMap<>());
            map.get(equation.get(0)).put(equation.get(1), values[i]);
            map.get(equation.get(1)).put(equation.get(0), 1 / values[i]);
        }
        return map;
    }
}

/**
 * SOL2. BFS
 */
class SolED2 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //build graph
        Map<String, Map<String, Double>> map = buildGraph(equations, values);
        //unconnected
        double[] res = new double[queries.size()];
        int idx = 0;   //用于填写res的pointer
        for (List<String> query : queries) {
            res[idx++] = bfs(query.get(0), query.get(1), map);
        }
        return res;
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            map.putIfAbsent(equation.get(0), new HashMap<>());
            map.putIfAbsent(equation.get(1), new HashMap<>());
            map.get(equation.get(0)).put(equation.get(1), values[i]);
            map.get(equation.get(1)).put(equation.get(0), 1 / values[i]);
        }
        return map;
    }

    private double bfs(String start, String end, Map<String, Map<String, Double>> map) {
        if (!map.containsKey(start)) {
            return -1;
        }
        Queue<Pair> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Pair(start, 1.0)); //init root
        visited.add(start);  //单独mark visited root
        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            for (String nei : map.get(cur.str).keySet()) {
                if (nei.equals(end)) {
                    return map.get(cur.str).get(nei) * cur.val;
                }
                if (visited.add(nei)) {      //mark visited at generation
                    queue.offer(new Pair(nei, map.get(cur.str).get(nei) * cur.val));
                }
            }
        }
        return -1;
    }

    static class Pair {
        String str;
        double val;
        Pair(String str, double val) {
            this.str = str;
            this.val = val;
        }
    }
}

