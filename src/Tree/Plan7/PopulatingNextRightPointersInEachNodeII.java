package Tree.Plan7;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Sol.BFS
 * 在expand时候处理：使用next pointer连接parent
 */
public class PopulatingNextRightPointersInEachNodeII {
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

/**
 * Sol2.DFS
 * 使用tail list，每次更新tail list时候连接next pointer
 */
class SolPNRPII2 {
    public Node connect(Node root) {
        //sanity check
        if (root == null) {
            return root;
        }
        List<Node> tail = new LinkedList<>();
        backTracking(root, 0, tail);
        return root;
    }

    private void backTracking(Node root, int level, List<Node> tail) {
        //base case
        if (root == null) {
            return;
        }
        //update cur path
        if (tail.size() == level) {
            tail.add(root);
        } else {
            tail.get(level).next = root;
            tail.set(level, root);
        }
        //recursion
        backTracking(root.left, level + 1, tail);
        backTracking(root.right, level + 1, tail);
    }

}

/**
 * Sol3.BFS 优化掉Queue版本
 * 因为Node定义中有next pointer，实质上不需要queue
 * 在generate时候处理，使用next pointer连接child层
 */
class SolPNRPII3 {
    public Node connect(Node root) {
        //sanity check
        if (root == null) {
            return root;
        }
        Node head = root;
        while (head != null) {            //整颗tree遍历结束
            Node nextHead = null;
            Node nextPrev = null;

            while (head != null) {        //当前level遍历结束
                //expand

                //generate
                if (head.left != null) {
                    if (nextHead == null) {
                        nextHead = head.left;
                    }
                    if (nextPrev != null) {
                        nextPrev.next = head.left;
                    }
                    nextPrev = head.left;
                }
                if (head.right != null) {
                    if (nextHead == null) {
                        nextHead = head.right;
                    }
                    if (nextPrev != null) {
                        nextPrev.next = head.right;
                    }
                    nextPrev = head.right;
                }
                head = head.next;
            }
            head = nextHead;
        }
        return root;
    }
}