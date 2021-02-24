package Graph.UnionFind.Plan1;

/**
 * SOL.UF
 * 注意：这道题要求变成tree，根据题目要求，查cycle即可
 * */

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        //sanity check
        if (edges == null || edges.length == 0) {
            return new int[0];
        }
        //init all node rootIdx
        int[] id = new int[edges.length + 1];
        for (int i = 1; i <= edges.length; i++) {
            id[i] = i;
        }
        //检查同一edge两个端点，如果根rootIdx已经相同，说明之前已经联通，走到这一步成环了，返回即可
        for (int[] edge : edges) {
            int rootu = findRoot(id, edge[0]);
            int rootv = findRoot(id, edge[1]);
            if (rootu == rootv) {
                return edge;
            }
            id[rootv] = rootu; //如果不是，就正常union即可
        }
        return new int[0];
    }

    private int findRoot(int[] id, int a) {
        if (id[a] != a) {
            id[a] = findRoot(id, id[a]);
        }
        return id[a];
    }
}

