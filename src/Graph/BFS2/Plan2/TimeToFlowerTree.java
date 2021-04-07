package Graph.BFS2.Plan2;
import java.util.*;

/**
 * 这题乍一看是traverse node，并且不需要测环，很明显可以使用DFS2或者BFS
 * 先用DFS2试着做一做
 * */
/**SOL1:DFS2（容易stack over flow，并且将事情复杂化，不推荐）
 * 显然使用DFS2将事情复杂化了，原因在于：
 * 这道题有一个细节，那就是实际上所给的graph是tree，并且需要我们按照"层"的概念去取time cost，而不是简单地把所有cost加在一起。
 * 比如同一层有两个cost 3， 5，那么因为同一层的遍历是同时发生的，所以针对同一层cost只保留最大的那一个
 * 对于分层的问题，显然BFS方法是最直接的
 *
 * 这道题还有一个坑，就是不一定是一棵树，可能有多个散落的点，即并非connected graph，而这些散落点在这一题中是没有意义的，要在corner case中去掉
 * */
public class TimeToFlowerTree {
    /**
     traverse all node -->dfs2 / bfs
     需要mark visited-》可用boolean[] 或者set
     */
    public int timeToFlowerTree(int[] father, int[] time) {
        // sanity check
        if (father == null || father.length <= 1 || time == null || time.length <= 1) {
            return 0;
        }
        List<Integer> totalTime = new ArrayList<>();
        int len = father.length;
        Map<Integer, List<Integer>> graph = buildGraph(father);
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        dfs(graph, 0, time, visited, totalTime, len, 0);
        //build res
        int res = 0;
        for (int num : totalTime) {
            res += num;
        }
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> graph, int fatherNode, int[] time,
                     Set<Integer> visited, List<Integer> totalTime, int len, int level) {
        //base case
        if (!graph.containsKey(fatherNode)) {   /**corner case:可能有些node单独存在，不在tree中*/
            return;
        }
        if(graph.get(fatherNode).size() == 0) { /**可能有些node*/
            return;
        }
        if (visited.size() == len) {
            return;
        }
        //不需要在expansion时候对root处理，因为0-》0没有意义，从generation直接开始
        //generation
        for (Integer nei : graph.get(fatherNode)) {
            if (visited.contains(nei)) {  //base case
                continue;
            }
            visited.add(nei); //mark visited
            if (totalTime.size() == level) {
                totalTime.add(time[nei]);
            } else {
                int larger = Math.max(totalTime.get(level), time[nei]);
                totalTime.set(level, larger);
            }
            dfs(graph, nei, time, visited, totalTime, len, level + 1);
        }
    }

    private Map<Integer, List<Integer>> buildGraph(int[] father) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>()); //init root
        for (int i = 1; i < father.length; i++) {
            if (!map.containsKey(father[i])) {
                map.put(father[i], new ArrayList<>());
            }
            map.get(father[i]).add(i);
        }
        return map;
    }
}


class TimeToFlowerTreeSol2 {
    /**
     * @param father: the father of every node
     * @param time: the time from father[i] to node i
     * @return: time to flower tree
     */

    /**
     SOL2: BFS
     因为题目中已经明确指出这是一个tree!!在tree上面做BFS实际上不需要mark visited
     因为压根就不可能走回头路
     */
    public int timeToFlowerTree(int[] father, int[] time) {
        // sanity check
        if (father == null || father.length <= 1 || time == null || time.length <= 1) {
            return 0;
        }
        Map<Integer, List<Integer>> graph = buildGraph(father);
        //从根节点0开始，往下做BFS
        Queue<Integer> queue = new LinkedList<>();
        int[] timeForNodes = new int[father.length];  /**记录从node0到nodei的总cost*/
        queue.offer(0);
        int max = Integer.MIN_VALUE; /**关键：*/
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                //expansion
                int cur = queue.poll();
                if (father[cur] == -1) {   //排除node 0信息，无效
                    timeForNodes[cur] = 0;
                } else {
                    /**0-》i cost =(0 -> i's parent cost) + (i's parent -> i) cost*/
                    timeForNodes[cur] = timeForNodes[father[cur]] + time[cur];
                }
                max = Math.max(timeForNodes[cur], max); //因为谁时间最长，决定最终遍历时间
                //generation
                /**重点：graph中要先检查children层是否为空，不为空才能generate*/
                List<Integer> children = graph.get(cur);
                if (children != null) {
                    for (int child : children) {
                        queue.offer(child);
                    }
                }
            }
        }
        return max;
    }


    private Map<Integer, List<Integer>> buildGraph(int[] father) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>()); //init root
        for (int i = 1; i < father.length; i++) {
            if (!map.containsKey(father[i])) {
                map.put(father[i], new ArrayList<>());
            }
            map.get(father[i]).add(i);
        }
        return map;
    }
}


