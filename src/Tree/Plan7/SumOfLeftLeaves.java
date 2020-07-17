package Tree.Plan7;

import Tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 Sol1.BFS
 只有走左子树的leaf时候才累加res
 */
public class SumOfLeftLeaves {
    public int sumOfLeftLeaves(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        int res = 0;
        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left != null) {
                if (cur.left.left == null && cur.left.right == null) {  //检查是叶子节点才累加
                    res += cur.left.val;
                }
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
        return res;
    }
}

/**
 Sol2.DFS
 traverse vertex即可，可优先设计经典版本pure recursion，看是否可行
 ,但是发现需要判断parent与child关系，从而得知是否是left child
 可直接设计side effect，使用经典版本backTracking
 */
class SolSLL2 {
    int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        backTracking(root, null);
        return res;
    }

    private void backTracking(TreeNode root, TreeNode prevNode) {
        //base case
        if (root == null) {
            return;
        }
        //update cur path
        if (root.left == null && root.right == null && prevNode != null && root == prevNode.left) {
            res += root.val;
        }
        //recursion
        backTracking(root.left, root);
        backTracking(root.right, root);
    }
}
