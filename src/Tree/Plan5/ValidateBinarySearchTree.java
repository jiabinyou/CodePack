package Tree.Plan5;

import Tree.TreeNode;

public class ValidateBinarySearchTree {
    boolean isBST = true;
    public boolean isValidBST(TreeNode root) {
        //sanity check
        if (root == null) {
            return true;
        }
        backTracking(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        return isBST;
    }

    private void backTracking(TreeNode root, double lB, double rB) { //left border, right border
        //base case
        if (root == null) {
            return;
        }
        //update glb
        if (root.val <= lB || root.val >= rB) {
            isBST = false;
        }
        //recursion
        backTracking(root.left, lB, root.val);
        backTracking(root.right, root.val, rB);
    }
}

/**
 * 用return value优化backTracking
 */
class SolVBST2 {
    public boolean isValidBST(TreeNode root) {
        //sanity check
        if (root == null) {
            return true;
        }
        return backTracking(root, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    private boolean backTracking(TreeNode root, double lB, double rB) { //left border, right border
        //base case
        if (root == null) {
            return true;
        }
        //update glb
        boolean curIsBST = true;
        if (root.val <= lB || root.val >= rB) {
            curIsBST = false;
        }
        //recursion
        curIsBST &= backTracking(root.left, lB, root.val);
        curIsBST &= backTracking(root.right, root.val, rB);
        //return val
        return curIsBST;
    }
}