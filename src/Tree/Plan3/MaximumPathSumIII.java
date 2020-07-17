package Tree.Plan3;

import Tree.TreeNode;

/**
 * 直上直下，any to any
 * https://app.laicode.io/app/problem/140
 */
/**
 笔记“tree pure recursion vs. BackTRacking例题” P16
 */

/** Sol1. 经典backtracking
        base = leaf
        glbMaxSum
 */
public class MaximumPathSumIII {int glbMaxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        backTracking(root, 0);
        return glbMaxSum;
    }

    private void backTracking(TreeNode root, int prevMaxSum) {
        //base case
        if (root.left == null && root.right == null) {
            prevMaxSum = root.val + (prevMaxSum < 0 ? 0 : prevMaxSum);
            glbMaxSum = Math.max(prevMaxSum, glbMaxSum);
            return;
        }
        //update cur path
        prevMaxSum = root.val + (prevMaxSum < 0 ? 0 : prevMaxSum);
        glbMaxSum = Math.max(prevMaxSum, glbMaxSum);
        //recursion
        if (root.left != null) {
            backTracking(root.left, prevMaxSum);
        }
        if (root.right != null) {
            backTracking(root.right, prevMaxSum);
        }
        //pass by value, 不需要recovery
    }
}

/**Sol1b 经典backtracking
        base = leaf
 *      因为base case与update path所做事情相同，所以将update path放在前面简化代码
 *      并且使用INT[] 优化掉glb
 */
class SolMPSIII1b {
    public int maxPathSum(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        int[] glbMaxSum = new int[]{Integer.MIN_VALUE};
        backTracking(root, 0, glbMaxSum);
        return glbMaxSum[0];
    }

    private void backTracking(TreeNode root, int prevMaxSum, int[] glbMaxSum) {
        //update cur path
        prevMaxSum = root.val + (prevMaxSum < 0 ? 0 : prevMaxSum);
        glbMaxSum[0] = Math.max(prevMaxSum, glbMaxSum[0]);
        //base case
        if (root.left == null && root.right == null) {
            return;
        }
        //recurision
        if (root.left != null) {
            backTracking(root.left, prevMaxSum, glbMaxSum);
        }
        if (root.right != null) {
            backTracking(root.right, prevMaxSum, glbMaxSum);
        }
        //pass by value, 不需要recovery
    }
}

/**Sol1c 优化版本backtracking
        base = leaf
 *      并且使用return value 优化掉glb
 */
class SolMPSIII1c {
    public int maxPathSum(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        return backTracking(root, 0);
    }

    private int backTracking(TreeNode root, int prevMaxSum) {
        //update cur path
        prevMaxSum = root.val + (prevMaxSum < 0 ? 0 : prevMaxSum);

        int curMaxSum = Integer.MIN_VALUE;
        curMaxSum = Math.max(prevMaxSum, curMaxSum);
        //base case
        if (root.left == null && root.right == null) {
            return curMaxSum;
        }
        //recurision
        if (root.left != null) {
            curMaxSum = Math.max(curMaxSum, backTracking(root.left, prevMaxSum));
        }
        if (root.right != null) {
            curMaxSum = Math.max(curMaxSum, backTracking(root.right, prevMaxSum));
        }
        //pass by value, 不需要recovery
        //return val
        return curMaxSum;
    }
}

/**Sol2. 经典版本pure recursion
 *      base = null
 *      glb
 */
class SolMPSIII2 {
    int glbMaxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        // sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbMaxSum;
    }

    private int pureRecursion(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        //recursion
        int left = pureRecursion(root.left);
        int right = pureRecursion(root.right);
        //induction rule
        int curSum = root.val + Math.max(0, Math.max(left, right));
        //update glb
        glbMaxSum = Math.max(glbMaxSum, curSum);
        //return val
        return curSum;
    }
}

/**Sol2b. 优化版本pure recursion
 *      base = null
 *      使用return type优化glb
 */
class SolMPSIII2b {
    public int maxPathSum(TreeNode root) {
        // sanity check
        if (root == null) {
            return 0;
        }
        ReturnType res = pureRecursion(root);
        return res.glbMaxSum;
    }

    private ReturnType pureRecursion(TreeNode root) {
        //sanity check
        if (root == null) {
            return new ReturnType(0, Integer.MIN_VALUE);
        }
        //recursion
        ReturnType left = pureRecursion(root.left);
        ReturnType right = pureRecursion(root.right);
        //induction rule
        int curNodeMax = root.val + Math.max(0, Math.max(left.curSum, right.curSum));
        //update glb
        int curNodeGlbMax = Math.max(curNodeMax, Math.max(left.glbMaxSum, right.glbMaxSum));
        //return val
        return new ReturnType(curNodeMax, curNodeGlbMax);
    }
}

class ReturnType {
    int curSum;
    int glbMaxSum;
    public ReturnType(int curSum, int glbMaxSum) {
        this.curSum = curSum;
        this.glbMaxSum = glbMaxSum;
    }
}