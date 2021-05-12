package Tree.Plan3;
import Tree.TreeNode;

/**
 人字形，any to any node
 */

/**
 leetcode 124
 https://leetcode.com/problems/binary-tree-maximum-path-sum/
 */

/**
 trverse path --> 但是由于是人字形path，所以实际上backTracking意义不大，直接尝试设计pure recursion
 经典backTracking:
    base = null;
    glb
 */

/**
 Clarafy: 是否有可能有负数
 -》如果inpur有负数，那么每一层计算return val，就要和0作比较，比0小的直接不要了

 思路：可以使用pure recurison
 return value：the max path sum for left / right
 level: height
 each level做的事情：
 single path cur level max path sum = root.val + max(left, right, 0)
 total path cur level max path sum = max(single level, root.val + left + right)

 TC: O(n)  n->#tree node
 SC:O(H) H->tree height
 */
public class BinaryTreeMaximumPathSum {
    int glbMaxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbMaxSum;
    }

    private int pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        //recurison
        int left = pureRecursion(root.left);
        int right = pureRecursion(root.right);
        //induction rule
        int curMaxSum = root.val + Math.max(0, Math.max(left, right));
        //update glb
        glbMaxSum = Math.max(glbMaxSum, Math.max(root.val + left + right, curMaxSum));
        //return val
        return curMaxSum;
    }
}

/**
 * 优化版本pure recurision：
 *  用return type优化glb
 */
class SolMPSIV1b {
    public int maxPathSum(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        ReturnTypeI res = pureRecursion(root);
        return res.glbMaxSum;
    }

    private ReturnTypeI pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return new ReturnTypeI(0, Integer.MIN_VALUE);
        }
        //recurison
        ReturnTypeI left = pureRecursion(root.left);
        ReturnTypeI right = pureRecursion(root.right);
        //induction rule
        int curNodeMaxSum = root.val + Math.max(0, Math.max(left.curMaxSum, right.curMaxSum));
        //update glb
        int curNodeglb = Math.max(Math.max(left.glbMaxSum, right.glbMaxSum), Math.max(root.val + left.curMaxSum + right.curMaxSum, curNodeMaxSum));
        //return val
        return new ReturnTypeI(curNodeMaxSum, curNodeglb);
    }
}

class ReturnTypeI {
    int curMaxSum;
    int glbMaxSum;
    public ReturnTypeI (int curMaxSum, int glbMaxSum) {
        this.curMaxSum = curMaxSum;
        this.glbMaxSum = glbMaxSum;
    }
}
