package Tree.Plan2;

/**
 * 破题方式和max depth of binary tree相同，但是最大的不同点：
 *      base case只能定在leafnode，不能够在null
 *      因为如果base case定在null，需要每一层都按照Math.min更新一次glb，但是在min depth中从上往下走，永远glb是被1覆盖的
 *      最后的结果永远是1
 *      所以base case只能在leafNode，所以我们只在leafNode更新glb，即比较走到leafNode时候不同path长度
 */

import Tree.TreeNode;

/**
 * Sol1
 *  经典backTracking:
 *     使用glb;
 *     base = leadNode；
 */
public class MinimumDepthOfBinaryTree {
    int glbMinDepth = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        backTracking(root, 0);
        return glbMinDepth;
    }

    private void backTracking(TreeNode root, int prevNum) {
        //base case + update glb
        if (root.left == null && root.right == null) {
            glbMinDepth = Math.min(glbMinDepth, prevNum  +1);
            return;
        }
        //update cur path
        prevNum += 1;
        //recursion
        if (root.left != null) {
            backTracking(root.left, prevNum);
        }
        if (root.right != null) {
            backTracking(root.right, prevNum);
        }
    }
}

/** Sol1a
 *  经典版本backTracking：
 *      base = leaf；
 *      使用int[]优化掉glb
 */
class SolMinDB1a {
    public int minDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        int[] glbMinDepth = new int[]{Integer.MAX_VALUE};
        backTracking(root, 0, glbMinDepth);
        return glbMinDepth[0];
    }

    private void backTracking(TreeNode root, int prevNum, int[] glbMinDepth) {
        //base case + update glb
        if (root.left == null && root.right == null) {
            glbMinDepth[0] = Math.min(glbMinDepth[0], prevNum  + 1);
            return;
        }
        //update cur path
        prevNum += 1;
        //recursion
        if (root.left != null) {
            backTracking(root.left, prevNum, glbMinDepth);
        }
        if (root.right != null) {
            backTracking(root.right, prevNum, glbMinDepth);
        }
    }
}

/** Sol1b
 *  优化版本backTracking：
 *      base = leaf；
 *      使用return value优化掉glb
 */
class SolMinDB1b {
    public int minDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        return backTracking(root, 0);
    }

    private int backTracking(TreeNode root, int prevNum) {
        //base case + update glb
        if (root.left == null && root.right == null) {
            prevNum += 1;
            return prevNum;
        }
        //update cur path
        prevNum += 1;
        //recursion
        int minDep = Integer.MAX_VALUE;
        if (root.left != null) {
            minDep = Math.min(minDep, backTracking(root.left, prevNum));
        }
        if (root.right != null) {
            minDep = Math.min(minDep, backTracking(root.right, prevNum));
        }
        //return value
        return minDep;
    }
}

/**
 经典pure recurison：
 base = leaf；(这里case不好化简)
 */
class SolMinDB2 {
    public int minDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        return pureRecursion(root);
    }

    private int pureRecursion(TreeNode root) {
        //base case + update glb
        if (root.left == null && root.right == null) {
            return 1;
        }
        //recursion + induction rule
        if (root.left != null && root.right != null) {
            return 1 + Math.min(pureRecursion(root.left), pureRecursion(root.right));
        } else if (root.left != null) {
            return 1 + pureRecursion(root.left);
        } else {
            return 1 + pureRecursion(root.right);
        }
    }
}



