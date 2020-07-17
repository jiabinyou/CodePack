package Tree.Plan8;

import Tree.TreeNode;

/**
 traverse path --> 人字形
 所以优先考虑pure recursion解法
 */
public class BinaryTreeLongestConsecutiveSequenceII {
    int glbLongest = 0;
    public int longestConsecutive(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbLongest;
    }

    //rerturn val:
    //int[0] : IncLongest
    //int[1] : DecLongest
    private int[] pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return new int[]{0, 0};
        }
        //recursion
        int[] l = pureRecursion(root.left);
        int[] r = pureRecursion(root.right);
        //induction rule
        int curIncLongest = 1;  //ascending
        int curDecLongest = 1;  //dscending
        if (root.left != null) {
            if (root.val == root.left.val - 1) {
                curIncLongest = Math.max(curIncLongest, 1 + l[0]);
            }
            if (root.val == root.left.val + 1) {
                curDecLongest = Math.max(curDecLongest, 1 + l[1]);
            }

        }
        if (root.right != null) {
            if (root.val == root.right.val - 1) {
                curIncLongest = Math.max(curIncLongest, 1 + r[0]);
            }
            if (root.val == root.right.val + 1) {
                curDecLongest = Math.max(curDecLongest, 1 + r[1]);
            }
        }
        //update glb
        glbLongest  = Math.max(glbLongest, curIncLongest + curDecLongest - 1);
        //return val
        return new int[]{curIncLongest, curDecLongest};
    }
}

