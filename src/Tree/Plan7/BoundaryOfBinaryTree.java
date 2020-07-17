package Tree.Plan7;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BoundaryOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        //sanity check
        if(root == null) {
            return res;
        }
        res.add(root.val);
        backTracking(root.left, true, false, res);
        backTracking(root.right, false, true, res);
        return res;
    }

    private void backTracking(TreeNode root, boolean leftMost, boolean rightMost, List<Integer> res) {
        //base case
        if (root == null) {
            return;
        }
        //preOrder: leftMost + leaf
        if (leftMost || root.left == null && root.right == null) {
            res.add(root.val);
        }
        //recursion
        backTracking(root.left, leftMost, rightMost && root.right == null, res);
        backTracking(root.right, leftMost && root.left == null, rightMost, res);
        //postOrder
        if (!leftMost && !(root.left == null && root.right == null) && rightMost) {
            res.add(root.val);
        }
    }
}
