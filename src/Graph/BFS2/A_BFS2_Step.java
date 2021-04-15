package Graph.BFS2;


/**BFS2
 * 本质：greedy算法
 * 常见：Dijkstra （）
 */

/**
 * Dijkstra's Algorithm
 * https://www.youtube.com/watch?v=lAXZGERcDf4
 * 其实质是一个贪心算法，有向图和无向图中均可使用，但是权值不能够是负数。
 *
 *  应用前提：
 *       path权重全正数 （如果权值为负数可以使用 Bellman-Ford 算法）
 *
 *  TC:
 *       O(ElogV)
 *       ->因为traverse一遍all edge，每遍历一条edge，就将一组vertex相关信息放入heap进行排序,每个顶点耗费loh(v),共进行E次
 *  SC:
 *
 *
 *  应用：
 *      1.用于计算一个节点到其他所有节点的最短路径
 *      2.用于计算一个节点到其他所有节点的路径中，最小的最大值点(权重)/最大的最小值点
 *      (充分理解：dikastra算法中，每两点之间记录的都是到彼此的最短edge)
 *
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
 */
public class A_BFS2_Step {

}
