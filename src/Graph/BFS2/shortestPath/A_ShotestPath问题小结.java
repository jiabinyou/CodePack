package Graph.BFS2.shortestPath;
import java.util.*;
import Graph.GraphTraversal.Plan1.Node;

public class A_ShotestPath问题小结 {
}
//九章课件：
//https://jiuzhang-media.oss-cn-hangzhou.aliyuncs.com/new_storage_v2/private/staff/meta_chapter_sliders/2/24/249f67c8-763f-11eb-b7df-0242ac190002/Chapter%207.%20BFS%20%E8%BF%9B%E9%98%B6%E4%B8%8E%E6%9C%80%E7%9F%AD%E8%B7%AF%E7%AE%97%E6%B3%95%20v4.pdf?OSSAccessKeyId=LTAImC94doJucLC5&Expires=1618959087&Signature=fcxTpI4wCJaLCRiZYQ%2FrqAxe4lM%3D
/**Shoetest path主要有4种解法
 * 一. BFS1: bread first search -》优化：SPFA(Shortest Path Fast Algorithm)       【高频率】
 *    1. 权重全部>0, 权重unit ：使用基本BFS1，即可解决shortest path问题
 *    2. 权重全部>0, 权重非unit,可以用下面几种方法：
 *       a) 双层BFS: 【用得少】
 *                 外层 BFS 求最短路径,内层 BFS 找连通的点;
 *       b) 夹心BFS:  【用得少】
 *                 两个队列交替, 使用 BFS 实现中两个队列交替的方法,
 *                 将通过掷骰子跳到的点放在下一个队列里, 将通过直连到达的点放在当前队列里,这样就不会破坏每个队列为同一层节点的属性
 *       c) SPFA(Shortest Path Fast Algorithm) 【重要】！！！！
 *
 * 二. DP                            【次高频率】
 *      找最短路径也在动态规划的魔爪范围内,由于规定了跳跃的方向性（从左到右）
 *      可以使用"坐标型动态规划"自底向上（从右至左）来计算
 *
 *      definition:     dp[i] 表示从 i 出发跳到终点需要最少多少步
 *      induction rule: dp[i] = min(dp[j] + 1 or 0)
 *                 j 是 i 可以跳到或者直通的点, 跳过去的话步数+1，直通的话步数 + 0
 *      init:           dp[1.......n-1] = ∞, dp[n] = 0
 *       res:            dp[1]
 *
 * 三. DFS                           【尽量别用】
 * 四. BFS2: best first search       【很少出现】
 *
 * -->技巧：能用BFS的地方就尽量别用DFS
 * */

/**
 * 1.BFS1(bread first search) -->用于全正数，权重相同graph求shortest path
 * 用bread first search求shortest distance
 * 前提：only when all grah edge cost in the graph are uniform, we can use BFSI find the shortest path
 * 用接下来的code求一个点到另一个target点的shortest path的理论基础：
 * 使用前提：
 * 1.weight of all edges都是正数
 * 2.the weight of all edges are equal每一条边的权重相同
 */

/************************************************模板************************************************************8*/
/**********************************1. 权重全部>0, 权重unit ：使用基本BFS1解决shortest path模板************************************/
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

/**
 * BFSI只能简单图的shortest path问题：解权重unit，即第几层访问到该节点=到达该节点的最短路径
 * 但是遇到复杂图，unit不一样的时候，简单的BFS1就无法应用了，因为此时可能通过三层访问到比通过两层访问到的路径更短
 * 此时解决方法是：SPFA(Shortest Path Fast Algorithm)
 *
 *
 * SPFA(Shortest Path Fast Algorithm) :
 * 这个算法实际上是基于 BFS 算法的一个拓展
 * 1.基本写法：仍然使用queue
 *           如果在第三层中发现一个第二层中访问过的节点，但是此时找到的路径更短 就丢回队列——再从这个节点出发往下拓展（即会多次generate）
 * 2.优化写法：使用 Heap(PriorityQueue) 来替换 Queue
 *           这样能够更快的找到最短路径
 * */


/**********************************1. 权重全部>0, 权重非unit ：使用双层BFSI解决shortest path模板************************************/
//以1565 · Modern Ludo I为例
//外层 BFS 做最短路径，内层 BFS 找连通块。
class 双层BFS {
    public int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(1, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            int limit = Math.min(node + 7, length + 1);
            for (int neighbor = node + 1; neighbor < limit; neighbor++) {
                List<Integer> connectedNodes = getUnvisitedNodes(graph, distance, neighbor);
                for (int connectedNode: connectedNodes) {
                    distance.put(connectedNode, distance.get(node) + 1);
                    queue.offer(connectedNode);
                }
            }
        }

