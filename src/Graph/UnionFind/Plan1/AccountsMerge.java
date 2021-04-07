package Graph.UnionFind.Plan1;
import java.util.*;

/**
 * Sol2.Union Find
 * */
/**
 * 思路
 * 因为一个name可以对应多个email，而我们实际需要合并的是email set。将name和email放在一起看容易混乱，这里的思路是：
 * 将accountList中的idx当做是并查集的root idx，将提取出来的所有email先连接到对应的root idx上，再根据是否有相同的email将集合进行merge，
 * 最后将每个集合的email排序后，手动在开头添加上name，最后输出。
 * step 1.（uf.add）
 *       以accountList中的每个idx当做是并查集的root idx，分别建立一个集合
 * step 2.（uf.union）
 *        先忽略name，并将每行所有的email adress提取出来，做两件事情
 *        a)使用map<email, idx>记录下每个email对应的idx（后面添加name时候需要用到）
 *        b) （关键！！）如果一旦查到，正在遍历的email在map中已经存在了，说明这个email所在的这一行的所有email（curIdx作为root的集合），
 *            应该和之前email所在的那一行所有email（preIdx作为root的集合）属于同一个集合
 *            使用uf.union(preIdx, curIdx)将这两个集合合并
 *
 * step 3.(uf.find)
 *       因为要求每个集合的所欲email sorted，使用uf.find找到所有合并好后的集合，取出他们对应的所有email，放进treeSet中进行排序
 * step 4.手动为每个集合开头添加name，并放入List<List<>>存储，输出即可
 * */
public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        //sanity check
        if (accounts == null || accounts.size() == 0) {
            return res;
        }
        UnionFind uf = new UnionFind();
        Map<String, Integer> emailToIdx = new HashMap<>(); //<email, rootIdx>
        int size = accounts.size();
        for (int i = 0; i < size; i++) {
            uf.add(i);     //step 1:为accounts的每一行建立以idx i为根的集合
            //step 2: 将含有相同email的集合union到一起
            List<String> curRow = accounts.get(i);
            for (int j = 1; j < curRow.size(); j++) {  //忽略name，只提取email
                String email = curRow.get(j);
                if (emailToIdx.containsKey(email)) {
                    uf.union(emailToIdx.get(email), i);  //<emailToIdx.get(email) = preIdx, i = curIdx>
                } else {
                    emailToIdx.put(email, i);
                }
            }
        }

        //step 3: 使用uf.fin找到union后的集合的root，将属于同一集合所有email，放入root位置的treeSet排序
        Map<Integer, TreeSet<String>> map = new HashMap<>(); //<idx, TreeSet<String>>
        for (int i = 0; i < size; i++) {
            int root = uf.find(i);  //find final root for i集合
            if (!map.containsKey(root)) {
                map.put(root, new TreeSet<>());  //root为根的集合如果还没建立，就新建treeset
            }
            TreeSet<String> treeSet = map.get(root);  //如果已经建立，就直接取出root位置treeSet
            //取出i集合的所有email，继续放入root位置的treeSet中排序
            for (int j = 1; j < accounts.get(i).size(); j++) {
                treeSet.add(accounts.get(i).get(j));
            }
            map.put(root, treeSet);
        }

        //step 4: 为每个集合开头添加name
        for (Map.Entry<Integer, TreeSet<String>> entry : map.entrySet()) {
            int idx = entry.getKey();
            String name = accounts.get(idx).get(0);
            TreeSet<String> allEmail = entry.getValue();  //同一个集合的所有email

            List<String> curList = new ArrayList<>();
            curList.add(name);  //开头添加name
            curList.addAll(allEmail);
            res.add(curList);
        }
        return res;
    }

    /**UF只需要用到father map，sizeOfUnion(map)和numOfUnion(feild)都不需要*/
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


/**
 * Sol1.DFS
 * 难点：难看出题目想要求的本质
 * 本质：可以将name单独抛开，只看email，实际上就是求以每个email为一个graph node，将connected area用sort方式还原出来
 *       最后再加上前面的name即可（name是迷惑项）
 * 所以实质：unconnected，undirected ， may cylic graph， 求connected area相关
 * --> traverse vertex
 * --> DFS1
 * 需要build 2G graph, extra mark visited ds
 */

class AccountsMergeSol2 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        //sanity check
        if (accounts == null || accounts.size() == 0) {
            return res;
        }
        Map<String, List<String>> graph = buildGraph(accounts);  //<email, all neighbor emails>
        /**难点：unconnected graph，以原input信息为起点，在自己建的graph上跑dfs*/
        Set<String> visited = new HashSet<>();
        for (List<String> account : accounts) {
            String name = account.get(0);
            String node = account.get(1);  //第一个email作为curNode
            if (account.size() > 1 && visited.add(node)) {  /**单独mark visit root*/
                List<String> curPath = new ArrayList<>();  //dfs寻找与curNode相连的xonnected area中所有email
                dfs(graph, node, curPath, visited);
                Collections.sort(curPath);
                curPath.add(0, name);    //首位add name
                res.add(new ArrayList<>(curPath));  //add emails
            }
        }
        return res;
    }

    private void dfs(Map<String, List<String>> graph, String node, List<String> curPath, Set<String> visited) {
        //base case (root中检查过了)
        //update curPath
        curPath.add(node);
        //recursion
        for (String nei : graph.get(node)) {
            if (visited.add(nei)) {  /**mark visited at generation*/
                dfs(graph, nei, curPath, visited);
            }
        }

    }

    private Map<String, List<String>> buildGraph(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> account : accounts) {
            if (account.size() > 1) {
                for (int i = 1; i < account.size(); i++) {
                    graph.putIfAbsent(account.get(i), new ArrayList<>());
                    if (i >= 2) {   //避开name，只放email
                        graph.get(account.get(i)).add(account.get(i - 1));  //前后相邻的两个email互相指向edge
                        graph.get(account.get(i - 1)).add(account.get(i));
                    }
                }
            }
        }
        return graph;
    }
}

