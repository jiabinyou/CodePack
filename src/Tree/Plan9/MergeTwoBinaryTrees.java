package Tree.Plan9;

import Tree.TreeNode;

public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //sanity check + base case
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        //recursion
        TreeNode l = mergeTrees(t1.left, t2.left);
        TreeNode r = mergeTrees(t1.right, t2.right);
        //induction rule
        TreeNode root = new TreeNode(t1.val + t2.val);
        //connect
        root.left = l;
        root.right = r;
        //return val
        return root;
    }
}


