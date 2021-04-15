package Graph.BFS2.Path_MINMAX_ProblemSeries.MinimumSpanningTree;

/**这道题实质是"Kruskal（最小生成树）"算法，UF只是这个算法中用到的一步
 * Kruskal（最小生成树）算法简介：
 * Kruskal的基本思路是贪心（Greedy）。利用边集求最小生成树。
 * 1.首先对边集edges排序，然后遍历edges，利用并查集(Union-Find Set)对边的端点进行合并。
 * 2.若两端点不在同一个集合中，则将两端点进行merge，并记录权重。
 *
 * 本题在应用Kruskal之前需要稍作转换：构建虚拟节点n+1，将wells数组转化为每一个村庄1~n与n+1的边。
 *
 * */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**原理解释：
 * 我们将所有住户想象成n个节点，如果能将n个节点都连接起来，并且保证至少一家挖了水井，就可使得所有住户都有水喝。
 * 不过题目要求找到最优连接方案，也就是要知道如何连接节点，以及挖几口井的总花费最少。
 * 我们先来想第一个问题，比如住户A通过某条水管路线通上了水，住户B也通过某条水管通上了水，那么A和B之间是不需要再进行连线的，否则会花费多余的费用。
 * 这样一来，所有连线就不会产生回路，也就是说，--》最终将所有节点接起来之后，应该是一个树形结构。（重要结论！）
 * 不过问题来了，题目给出的pipes 数组貌似并不足以让所有节点都能保证联通，也就是说，最后可能会形成多棵树，每棵树的顶点是挖井的住户。
 * e.g.
 *                      A                       D
 *                 /        \               /       \
 *                 B        C               E       F
 *  (node是住户，edge是pipe)
 *此时我们只需要在A,D各自打上井，就能让所有人家吃上水了。但问题是，这样是最小cost吗？
 * 很显然不一定。这里很关键的一点是：
 * 我们应该尽量让打井cost少，pipe便宜的的住户，作为root，尽量放在树的上面，将整颗树发展起来。
 * 这就意味着：
 * tree node ：住户
 * edge： well / pipe （关键：都看成edge）
 * 我们就可以将所有edge按照cost由小到大排序。然后我们去遍历node建树。
 * 如果遍历到的prev，cur node之间
 *          1.已经有edge（即connected，关键：即用UF查出来有相同根root，），就可以将这两个node用UF union到同一个connected area。
 *          2.没有edge，就按照排序好的，取出最小cost的一个（well/pipe），将这个cost加入总花费中。
 * */
public class OptimizeWaterDistributionInAVillage {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        //sanity check
        if (n == 0) {
            return 0;
        }
        //build uf (// 初始时每个area的根节点为住户自己)
        UnionFind uf = new UnionFind();
        for (int i = 0; i <= n; i++) {
            uf.add(i);
        }
        //build minHeap, 将所有的well cost, pipe cost作为有权重edge放进去后sort
        int[][] edges = new int[pipes.length + wells.length][3];  //edges[j] = [house1j,house2j,costj],和pipes意义一致
        for (int i = 0; i < n; i++) {
            /**关键：将wells的cost按照pipe同样的形式存储。假想有node 0，所有wells[i]表示住户0与住户i + 1之间互通有水的cost*/
            /**最终状态：所有unconnected area的root都会挂到0下面去，意味着他们和水井相连了*/
            edges[i] = new int[]{0, i + 1, wells[i]};   //put all wells cost into edge
        }
        for (int i = n; i < edges.length; i++) {  /**index = n后面开始，放所有pipe cost*/
            edges[i] = pipes[i - n];
        }
        //进行sort
        Arrays.sort(edges, (x, y)->x[2]-y[2]);
        //遍历各个node， build res
        int cost = 0;
        for (int[] edge : edges) {
            int root1 = uf.find(edge[0]);
            int root2 = uf.find(edge[1]);
            //case1.根节点相同，说明两个node已经connected(已经有水),跳过不用管了
            if (root1 == root2) {
                continue;  //此时跳出此循环，后面cost也不会计算了
            }
            //case2:根是0表示：某node已经和水井连接，花费该cost将另一个root和水井连在一起
            if (root1 == 0 || root2 == 0) {
                uf.union(root1, 0);
                uf.union(root2, 0);
            } else {  //case 3: 这两个area之间没有edge，要花费该cost union到一起
                uf.union(root1, root2);
            }
            cost += edge[2];  //只有后两个case有cost
        }
        return cost;
    }

    static class UnionFind {
        private Map<Integer, Integer> father;
        public UnionFind() {
            father = new HashMap<Integer, Integer>();
        }

        public void add(int x) {
            if (father.containsKey(x)) {
                return;
            }
            father.put(x, null);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                father.put(rootX, rootY);
            }
        }

        public int find(int x) {
            int root = x;
            while (father.get(root) != null) {
                root = father.get(root);
            }
            while (x != root) {
                int originalFather = father.get(x);
                father.put(x, root);
                x = originalFather;
            }
            return root;
        }
    }
}
