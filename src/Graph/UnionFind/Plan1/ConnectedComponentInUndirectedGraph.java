package Graph.UnionFind.Plan1;

/**
 * 这道题别看题目给的例子，乱七八糟。其实就看成：
 * 静态graph，求所有connected area node：因为是静态graph + travese node + connectivity
 * 可用：
 * DFS / BFS / UF
 * */

import java.util.*;

/**Sol1.Union Find*/
public class ConnectedComponentInUndirectedGraph {
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        UnionFind uf = new UnionFind();
        //build UF
        for (UndirectedGraphNode node : nodes) {
            uf.add(node.label);
        }
        //Union
        for (UndirectedGraphNode node : nodes) {
            for (UndirectedGraphNode nei : node.neighbors) {
                uf.union(node.label, nei.label);
            }
        }
        //buid res
        /**important: 将各个集合从uf中取出来处理的过程*/
        Map<Integer, List<Integer>> map = new HashMap<>();  //先用map<root, 该集合所有node>记录res
        for (int curNode : uf.father.keySet()) {  //<key : curNode, val: root>
            int root = uf.find(curNode);
            map.putIfAbsent(root, new ArrayList<>());
            map.get(root).add(curNode);
        }
        List<List<Integer>> res = new ArrayList<>(map.values()); //map中所有value放进去就是res
        for (List<Integer> list : res) {   //将每个集合node拿出来还需要sort一下
            Collections.sort(list);
        }
        return res;
    }

    static class UnionFind {   /**推荐将class UnionFind写成static，放在大class内的用法，这样obj就可以直接调用class UnionFind的函数和field*/
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

class UndirectedGraphNode {
      int label;
      ArrayList<UndirectedGraphNode> neighbors;
      UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
  }


/**Sol2. BFS
 这道题最适合的解法其实是BFS, 正好使用BFS，每一层遍历完就得到了一个connected area，最后当前层sort一下即可
 */
class ConnectedComponentInUndirectedGraphSol2 {
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nodes == null || nodes.size() == 0) {
            return null;
        }
        //BFS
        Set<UndirectedGraphNode> visited = new HashSet<>();
        for(UndirectedGraphNode node : nodes){
            if(visited.add(node)){  //单独mark visit root
                List<Integer> curLayer = bfs(node, visited);
                res.add(curLayer);
            }
        }
        return res;
    }

    private List<Integer> bfs(UndirectedGraphNode node, Set<UndirectedGraphNode> visited) {
        Queue<UndirectedGraphNode> q = new LinkedList<>();
        List<Integer> curLayer = new ArrayList<>();
        q.offer(node);  //init root for BFS
        curLayer.add(node.label); //单独process root
        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            for (UndirectedGraphNode nei : cur.neighbors) {
                if (visited.add(nei)) {  //mark visit at generate
                    q.offer(nei);
                    curLayer.add(nei.label); //process at generation
                }
            }
        }
        Collections.sort(curLayer);
        return curLayer;
    }
}