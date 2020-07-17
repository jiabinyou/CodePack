package Tree.Plan8;

/**
 traverse path --> 直上直下
 可首先考虑经典版本backTracking：
 side effect: prevLongest, prevVal
 update path:
 if (cur.val == prevVal + 1) {
 prevLongest += 1;
 glbLongest更新
 } else {
 prevLongest = 0;
 }
 glbLongest

 */

import Tree.TreeNode;

/**
 细节难点：
 prevNode传treenode更好，而不是直接传int。因为如果传入int值，虽然初始可以设置root之上node是root.val-1,但是万一
 root是int极限值，就会造成麻烦
 所以：prevNode直接传入TreeNode，并且再次判断一下是否是root
 */
public class BinaryTreeLongestConsecutiveSequence {
    int glbLongest = 0;
    public int longestConsecutive(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        backTracking(root, null, 0);
        return glbLongest;
    }

    private void backTracking(TreeNode root, TreeNode prevNode, int prevLongest) {
        //base case
        if (root == null) {
            return;
        }
        //update cur path
        if (prevNode != null && root.val == prevNode.val + 1) {
            prevLongest += 1;
        } else {
            prevLongest = 1;
        }
        //update glb
        glbLongest = Math.max(glbLongest, prevLongest);
        //recursion
        backTracking(root.left, root, prevLongest);
        backTracking(root.right, root, prevLongest);
    }
}

/**
 * pure recursion
 */
class SolLCP2 {
    int glbLongest = 0;
    public int longestConsecutive(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbLongest;
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
        int curLongest = 1;
        if (root.left != null && root.val == root.left.val - 1) {
            curLongest = Math.max(curLongest, 1 + l);
        }
        if (root.right != null && root.val == root.right.val - 1) {
            curLongest = Math.max(curLongest, 1 + r);
        }
        //update glb
        glbLongest  = Math.max(glbLongest, curLongest);
        //return val
        return curLongest;
    }
}