package Tree.Plan2;

import Tree.TreeNode;

public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        //sanity check
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        } else {
            return compareSAndT(s, t);
        }
    }

    //pure recursion
    private boolean compareSAndT(TreeNode s, TreeNode t) {
        //base case
        if (s == null || t == null) {
            return false;
        }
        if (isSame(s, t)) { //pruning
            return true;
        }
        return compareSAndT(s.left, t) || compareSAndT(s.right, t);
    }

    //pure recursion
    private boolean isSame(TreeNode one, TreeNode two) {
        if (one == null && two == null) {
            return true;
        } else if (one == null || two == null) {
            return false;
        } else if (one.val != two.val) {
            return false;
        } else {
            return isSame(one.left, two.left) && isSame(one.right, two.right);
        }
    }
}
