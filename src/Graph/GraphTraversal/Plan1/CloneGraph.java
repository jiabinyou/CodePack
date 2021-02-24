package Graph.GraphTraversal.Plan1;

import java.util.*;

/**
 * Sol1. DFS
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
