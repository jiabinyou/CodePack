package Tree.Plan2;

import Tree.TreeNode;

/**总思路
 1.traverse path/vertex? --> path
 2.所以首先设计经典版本backTracking
 寻找side effect：root到prevNode包含的#node,prevNum
 更新side effect方法： prevNum += 1
 确定base case：可放base == null, base = leafNode 都可以
 是否要glb：要，记录glbMaxDepth
 3.使用了glb，可设计优化版本backTracking
 4.思考能不能用pure recursion解？
   寻找induction rule
 */

/** Sol1
 经典backTracking:
    使用glb;
    base = null;
      --> 优点：代码更简洁
      --> 缺点：代码多走一层；实质只需在leafNode更新一次，但base定在null，不知何时到leaf，所以每层都更新一下glb
 */
public class MaximumDepthOfBinaryTree {
    int glbMaxDepth = 0;
    public int maxDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        backTracking(root, 0);
        return glbMaxDepth;
    }

    private void backTracking(TreeNode root, int prevNum) {
        //base case
        if (root == null) {
            return;
        }
        //update cur path
        prevNum += 1;
        //update glb
        glbMaxDepth = Math.max(glbMaxDepth, prevNum);
        //recursion
        backTracking(root.left, prevNum);
        backTracking(root.right, prevNum);
    }
}

/** Sol1a
 * 经典backTracking ：
 *  base = null；
 *  使用int[]优化掉glb
 */
class SolMaxDB1a {
    public int maxDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        int[] glbMaxDepth = new int[1];
        backTracking(root, 0, glbMaxDepth);
        return glbMaxDepth[0];
    }

    private void backTracking(TreeNode root, int prevNum, int[] glbMaxDepth) {
        //base case
        if (root == null) {
            return;
        }
        //update cur path
        prevNum += 1;
        glbMaxDepth[0] = Math.max(glbMaxDepth[0], prevNum);
        backTracking(root.left, prevNum, glbMaxDepth);
        backTracking(root.right, prevNum, glbMaxDepth);
    }
}

/** Sol1b
 *  优化版本backTracking：
 *      base = null；
 *      使用return value优化掉glb
 */
class SolMaxDB1b {
    public int maxDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        return backTracking(root, 0);
    }

    private int backTracking(TreeNode root, int prevNum) {
        //base case
        if (root == null) {
            return prevNum;
        }
        //update cur path
        prevNum += 1;
        //recursion
        int maxDep = Integer.MIN_VALUE;
        maxDep = Math.max(maxDep, backTracking(root.left, prevNum));
        maxDep = Math.max(maxDep, backTracking(root.right, prevNum));
        //return value
        return maxDep;
    }
}

/**
 * Sol2
 *  经典backTracking:
 *     使用glb;
 *     base = leadNode；
 *          优点：提前一层可停止，代码效率更高; glb只需在leafNode更新一次
 *          缺点：代码稍微冗长一点
 */
class SolMaxDB2 {
    int glbMaxDepth = 0;
    public int maxDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        backTracking(root, 0);
        return glbMaxDepth;
    }

    private void backTracking(TreeNode root, int prevNum) {
        //base case + update glb
        if (root.left == null && root.right == null) {
            glbMaxDepth = Math.max(glbMaxDepth, prevNum  +1);
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

/** Sol2a
 * 经典backTracking ：
 *  base = leaf；
 *  使用int[]优化掉glb;
 */
class SolMaxDB2a {
    public int maxDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        int[] glbMaxDepth = new int[1];
        backTracking(root, 0, glbMaxDepth);
        return glbMaxDepth[0];
    }

    private void backTracking(TreeNode root, int prevNum, int[] glbMaxDepth) {
        //base case + update glb
        if (root.left == null && root.right == null) {
            glbMaxDepth[0] = Math.max(glbMaxDepth[0], prevNum  +1);
            return;
        }
        //update cur path
        prevNum += 1;
        //recursion
        if (root.left != null) {
            backTracking(root.left, prevNum, glbMaxDepth);
        }
        if (root.right != null) {
            backTracking(root.right, prevNum, glbMaxDepth);
        }
    }
}

/** Sol2b
 *  优化版本backTracking：
 *      base = leaf；
 *      使用return value优化掉glb
 */
class SolMaxDB2b {
    public int maxDepth(TreeNode root) {
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
        int maxDep = Integer.MIN_VALUE;
        if (root.left != null) {
            maxDep = Math.max(maxDep, backTracking(root.left, prevNum));
        }
        if (root.right != null) {
            maxDep = Math.max(maxDep, backTracking(root.right, prevNum));
        }
        //return value
        return maxDep;
    }
}

/**
 *
 */

/**
 经典pure recurison：
     base = null；(这里leaf也可以，但放在null方便化简case)
 */
class SolMaxDB3a {
    public int maxDepth(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        return pureRecursion(root);
    }

    private int pureRecursion(TreeNode root) {
        //base case + update glb
        if (root == null) {
            return 0;
        }
        //recursion + induction rule
        if (root.left != null && root.right != null) {
            return 1 + Math.max(pureRecursion(root.left), pureRecursion(root.right));
        }
        return root.left != null ? 1 + pureRecursion(root.left) : 1 + pureRecursion(root.right);
    }
}

/**
 经典pure recurison：
    base = leaf；(这里case不好化简)
 */
class SolMaxDB3b {
    public int maxDepth(TreeNode root) {
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
            return 1 + Math.max(pureRecursion(root.left), pureRecursion(root.right));
        } else if (root.left != null) {
            return 1 + pureRecursion(root.left);
        } else {
            return 1 + pureRecursion(root.right);
        }
    }
}