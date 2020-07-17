package Tree.Plan3;

/**
 traverse vertex
 -->首先考虑设计经典版本pure recursion
 */

import Tree.TreeNode;

/**
 *这道题的难点在于tilt的定义的理解：
 * 每个node的tilt都是|leftsubtree node sum - rightsubtree node sum|，而不是简单的|root.left.val - root.right.val|
 * 所以pure recursion中可以按照计算pathsum方法，return value直接返回node value sum值
 */
public class BinaryTreeTilt {int glbTilt = 0;
    public int findTilt(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbTilt;
    }

    //return value: 包含自己在内，当前subtree的node value sum值
    private int pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        //recursion
        int l = pureRecursion(root.left);
        int r = pureRecursion(root.right);
        //induction rule
        int curSum = l + r + root.val;
        //update glb
        if (root.left != null && root.right != null) {
            glbTilt += Math.abs(l - r);
        } else if (root.left != null) {
            glbTilt += Math.abs(l);
        } else if (root.right != null) {
            glbTilt += Math.abs(r);
        }
        return curSum;
    }
}

/**
 * 简化一下代码
 */
class SolBTT1b {
    int glbTilt = 0;
    public int findTilt(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbTilt;
    }

    //return value: 包含自己在内，当前subtree的node value sum值
    private int pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        //recursion
        int l = pureRecursion(root.left);
        int r = pureRecursion(root.right);
        //induction rule
        int curSum = l + r + root.val;
        //update glb
        glbTilt += Math.abs(l - r);
        return curSum;
    }
}