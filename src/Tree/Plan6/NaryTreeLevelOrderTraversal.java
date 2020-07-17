package Tree.Plan6;

import Tree.Plan1.Node;
import java.util.*;

public class NaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curLayer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node cur = q.poll();
                curLayer.add(cur.val);
                for (Node child : cur.children) {
                    if (child != null) {
                        q.offer(child);
                    }
                }
            }
            res.add(new ArrayList<>(curLayer));
        }
        return res;
    }
}
