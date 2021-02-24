package TreeBST.Plan2;

import Tree.TreeNode;

/**
 * O(logn)
 */
public class TrimABinarySearchTree {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        //sanity check
        if (root == null) {
            return root;
        }
        return trim(root, Integer.MIN_VALUE, Integer.MAX_VALUE, L, R);
    }

    private TreeNode trim(TreeNode root, int min, int max, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            return trim(root.right, root.val + 1, max, L, R);
        }
        if (root.val > R) {
            return trim(root.left, min, root.val - 1, L, R);
        }
        if (min >= L && max <= R) {
            return root;
        }
        root.left = trim(root.left, min, root.val - 1, L, R);
        root.right = trim(root.right, root.val + 1, max, L, R);
        return root;
    }
}

