package Graph.BFS2.shortestPath;
import java.util.*;
import Graph.GraphTraversal.Plan1.Node;

public class A_ShotestPath问题小结 {
}

/**Shoetest path主要有两种解法*/
/**
 * 1.BFS1(bread first search) -->用于全正数，权重相同graph求shortest path
 * 用bread first search求shortest distance
 * 前提：only when all grah edge cost in the graph are uniform, we can use BFSI find the shortest path
 * 用接下来的code求一个点到另一个target点的shortest path的理论基础：
 * 使用前提：
 * 1.weight of all edges都是正数
 * 2.the weight of all edges are equal每一条边的权重相同
 */

/**
 * 1.BFS2(best first search)  -->用于全正数，权重不同graph求shortest path
 * 使用前提：
 * 1.weight of all edges都是正数
 *
 * 目标：find the shortest path from one node to any other node
 * data structure: priority queue
 * 前提： edges cost不同，使用BFSII求shortest path
 *  1 -------------------> 2
 *  ^ \                    |
 *  |   \                  |
 *  |      ---> 3          |
 *  |   /                  |
 *  | /                    |
 * 4 --------------------->5
 * e.g.shortest path (2, 3)  = 2 → 5 → 4 → 3
 * */

/************************************************模板************************************************************8*/
/**********************************BFS1 shortest path模板************************************/
/**1.BFS1 求length of shortest path*/
class A_ShotestPath问题小结_BFS1模板 {
    /**模板1：ount at expansion*/
    private int bfsShortestDistance1(Node root, Node target) {
        if (root == target) {
            return 0;
        }
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        int distance = 0;
        //init
        queue.offer(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (cur == target) {
                    return distance;
                }
                for (Node nei : cur.neighbors) {  //expand
                    if (visited.add(nei)) {
                        queue.offer(nei);	//generation
                    }
                }
            }
            distance++;
        }
        return -1;
    }

    /**模板2：count at generation(更快)*/
    private int bfsShortestDistance2(Node root, Node target) {
        if (root == target) {
            return 0;
        }
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        int distance = 0;
        //init
        queue.offer(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node nei : cur.neighbors) {
                if (nei == target) {
                    return distance + 1;
                }
                if (visited.add(nei)) {
                    queue.offer(nei);
                }
            }
        }
        return -1;
    }
}

/**BFS1 reconstruct shortest path*/
/**使用prevMap记录使用BFS一路走来，shortest path上面所有node前后跳的关系，从而进行recover*/
class A_ShotestPath问题小结_BFS1_reconstructPath模板 {
    /**
     * step 1:construct the prevMap to indicate for each node, whi is the parent node to generate the cur node
     */
    private List<Node> bfsRecoverPath(Node root, Node target) {
        Queue<Node> queue = new ArrayDeque<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> prevMap = new HashMap<>();
        //init
        queue.offer(root);
        visited.add(root);
        prevMap.put(root, null);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (Node nei : cur.neighbors) {
                if (!prevMap.containsKey(nei)) {
                    queue.offer(nei);
                    prevMap.put(nei, cur);
                }
            }
        }
        /**step 2:reconstruct the one shortest path, start from the target until find root, then reverse*/
        List<Node> path = new ArrayList<>();
        path.add(target);
        Node cur = target;
        while (cur != root) {
            cur = prevMap.get(cur);
            path.add(cur);
        }
        Collections.reverse(path);
        return path;
    }
}

/**********************************BFS2 shortest path模板************************************/






