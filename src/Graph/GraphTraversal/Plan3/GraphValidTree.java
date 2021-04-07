package Graph.GraphTraversal.Plan3;
import java.util.*;

/**
 * //An undirected graph is a tree if it satisfies 2 of the below 3 conditions
 * //1. no cycle
 * //2. connected
 * //3. |E| = |V| - 1
 */

/**Sol10 : check |E| = |V| - 1 和 no cycle条件
 * Union Find to check if a conneted graph
 * */
class GraphValidTreeSol0 {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        //sanity check
        if (edges == null) {
            return false;
        }

        //check |E= == |V| - 1
        if (edges.length != n - 1) {
            return false;
        }

        //check if a connected graph (UF)
        UnionFind uf = new UnionFind();
        for (int i = 0; i < n; i++) {
            uf.add(i);
        }
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.numOfSet == 1;
    }


    static class UnionFind {   /**推荐将class UnionFind写成static，放在大class内的用法，这样obj就可以直接调用class UnionFind的函数和field*/
    private Map<Integer, Integer> father;
        private int numOfSet = 0;
        public UnionFind() {
            father = new HashMap<Integer, Integer>();
            numOfSet = 0;
        }

        public void add(int x) {
            if (father.containsKey(x)) {
                return;
            }
            father.put(x, null);
            numOfSet++;
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                father.put(rootX, rootY);
                numOfSet--;
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




/**
 * Sol1: check |E| = |V| - 1 和 no cycle条件
 * traverse vertex， 并且需要V ds, 记录0到n-1这些graph node中的遍历情况
 * -->boolean[] visited
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        //sanity check
        if (edges == null) {
            return false;
        }

        //check |E| = |V| - 1
        int[] edgeCount = new int[1];
        List<List<Integer>> graph = buildGraph(edges, n, edgeCount);
        if (edgeCount[0] != n - 1) {
            return false;
        }
        //dfs check if has cycle
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i] && hasCycle(graph, i, -1, visited)) {
                return false;
            }
        }
        return true;
    }

    /**DFS1 check if undirected graph has cycle,重点！！check prev != nei才可,表示不是自己指向自己的edge*/
    private boolean hasCycle(List<List<Integer>> graph, int node, int prev, boolean[] visited) {
        //base case
        if (visited[node]) {
            return true;
        }
        //mark visited at expansion
        visited[node] = true;
        //recursion
        for (int nei : graph.get(node)) {
            if (prev != nei && hasCycle(graph, nei, node, visited)) {
                return true;
            }
        }
        return false;
    }

    private List<List<Integer>> buildGraph(int[][] edges, int n, int[] edgeCount) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            edgeCount[0]++; /**undirected graph,虽然指向是双向的，但是仍然是一条edge*/
        }
        return graph;
    }
}

/**
 * Sol1: check |E| = |V| - 1 和 connected条件
 * traverse vertex， 并且需要V ds, 记录0到n-1这些graph node中的遍历情况
 * boolean[] visited
 */
/**
 * 和上面sol唯一区别：这里我们需要按照connected area的方法遍历，只进行一遍dfs，最后比较看visited node数量是否是edgeCount
 * 即，必须是n,才可能是tree
 * 所以这里V ds换一个能够查长度的即可：Set<Integer>足够
 */
class SolGVT2 {
    public boolean validTree(int n, int[][] edges) {
        //sanity check
        if (edges == null) {
            return false;
        }

        //check |E| = |V| - 1
        int[] edgeCount = new int[1];
        List<List<Integer>> graph = buildGraph(edges, n, edgeCount);
        if (edgeCount[0] != n - 1) {
            return false;
        }
        //dfs check if connected graph
        Set<Integer> visited = new HashSet<>();
        dfs(graph, 0, visited); //默认从node 0开始
        if (visited.size() != n) {
            return false;
        }
        return true;
    }

    private void dfs(List<List<Integer>> graph, int node, Set<Integer>  visited) {
        //base case
        if (visited.contains(node)) {
            return;
        }
        //mark visited at expansion
        visited.add(node);
        //recursion
        for (int nei : graph.get(node)) {
            dfs(graph, nei, visited);
        }
    }

    private List<List<Integer>> buildGraph(int[][] edges, int n, int[] edgeCount) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
            edgeCount[0]++; /**undirected graph,虽然指向是双向的，但是仍然是一条edge*/
        }
        return graph;
    }
}
