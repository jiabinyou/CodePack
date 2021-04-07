package Graph.GraphTraversal.Plan1;

import java.util.*;

/**
 * Sol1. DFS
 * 需要一个map保存旧的node到新的node映射，这样， 遍历到每个节点的时候， 对于任何一条旧的边，都可以找到对应的新的节点，
 * 并为新的节点copyNode连接上已经复制好的它的neighbors(lower level dfs完成)。
 * 同时，map也起到了mark visited的作用。只要是map中已经存在的节点，说明我们已经visit过了。
 *
 * TC: O(V + E)       dfs traverse all vertex, 每个vertex和edge各遍历一遍
 * SC: O(V)           map重要存储所有vertex，O（V); recursion深度也是O（V），所以最终O(V)
 */
public class CloneGraph {
    public Node cloneGraph(Node node) {
        //sanity check
        if (node == null) {
            return node;
        }
        Map<Node, Node> map = new HashMap<>(); //V ds
        dfs(node, map);
        return map.get(node);
    }
    /**dfs:process at generation, mark visit at expansion*/
    private void dfs(Node node, Map<Node, Node> map) {
        //base case
        if (map.containsKey(node)) {
            return;
        }
        Node copy = new Node(node.val, new ArrayList<>());    //process at generation
        map.put(node, copy); //mark visit at expansion
        //recursion
        for (Node nei : node.neighbors) {
            dfs(nei, map);
            copy.neighbors.add(map.get(nei));
        }
    }
}

/**
 * Sol2. BFS
 * TC: O(V + E)       BFS traverse all vertex, 每个vertex和edge各遍历一遍
 * SC: O(V)           在tree上，queue最大空间消耗=tree最宽一层的node数目，但在general graph中我们假设是一个太阳性状
 *                    connected graph，其他所有node都连在root上面，最多queue size = #node
 */
class Solcg2 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node); //init root
        map.put(node, new Node(node.val, new ArrayList<>())); //单独process root, 单独mark visit root
        while (!queue.isEmpty()) {
            //expansion
            Node cur = queue.poll();
            //generation
            for (Node nei : cur.neighbors) {
                if (!map.containsKey(nei)) {
                    Node copy = new Node(nei.val, new ArrayList<>());  //process at generation
                    map.put(nei, copy); //mark visit at generation
                    queue.offer(nei);
                }
                map.get(cur).neighbors.add(map.get(nei));
            }
        }
        return map.get(node);
    }
}
