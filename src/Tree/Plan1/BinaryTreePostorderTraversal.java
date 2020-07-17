package Tree.Plan1;

import Tree.TreeNode;
import java.util.*;

public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.offer(root);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (prev == null || prev.left == cur || prev.right == cur) {
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    stack.pop();
                    res.add(cur.val);
                }
            } else if (prev == cur.left) {
                if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    stack.pop();
                    res.add(cur.val);
                }
            } else {
                stack.pop();
                res.add(cur.val);
            }
            prev = cur;
        }
        return res;
    }
}
