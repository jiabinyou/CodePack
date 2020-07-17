package Tree.Plan1;

import java.util.*;
/**
 Sol1. recursion
 */
public class NaryTreePostorderTraversal {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        recursion(root, res);
        return res;
    }

    private void recursion(Node root, List<Integer> res) {
        //base case
        if (root == null) {
            return;
        }

        for (Node child : root.children) {
            recursion(child, res);
        }
        res.add(root.val);
    }
}

/**
 * Sol2. iterative
 */

/**
 * 按照left to right post-order == （pre-order right to left）的逆序 来计算即可
 */
class SolBPostT {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            res.add(cur.val);
            for (Node child : cur.children) {
                stack.push(child);
            }
        }
        Collections.reverse(res);
        return res;
    }
}