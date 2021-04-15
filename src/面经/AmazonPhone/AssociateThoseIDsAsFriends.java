package 面经.AmazonPhone;

import java.util.HashMap;
import java.util.Map;

//完全就是union find
public class AssociateThoseIDsAsFriends {
    private Map<Integer, Integer> father;

    public AssociateThoseIDsAsFriends() {
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

    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
