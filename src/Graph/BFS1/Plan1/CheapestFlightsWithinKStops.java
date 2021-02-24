package Graph.BFS1.Plan1;
import java.util.*;

/**
 * 这道题本质：
 * 沒有 k 個中繼城市的限制，就是最單純的 Dijkstra Algorithm （edge weight不同，求min的问题--> BFS2解决）
 * 但是有了K stops的限制，就是变化版本的Dijkstra Algorithm
 */

/**
 * Sol1.Graph.DFS3 find all path from init to goal
 */
public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        /**build graph: <index: edge init, list<edge goal, cost>>*/
        List<List<int[]>> graph = buildGraph(n, flights);
        int[] glbMin = new int[] {Integer.MAX_VALUE};
        dfs(graph, src, dst, K + 1, 0, glbMin); //允许K stops，即K + 1 steps
        return glbMin[0] == Integer.MAX_VALUE ? -1 : glbMin[0];
    }

    /**dfs3: find all path from init to goal*/
    private void dfs(List<List<int[]>> graph, int start, int end, int remainStep, int cost, int[]glbMin) {
        //base case
        if (remainStep < 0) {
            return;
        }
        if (start == end) {
            glbMin[0] = cost;
            return;
        }
        if (graph.get(start).isEmpty()) {
            return;
        }
        //recursion
        for (int[] nei : graph.get(start)) {
            if (cost + nei[1] < glbMin[0]) {  /**关键：只有接下里要走的cost，比已知cost小，这条path走下去才有意义*/
                dfs(graph, nei[0], end, remainStep - 1, cost + nei[1], glbMin);
            }
        }
    }
    private List<List<int[]>> buildGraph(int node, int[][] flights) {
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < node; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new int[] {flight[1], flight[2]});
        }
        return graph;
    }
}

/**
 * Sol2.Graph.BFS1 with K levels
 */
class SolCFWKS2 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        /**build graph*/
        List<List<State>> graph = buildGraph(n, flights);
        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(src, 0));
        int level = 0;
        int min = Integer.MAX_VALUE;
        while (!queue.isEmpty() && level <= K + 1) {
            //expand
            int size = queue.size();
            //generation
            for (int i = 0; i < size; i++) {
                State cur = queue.poll();
                if (cur.city == dst) {
                    min = Math.min(min, cur.cost);
                }
                for (State nei : graph.get(cur.city)) {
                    if (cur.cost + nei.cost < min) {
                        queue.offer(new State(nei.city, cur.cost + nei.cost));
                    }
                }
            }
            level++;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private List<List<State>> buildGraph(int node, int[][] flights) {
        List<List<State>> graph = new ArrayList<>();
        for (int i = 0; i < node; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new State(flight[1], flight[2]));
        }
        return graph;
    }

    static class State {
        int city;
        int cost;
        State(int city, int cost) {
            this.city = city;
            this.cost = cost;
        }
    }
}

/**
 * Sol3. Graph.BFS2
 * Graph.BFS2 更新每個城市到 src 的最短路徑且符合K STOPS限制，直到 pop 出來的城市是 dst
 */
class SolCFWKS3 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        List<List<State>> graph = buildGraph(n, flights, K);
        PriorityQueue<State> minHeap = new PriorityQueue<>();
        minHeap.offer(new State(src, 0, K + 1));
        while (!minHeap.isEmpty()) {
            State cur = minHeap.poll();
            if (cur.city == dst) {
                return cur.cost;
            }
            if (cur.stops > 0) { /**关键：符合此条件，说明在K步之内，才能继续走到nei*/
                for (State nei : graph.get(cur.city)) {
                    minHeap.offer(new State(nei.city, cur.cost + nei.cost, cur.stops - 1));
                }
            }
        }
        return -1;
    }

    private List<List<State>> buildGraph(int node, int[][] flights, int K) {
        List<List<State>> graph = new ArrayList<>();
        for (int i = 0; i < node; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] flight : flights) {
            graph.get(flight[0]).add(new State(flight[1], flight[2], K + 1));
        }
        return graph;
    }

    static class State implements Comparable<State> {
        int city;
        int cost;
        int stops;
        State(int city, int cost, int stops) {
            this.city = city;
            this.cost = cost;
            this.stops = stops;
        }
        @Override
        public int compareTo(State other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
}



