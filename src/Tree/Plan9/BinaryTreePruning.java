package Tree.Plan9;

import Tree.TreeNode;

public class BinaryTreePruning {
    public TreeNode pruneTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return null;
        }
        //recursion
        TreeNode l = pruneTree(root.left);
        TreeNode r = pruneTree(root.right);
        //induction rule
        if (l == null && r == null && root.val == 0) {
            return null;
        }
        //connect
        root.left = l;
        root.right = r;
        //return val
        return root;

    }
}
