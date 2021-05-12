package Tree.Plan4;

import Tree.TreeNode;

/**
 traverse path --> 人字形 -->
 首先考虑经典pure recursion
 */

/**
 思路：可以使用pure recurison
 return value：the longest depth for left / right (single)
 level: tree的每一层对应一层recursion level
 each level做的事情：
 single path cur level max depth = 1 + max(left, right)
 total path cur level max depth = root.val + left + right

 TC: O(n)  n->#tree node
 SC:O(H) H->tree height
 */

/**过例子
 *                                return                   glbMax(diameter = glbMax - 1)
 *           1                     [1]3                       4
 *          / \
 *         2   3                   [2]2      [3]0             3
 *        / \
 *       4   5                [4]0    [5]0                    0
 *
 * */
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
