package Tree.Plan5;

import Tree.TreeNode;

public class BalancedBinaryTree {
    boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        //sanity check
        if (root == null) {
            return true;
        }
        pureRecursion(root);
        return isBalanced;
    }

    private int pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        int l = pureRecursion(root.left);
        int r = pureRecursion(root.right);
        int curHeight = 1 + Math.max(l, r);
        if (Math.abs(l - r) > 1) {
            isBalanced = false;
        }
        return curHeight;
    }
}

/**
 * 简化代码：划分return value区间来代表不同含义
 */
class SolBBT2 {
    boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        //sanity check
        if (root == null) {
            return true;
        }
        return (pureRecursion(root) == -1) ? false : true;
    }

    private int pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        //recursion
        int l = pureRecursion(root.left);
        int r = pureRecursion(root.right);
        //induction rule
        if (l == -1 || r == -1) {  //提前pruning
            return -1;
        }
        int curHeight = 1 + Math.max(l, r); //induction rule
        if (Math.abs(l - r) > 1) {  //提前pruning
            return -1;
        }
        //return val
        return curHeight;
    }
}

