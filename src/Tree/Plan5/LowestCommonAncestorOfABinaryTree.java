package Tree.Plan5;

import Tree.TreeNode;

/**
 pure recursion,分8情况讨论
 */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //sanity check
        if (root == null) {
            return null;
        }
        return LCA(root, p, q);
    }

    private TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        //base case + case 1
        if (root == null || root == p || root == q) {
            return root;
        }
        //recursion
        TreeNode l = LCA(root.left, p, q);
        TreeNode r = LCA(root.right, p, q);
        //induction rule
        if (l != null && r != null) {
            return root;
        }
        return l != null ? l : r;
    }
}
