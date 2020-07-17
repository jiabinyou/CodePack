package Tree.Plan8;

import Tree.TreeNode;

/**
 traverse path , 但是人字形 --> 考虑pure recursion
 设计induction rule：
 左边：直上直下包含自己在内longest uni path
 右边：直上直下包含自己在内longest uni path
 向上返回：人字形longest uni path
 */
public class LongestUnivaluPath {int glbLongest = Integer.MIN_VALUE;
    public int longestUnivaluePath(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbLongest;
    }

    //pure recursion return val：
    //直上直下longest uni val path
    private int pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        //recursion
        int l = pureRecursion(root.left);
        int r = pureRecursion(root.right);
        //induction rule + return val
        if (root.left != null && root.val == root.left.val) {
            l += 1;
        } else {
            l = 0;
        }
        if (root.right != null && root.val == root.right.val) {
            r +=1;
        } else {
            r = 0;
        }
        int curLongest = Math.max(l, r);
        //update glb
        glbLongest = Math.max(glbLongest, Math.max(curLongest, l + r));
        //return val
        return curLongest;
    }
}