        return distance.get(length);
    }

    private Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            graph.get(from).add(to);
        }
        return graph;
    }

    private List<Integer> getUnvisitedNodes(Map<Integer, Set<Integer>> graph,
                                            Map<Integer, Integer> distance,
                                            int node) {
        List<Integer> unvisitedNodes = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            if (distance.containsKey(currentNode)) {
                continue;
            }
            unvisitedNodes.add(currentNode);
            for (int neighbor: graph.get(currentNode)) {
                if (!distance.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    unvisitedNodes.add(neighbor);
                }
            }
        }
        return unvisitedNodes;
    }
}

/**********************************1. 权重全部>0, 权重非unit ：使用夹心BFSI解决shortest path模板************************************/
//以1565 · Modern Ludo I为例
//两个queue交替
class 夹心BFSI {
    public int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);

        List<Integer> queue = new ArrayList<>();
        queue.add(1);
        Map<Integer, Integer> distance = new HashMap<>();
        distance.put(1, 0);

        while (!queue.isEmpty()) {
            List<Integer> nextQueue = new ArrayList<>();
            for (int i = 0; i < queue.size(); i++) {
                int node = queue.get(i);
                for (int directNode: graph.get(node)) {
                    if (distance.containsKey(directNode)) {
                        continue;
                    }
                    distance.put(directNode, distance.get(node));
                    queue.add(directNode);
                }
            }
            for (int i = 0; i < queue.size(); i++) {
                int node = queue.get(i);
                int limit = Math.min(node + 7, length + 1);
                for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                    if (distance.containsKey(nextNode)) {
                        continue;
                    }
                    distance.put(nextNode, distance.get(node) + 1);
                    nextQueue.add(nextNode);
                }
            }
            queue = nextQueue;
        }

        return distance.get(length);
    }

    private Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            graph.get(from).add(to);
        }
        return graph;
    }
}



/**********************************SPFA shortest path基本模板（queue）************************************/
//以1565 · Modern Ludo I为例
class SPFA {
    public int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            distance.put(i, Integer.MAX_VALUE);
        }
        distance.put(1, 0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nextNode: graph.get(node)) {
                if (distance.get(nextNode) > distance.get(node)) {
                    distance.put(nextNode, distance.get(node));
                    queue.offer(nextNode);
                }
            }
            int limit = Math.min(node + 7, length + 1);
            for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                if (distance.get(nextNode) > distance.get(node) + 1) {
                    distance.put(nextNode, distance.get(node) + 1);
                    queue.offer(nextNode);
                }
            }
        }

        return distance.get(length);
    }

    private Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            graph.get(from).add(to);
        }
        return graph;
    }
}


/**********************************SPFA shortest path优化模板（pq）************************************/
//以1565 · Modern Ludo I为例
class Pair {
    int distance, node;
    Pair(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}
class SPFA2 {

    public int modernLudo(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = buildGraph(length, connections);

        Queue<Pair> queue = new PriorityQueue(
                new Comparator<Pair>() {
                    public int compare(Pair p1,Pair p2) {
                        return p1.distance - p2.distance;
                    }
                }
        );
        queue.offer(new Pair(0, 1));
        Map<Integer, Integer> distance = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            distance.put(i, Integer.MAX_VALUE);
        }
        distance.put(1, 0);
        while (!queue.isEmpty()) {
            int dist = queue.peek().distance;
            int node = queue.peek().node;
            queue.poll();
            for (int nextNode: graph.get(node)) {
                if (distance.get(nextNode) > dist) {
                    distance.put(nextNode, dist);
                    queue.offer(new Pair(dist, nextNode));
                }
            }
            int limit = Math.min(node + 7, length + 1);
            for (int nextNode = node + 1; nextNode < limit; nextNode++) {
                if (distance.get(nextNode) > dist + 1) {
                    distance.put(nextNode, dist + 1);
                    queue.offer(new Pair(dist + 1, nextNode));
                }
            }
        }
        return distance.get(length);
    }

    private Map<Integer, Set<Integer>> buildGraph(int length, int[][] connections) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= length; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            graph.get(from).add(to);
        }
        return graph;
    }
}



