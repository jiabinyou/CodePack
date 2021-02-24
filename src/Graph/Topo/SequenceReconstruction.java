package Graph.Topo;
import java.util.*;
/***
 * Sol. BFS TOPO
 * 原输入中[1,2]表示1出现在2的前面，即如果出现2，前面必须先有1，是dependency的关系
 *
 * 难点：
 * 这道题要求，graph中必须有且只有一条topo path，即要求org是唯一一条valid topo path
 * 如果将每个数字作为一个节点，其前后关系作为一条边，则这道题的seqs参数可以转为一个图数据结构
 * 以例子4为例，
 * Input:
 * org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
 * Output:
 * true
 * [5,2,6,3] -> [5,2],[2,6],[6,3], [4,1,5,2] -> [4,1],[1,5],[5,2]
 * 基于以上的edge list可以构造一个图数据结构，而参数org则是满足有向图的遍历路径。当且仅当，org是唯一合法的拓扑遍历路径时，返回true.
 */
public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        if (!buildGraph(org, seqs, indegree, graph)) {
            return false;
        }
        return bfs(org, graph, indegree);
    }

    /**BFS TOPO*/
    private boolean buildGraph(int[] org, List<List<Integer>> seqs, Map<Integer, Integer> indegree, Map<Integer, Set<Integer>> graph) {
        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size(); i++) {
                indegree.putIfAbsent(seq.get(i), 0);
                graph.putIfAbsent(seq.get(i), new HashSet<>());
                if (i > 0) {
                    if (graph.get(seq.get(i - 1)).add(seq.get(i))) {
                        indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
                    }
                }
            }

        }
        return indegree.size() == org.length;
    }

    private boolean bfs(int[] org, Map<Integer, Set<Integer>> graph, Map<Integer, Integer> indegree) {
        Queue<Integer> queue = new ArrayDeque<>();
        for (Map.Entry<Integer, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }
        int index = 0;
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return false;
            }
            int cur = queue.poll();
            if (cur != org[index++]) {
                return false;
            }
            for (int nei : graph.get(cur)) {
                int curIndegree = indegree.get(nei);
                curIndegree--;
                indegree.put(nei, curIndegree);
                if (curIndegree == 0) {
                    queue.offer(nei);
                }
            }
        }
        return index == org.length;
    }
}