package Tree.Plan1;

import java.util.*;
/**
 Sol1: recursion
 */
public class NaryTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
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
        res.add(root.val);
        for (Node child : root.children) {
            recursion(child, res);
        }
    }
}

/**
 * Sol2. iterative
 */
class SolNPreT {
    public List<Integer> preorder(Node root) {
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
            for (int i = cur.children.size() - 1; i >= 0; i--) {  //倒着放入stack才能保证res中从左向右
                stack.push(cur.children.get(i));
            }
        }
        return res;
    }
}