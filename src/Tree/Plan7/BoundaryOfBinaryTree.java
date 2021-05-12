package Tree.Plan7;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 思路：
 * 按顺序打印left side node， leaf node，right side node
 * 可以使用recursion，看看用什么order来打印
 *
 *                      1
 *                /               \
 *           2                      3
 *         /    \                   /
 *       4      5                 6
 *        \      \                  \
 *         7     8                   9
 * preOrder:  1 2 4 7 5 8 3 6 9  ->leftMost,leaf
 * inOrder:   4 7 2 5 8 1 3 6 9  ->leaf
 * postOrder: 7 4 8 5 2 9 6 3 1  ->rightMost, leaf
 *
 * 可选择：preOrder打印leftMost + leaf， postOrder打印rightMost（目的：保证打印出来是按照题目要求顺序）
 * step 1：（相当于给isLeftMost，isRightMost init value）
 *         先从root开始往下走一层，走到左，右两边子树上
 *
 * step 2：这时候再往下，每次到一层新的recursion，站在root上，就可以判定root的左右孩子分别属于哪一类：
 * 最简单的是leafNode判定：root.left == null && root.right == null
 * 判定root.left node:
 *      1.判定root.left node是否是leftMost node：沿用root的boolean isLeftMost，如果root是左边界，那么root.left一定是左边界
 *      2.判定root.left node是否是rightMost node：需要额外判断。如果root isrightMost，并且root.right == null，那么root.left一定是右边界
 * 判定root.right node:
 *      1.判定root.right node是否是leftMost node：需要额外判断。如果root isLeftMost，并且root.left == null，那么root.right一定是左边界
 *      2.判定root.left node是否是rightMost node：沿用root的boolean isRighttMost，如果root是右边界，那么root.right一定是左边界

 *
 * step 3:
 *      3.1 因为recursion是从第二层开始的，所以root val要单独放进res
 *      3.2deduplication
 *      leftMost和leaf node都是在preOrder中完成的，每个node只遍历一次，所以不会dup；
 *      但是在打印rightMost时候，右下角9在leaf中被打印过了，为了避免重复打印这个node
 *      ，所以postOrder打印时候，不仅要确信node属于rightMost，还要排除不是leaf，才能打印
 *
 *TC:O(N)
 * SC:O(TREE HEIGHT)
 * */
public class BoundaryOfBinaryTree {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // sanity check
        if (root == null) {
            return res;
        }
        res.add(root.val);
        traverse(root.left, true, false, res);
        traverse(root.right, false, true, res);
        return res;
    }

    private void traverse(TreeNode root, boolean isLeftMost, boolean isRightMost, List<Integer> res) {
        //base case
        if (root == null) {
            return;
        }
        //leftMost + leaf
        if (isLeftMost || root.left == null && root.right == null) {
            res.add(root.val);
        }
        //recursion
        traverse(root.left, isLeftMost, isRightMost && root.right == null, res);
        traverse(root.right, isLeftMost && root.left == null, isRightMost, res);
        //rightMost
        if (isRightMost && !(root.left == null && root.right == null)) {
            res.add(root.val);
        }
    }
}



