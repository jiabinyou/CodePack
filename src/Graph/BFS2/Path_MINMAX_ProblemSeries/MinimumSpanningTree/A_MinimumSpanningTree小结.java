package Graph.BFS2.Path_MINMAX_ProblemSeries.MinimumSpanningTree;
import java.util.*;
/**使用条件：
 *  * //An undirected graph is a tree if it satisfies 2 of the below 3 conditions
 *  * //1. no cycle
 *  * //2. connected
 *  * //3. |E| = |V| - 1
 *  这里如果一个题目要求生成一个graph，并且这个graph是no cycle + connected，就说明最后生成的是一个tree；
 *  并且此时要求建树的前提是使用minimum cost
 *  此时就可以直接使用minimum spanning tree算法
 * */



/**minimum spanning tree实现方法一.Kruskal算法
 * 一个一个加edge，按照edge逐步建立tree ->适用于点多边少graph
 * TC = O(Elog(V))  因为是tree，所以e~v
 * 具体做法：
 * Kruskal的基本思路是贪心（Greedy）。利用边集求最小生成树。
 * 1.首先对边集edges排序，然后遍历edges，利用并查集(Union-Find Set)对边的端点进行合并。
 * 2.若两端点不在同一个集合中，则将两端点进行merge，并记录权重。
 *  */

/**以ConnectingCitiesWithMinimumCost题目为例*/
public class A_MinimumSpanningTree小结 {
    class Node {
        int workerId;
        int state;
        int cost;
        Node(int workerId, int state, int cost) {
            this.workerId = workerId;
            this.state = state;
            this.cost = cost;
        }
    }

    public int assignBikes(int[][] workers, int[][] bikes) {
        int m = workers.length;
        int n = bikes.length;

        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        minHeap.offer(new Node(0, 0, 0));
        Set<Integer> visited = new HashSet<>();
        int res = 0;
        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            int id = cur.workerId;
            int state = cur.state;
            int cost = cur.cost;
            if (!visited.add(state)) {
                continue;
            }
            if (id == m) { //pruning:此时说明所有worker已经分配结束了
                res = cost;
                break;
            }
            for (int j = 0; j < n; j++) {
                if ((state & (1 << j)) == 0) { /**1右移j位，即m个0的int中，第j位*/
                    int newState = state | (1 << j);
                    int newCost = cost + getDistance(workers[id], bikes[j]);
                    minHeap.offer(new Node(id + 1, newState, newCost));
                }
            }
        }
        return res;
    }

    private int getDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }
}

/**minimum spanning tree实现方法一.Prim算法
 *基于dijstra >适用于点少边多
 * 一个一个加点
 * any node 开始， run dirjstra,
 * O（V + E) * log（V） ~~（因为是tree）--》 最终O(2V)log（V）
 * */

class A_MinimumSpanningTree小结Prim {
    class Node {
        int house;
        int cost;
        Node(int house, int cost) {
            this.house = house;
            this.cost = cost;
        }
    }
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        for (int i = 0; i < wells.length; i++) {
            Node node = new Node(i + 1, wells[i]);
            //为什么这行不对:需要把起始点全部包括进去
            // if (minHeap.isEmpty()) {
            //     minHeap.offer(node);
            // }
            minHeap.offer(node);
            graph.computeIfAbsent(0, k -> new ArrayList<>()).add(node);
        }

        for (int[] pipe : pipes) {
            int src = pipe[0];
            int dest = pipe[1];
            int cost = pipe[2];
            graph.computeIfAbsent(src, k -> new ArrayList<>()).add(new Node(dest, cost));
            //为什么要加这一行？
            //因为这是无向图，如果不加这一行，图中会少边，比如2 可以通过另外一条Cost 比较小的edge 被generate 出来
            //但是少边的话，就有可能通过一条cost 比较大的edge 被generate 出来
            graph.computeIfAbsent(dest, k -> new ArrayList<>()).add(new Node(src, cost));
        }

        Set<Integer> visited = new HashSet<>();
        //因为是mark visited at expansion, 所以并没有visited.add() 这一步
        //如果加上的话，是mark visited at generate,因为起始点需要自己被自己generate出来
        int minCost = 0;
        while (!minHeap.isEmpty()) {
            Node cur = minHeap.poll();
            if (!visited.add(cur.house)) {// mark visited at expansion
                continue;
            }
            minCost += cur.cost;
            for (Node nei : graph.getOrDefault(cur.house, new ArrayList<>())) {
                minHeap.offer(nei);
            }
            //为什么这行不加也可以？
            //  因为每个点只会被遍历一次，如果这个点之前已经被visited过了就不会重复被遍历
            // if (visited.size() == wells.length) {
            //     break;
            // }
        }

        return minCost;
    }
}

