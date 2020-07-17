package Tree.Plan2;

import Tree.TreeNode;

/**
 traverse path/vertex? -->vertex
 所以考虑pure recursion

 1.寻找induction rule
 当前层：root的左右孩子交换
 返回上一层：root自己即可

 2.four cases：
      r                  r                           r                          r
    /  \               /  \                        /   \                       /  \
   l    r             l  null                   null   r                    null  null

 root.left = r;         root.right = l;          root.left = r;
 root.right = l;
 return root;           return root;              return root;               return root;

 3.base case
 可定在base case = leaf， 也可定在base case = null
 */

/**
 * Sol1
 * 经典pure recursion：
 *  base case = leaf
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return root;
        }
        return pureRecursion(root);
    }

    private TreeNode pureRecursion(TreeNode root) {
        //base case
        if (root.left == null && root.right == null) {
            return root;
        }
        //recursion
        TreeNode l = null;
        TreeNode r = null;
        if (root.left != null) {
            l = pureRecursion(root.left);
        }
        if (root.right != null) {
            r = pureRecursion(root.right);
        }
        //induction rule
        root.right = l;
        root.left = r;
        //return value
        return root;
    }
}

/**
 * Sol2
 * 经典pure recursion
 *      base case = null --> 这里可以简化case
 */
class SolIBT2 {
    public TreeNode invertTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return root;
        }
        return pureRecursion(root);
    }

    private TreeNode pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return root;
        }
        //recursion
        TreeNode l = pureRecursion(root.left);
        TreeNode r = pureRecursion(root.right);
        //induction rule
        root.right = l;
        root.left = r;
        //return value
        return root;
    }
}

