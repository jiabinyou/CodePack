package Graph.GraphTraversal.Plan2;

import java.util.ArrayList;
import java.util.List;

/**
 * Sol1.travers vertex, may cylic --> 可DFS1
 * need V ds？
 * 需要，因为虽然是0 到 graph.length - 1 个node的一维graph，但是比那里方向未知，由input提供
 * boolean[] visited
 *
 * 难点1：怎样检查是否是terminal node？
 * 只要这个node没有nei，即nei为空，没有任何能够走到的其他点，就是terminal node
 */
public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (graph == null || graph.length == 0) {
            return res;
        }
        State[] states = new State[graph.length];
        /**难点：不是只需要测出connected area，而是每个点都要作为起始检查，不能因为visited就跳过，*/
        for (int i = 0; i < graph.length; i++) {
            if (dfs(graph, i, states)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean dfs(int[][] graph, int node, State[] states) {
        //base case
        if (states[node] == State.SAFE) {  //DFS2中VISITED
            return true;
        }
        if (states[node] == State.UNSAFE) {  //DFS2中VISITING,此时说明node在环上
            return false;
        }
        states[node] = State.UNSAFE; //mark visiting at expansion
        //recursion
        for (int nei : graph[node]) {
            if (!dfs(graph, nei, states)) {  //只要nei有一个有环，说明当前node unsafe
                return false;
            }
        }
        states[node] = State.SAFE; //mark visited at expansion
        return true;
    }

    static enum State {
        UNVISITED, UNSAFE, SAFE
    }
}
