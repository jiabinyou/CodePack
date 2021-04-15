package Graph.BFS2.Path_MINMAX_ProblemSeries;
public class A_PathMinMax问题小结 {
}
/**
 * PathMinMax问题是经典shortestPath问题的变形，所求的是：
 * 从init到goal node的所有path中，最小的最大值点(权重)/最大的最小值点
 * （解释：每一条路径都是由多个点构成的，每两个点之间都会有一段距离，
 *  在一条路径上找出其中距离最大的那一条边，然后与其他路径的最大边进行比较，找出最大边最小的值）
 *
 * 解决这一类问题有三种常见算法：
 * 1.Floyd
 * 2.最小生成树Kruskal
 * 3.Dijkstra(BFS2)
 *   因为Dijkstra存两点间距离的ds，一般都是存两点之间所有路径中互相到达需要的最短路径值，
 *   而现在只需要改成存两点到达需要的最大边的最小值。
 *
 * 代表性例题：
 * frogger
 * Heavy Transportation
 * swim in rising wate
 * .......
 * */

