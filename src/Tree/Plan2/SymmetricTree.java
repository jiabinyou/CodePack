package Tree.Plan2;

import Tree.TreeNode;

public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        //sanity check
        if (root == null) {
            return true;
        }
        return isS(root.left, root.right);
    }

    private boolean isS(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        return isS(p.left, q.right) && isS(p.right, q.left);
    }
}
