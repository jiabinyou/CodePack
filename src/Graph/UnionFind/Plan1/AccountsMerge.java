package Graph.UnionFind.Plan1;
import java.util.*;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> result = new ArrayList<>();
        if (accounts == null || accounts.isEmpty()) {
            return result;
        }
        UnionFind uf = new UnionFind(accounts.size());
        /**每个email是一个graph node，给每个email init初始root idx*/
        Map<String, Integer> emailToIndex = new HashMap<>();  //<email, rootIdx>
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {  //遍历所有email（剔除name）
                String email = accounts.get(i).get(j);
                if (emailToIndex.containsKey(email)) {
                    int rootIndex = emailToIndex.get(email);
                    uf.union(rootIndex, i); /**关键：当前area编号是i，emailToIndex中找到的编号是rootIndex，UNION起来*/
                } else {
                    emailToIndex.put(email, i);  //将i作为每行email的初始rootIdx，因为同一行email是connected的
                }
            }
        }
        /**将每个connected area中所有元素取出，放进TreeSet中排序*/
        Map<Integer, TreeSet<String>> users = new HashMap<>(); //<rootIdx, TreeSet(email)>
        for (int i = 0; i < accounts.size(); i++) {
            int rootIndex = uf.findRoot(i);   //找到每一行，最终对应的rootIdx
            users.putIfAbsent(rootIndex, new TreeSet<>()); //对于该rootIdx，如还没有TreeSet，先建立
            TreeSet<String> cur = users.get(rootIndex);  //如果已经有了，遍历这一行所有email，加入treeSet中
            for (int j = 1; j < accounts.get(i).size(); j++) {
                cur.add(accounts.get(i).get(j));
            }
            users.put(rootIndex, cur);
        }
        /**每个connected area，加上前面的name后输出*/
        for (Map.Entry<Integer, TreeSet<String>> entry : users.entrySet()) {
            List<String> curList = new ArrayList<>();
            curList.add(accounts.get(entry.getKey()).get(0));
            curList.addAll(entry.getValue());
            result.add(curList);
        }
        return result;
    }

    static class UnionFind {
        int[] roots;
        int[] sizes;
        UnionFind(int n) {
            this.roots = new int[n];
            this.sizes = new int[n];
            for (int i = 0; i < n; i++) {
                roots[i] = i;
                sizes[i] = 1;
            }
        }

        int findRoot(int a) {
            if (a != roots[a]) {
                roots[a] = findRoot(roots[a]);
            }
            return roots[a];
        }

        void union(int a, int b) {
            int roota = findRoot(a);
            int rootb = findRoot(b);
            if (roota == rootb) {
                return;
            }
            if (sizes[roota] >= sizes[rootb]) {
                sizes[roota] += sizes[rootb];
                roots[rootb] = roota;
            } else {
                sizes[rootb] += sizes[roota];
                roots[roota] = rootb;
            }
        }
    }
}

