package Graph.UnionFind.Plan1;

/** Union Find
 * 注意：这道题要求变成"rooted tree！！"--》connected， no cycle， one parent
 * 所以即使无环，但是多于一个parent也不行，所以有下面三种cases
Three cases:
1. Every node has only one parent, and there is a cycle which include the root node.
        1
       / \
      2-->3
2. There is a node with two parents, no cycle
        5 <- 1 -> 2
        ^
        |
        4
   （5有两个parent，无cycle）
3. There is a node with two parents, with cycle
    5 <- 1 -> 2
    ^    |
    |    v
    4 <- 3
   （5有两个parent，并且在cycle上）
*/
public class RedundantConnectionII {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] roots = new int[edges.length + 1];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = i;
        }
        int[] candidate1 = null;
        int[] candidate2 = null;
        for (int[] edge : edges) {
            int rootu = findRoot(roots, edge[0]); //find
            int rootv = findRoot(roots, edge[1]);
            if (rootv != edge[1]) {
                candidate1 = edge;//indegree == 2 case, skip union
            } else if (rootu == rootv) {
                candidate2 = edge; //with cycle case, skip union
            } else {
                roots[rootv] = rootu; //union
            }
        }
        if (candidate1 == null) {
            return candidate2;
        }
        if (candidate2 == null) {
            return candidate1;
        }
        for (int[] edge : edges) {
            if (edge[1] == candidate1[1]) {
                return edge;
            }
        }
        return new int[0];
    }

    private int findRoot(int[] roots, int a) {
        if (roots[a] != a) {
            roots[a] = findRoot(roots, roots[a]);
        }
        return roots[a];
    }
}

