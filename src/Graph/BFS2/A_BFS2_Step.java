package Graph.BFS2;
import java.util.*;
public class A_BFS2_Step {

}

/**BFS2
 * 本质：greedy算法
 * 常见：Dijkstra
 * 用法指导：在判断条件符合能用mark visit at generation时候，就尽量mark visit at generation，如果条件不符合就mark visit at expansion
 * 偷懒写法：mark visited at expansion永远都不会出错，可直接选用这个写法
 */

/**Dijkstra
 *  应用前提：path权重全正数 （如果权值为负数可以使用 Bellman-Ford 算法）
 *  TC: O(ElogV) -> 因为traverse一遍all edge，每遍历一条edge，就将一组vertex相关信息放入heap进行排序,每个顶点耗费loh(v),共进行E次
 *  SC:
 *  应用：
 *      1.用于计算一个节点到其他所有节点的最短路径
 *      2.用于计算一个节点到其他所有节点的路径中，最小的最大值点(权重)/最大的最小值点
 *      (充分理解：dikastra算法中，每两点之间记录的都是到彼此的最短edge)
 *  原理：
 * Dijkstra算法 在实现上与 BFS模板 十分类似，主要区别有如下两点：
 *  1. 使用了 PriorityQueue 来替代 Queue；
 *  2. 对 Node(Position) 进行记录的时间不同；
 *     在 BFS 中，我们在将 node 放入队列的时候，我们就会在 distanceMap 中记录对应的数据；
 *     但是在 Dijkstra 中，我们是在 node 从队列中取出来的时候，记录对应的数据。
 *     这是因为：
 *     我们知道 distanceMap 记录的是从起点到当前位置的 最短路径，
 *     而在 Dijkstra 中，最短路径是在 node 从 Priority Queue 被取出来的时候决定的。
 *  3. 将一个点 poll 出来时，我们需要在这是判断这个点是否已经处理过；
 *     这是因为由第二点可知：只有当该节点被 poll 出来的时候，distances才会记录这个点被处理过
 *     所以有可能一个点被 put 进来多次，但是只要被处理过一次，
 *     后面的到达该节点的路径就不是最优解，需要ignore掉（这也就是Dijkstra算法的贪心点所在）
 *
 * 1.BFS2需不需要dedup？即mark visited？
 * 根本原因：对于graph中的每一个vertex，走不同path的时候，可能会generate同一个vertex多次，并且计算出不一样的cost。
 * 最终目标：最终我们希望每一个vertex只会expand一次，并且expand出来时候携带的是v对应的最小的cost。
 *
 * 2.BFS2可以在什么时候进行dedup？
 * 根据根本原因，我们可以知道：
 * 对于BFS2, dedup at expand使用起来是一定正确的；
 *  	      dedup at generation需要符合下面两个条件：
 * a).cost will never be changed even it could be from different paths.
 * b).later generation won't give a better estimated cost.
 * 如果题目的这两个条件符合，可以优先考虑使用dedup at generation，因为这样可以保证对于每一个vertex v，只会generate一次，可以节省空间和时间
 */

/**Dijkstra伪代码
 * initialize rv: {start vertex}
 * for each round: {
 *      // 1. pick the smallest cost vertex from rv{} to expand
 *      rv. v = rv.poll Min();     //expand
 *      //看所有v的edge>=0的neighbor, 判断属于哪个case：做do nothing?update?generate?
 *      for (neighbor n : v.neighbors) {      //generate
 *          //在generate all neighbors n过程中 .
 *          //计算每个n-->start node，经过v的shortest path,即minCost
 *          v.cost + weight(v -> n)
 *          if (n already expanded before) do nothing;                      //case 1：do nothing
 *          if (n not in rv) insert n to rv;                                //case 2：generate
 *          if (n already in rv and cur cost < prev cost) update n in rv;   //case 3：update
 *      }
 * }
 * */



/**Dijkstra实现方法1:
 * 使用minHeap，遇到case3时候，不是使用update（），而是和case2 做一样的处理，直接将带着新的minCost的n插入minHeap中。
 * We don't do update, we just insert a new element into the min Heap each time,
 * but we ensure each element is only expanded once, please don't confuse this with dedupe at generation class
 */
/** mark visit at expansion*/

/**Dijkstra模板：
 * 见链接：https://docs.google.com/document/d/1__bH23SN6oQHkkcAhenwfZxjJhKIREZS-v9LLbrynyQ/edit#
 * **/

//class Vertex {
//    int idx;
//    List<Vertex> neighbors;
//    public Vertex(int idx) {
//        this.idx = idx;
//    }
//}
//
//class Element {
//    Vertex v;
//    int cost;
//    public Element(Vertex v, int cost) {
//        this.v = v;
//        this.cost = cost;
//    }
//}
//
//class DijkstraModel {
//    public void DijkstraModel1_1(Vertex start) {
//        PriorityQueue<Element> minHeap = new PriorityQueue<>(); // min heap comparing cost.
//        Map<Vertex, Integer> generated = new HashMap<>(); // record all the vertices generated at least once and its corresponding best cost.
//        Set<Vertex> expanded = new HashSet<>(); // record all the vertices already expanded （mark visited using expanded）
//
//        minHeap.offer(new Element(start, 0));
//        while (!minHeap.isEmpty()) {
//            //for each round// terminate condition(kth, start to goal, ......) {
//            //step 1. expand the smallest
//            Element curMin = minHeap.poll();
//            if (!expanded.add(curMin.v)) {
//                continue;
//            }
//            // step 2. generate all neighbors of v
//            for (Vertex nei : curMin.v.neighbors) {
//                // case 1 if already expanded --> ignore
//                if (expanded.contains(nei)) {
//                    continue;
//                }
//            //case 2 if not generated yet.
//            int cost = curMin.cost + nei.cost;
//            if (!generated.containsKey(nei)) {
//                minHeap.offer(new Element(nei, cost));
//                generated.put(nei, cost);
//            }
//            //case 3 --> need a data structure to record the best cost for the already generated vertices
//            else if (cost < generated.get(n)) {
//                minHeap.offer(<n, cost>);
//                generated.put(n, cost);
//            }
//// --> optimization: the above two branches can be combined.
//        }
//
//    }
//}
//
//
