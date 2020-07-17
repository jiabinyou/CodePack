package Tree.Plan4;

import Tree.TreeNode;

/**
 traverse path --> 人字形 -->
 首先考虑经典pure recursion
 */
public class DiameterOfBinaryTree {
    int glbLongest = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbLongest - 1;   //length of edge
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
        int curLongest = 1 + Math.max(l, r);
        //update glb
        glbLongest = Math.max(glbLongest, 1 + l + r);
        //return val
        return curLongest;
    }
}
