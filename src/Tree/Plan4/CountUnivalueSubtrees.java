package Tree.Plan4;

import Tree.TreeNode;

/**
 traverse vertex
 -->优先考虑设计经典版本pure recursion
 */
public class CountUnivalueSubtrees {
    int glbCount = 0;
    public int countUnivalSubtrees(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbCount;
    }

    private boolean pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return true;
        }
        //recursion
        boolean l = pureRecursion(root.left);
        boolean r = pureRecursion(root.right);
        //induction rule + return val
        if (root.left == null && root.right == null) {
            glbCount += 1;
            return true;
        } else if (root.left != null && root.right != null) {
            if (l && r && root.left.val == root.val && root.right.val == root.val) {
                glbCount += 1;
                return true;
            }
        } else if (root.left == null) {
            if (r && root.right.val == root.val) {
                glbCount += 1;
                return true;
            }
        } else { //root.right == null && root.left != null
            if (l && root.left.val == root.val) {
                glbCount += 1;
                return true;
            }
        }
        return false;
    }
}

/**
 * 简化代码
 */
class SolCUS1b {
    int glbCount = 0;
    public int countUnivalSubtrees(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbCount;
    }

    private boolean pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return true;
        }
        //recursion
        boolean l = pureRecursion(root.left);
        boolean r = pureRecursion(root.right);
        //induction rule + return val
        if ((root.left == null && root.right == null) ||
                (root.left != null && root.right != null) && (l && r && root.left.val == root.val && root.right.val == root.val) ||
                (root.left == null) && (r && root.right.val == root.val) ||
                (root.right == null) && (l && root.left.val == root.val)) {
            glbCount += 1;
            return true;
        }
        return false;
    }
}

/**
 * 继续化简代码
*/
class SolCUS1c {
    int glbCount = 0;
    public int countUnivalSubtrees(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbCount;
    }

    private boolean pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return true;
        }
        //recursion
        boolean l = pureRecursion(root.left);
        boolean r = pureRecursion(root.right);
        //induction rule + return val
        if (l && r &&
                (root.left == null || root.val == root.left.val) &&
                (root.right == null || root.val == root.right.val)) {
            glbCount += 1;
            return true;
        }
        return false;
    }
}
