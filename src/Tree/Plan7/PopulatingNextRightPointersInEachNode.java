package Tree.Plan7;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Sol.BFS
 * 在expand时候处理：使用next pointer连接parent
 */
public class PopulatingNextRightPointersInEachNode {
    public Node connect(Node root) {
        //sanity check
        if (root == null) {
            return root;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Node prev = null;
            for (int i = 0; i < size; i++) {
                //expand
                Node cur = q.poll();
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;
                //generate
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return root;
    }
}

