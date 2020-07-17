package Tree.Plan9;

import Tree.TreeNode;

/**
 traverse vertex, pure recursion即可 -->preOrder
 root              root                root                  root
 /   \            /    \               /   \                 /   \
 null  null       l    null           null  r               l    r
 res=root.val    res=root.val+(l)    res=root.vsl+()+(r)   res=root.val+(l)+(r)
 return root     return root         return root           return root

 合并case后写出code如下
 */
public class ConstructStringFromBinaryTree {
    String res = "";
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        preorder(t);
        return res;
    }

    private void preorder(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        //induction rule + recursion
        res += root.val;
        if (root.left != null) {
            res += "(";
            preorder(root.left);
            res += ")";
        }
        if (root.right != null) {
            if (root.left == null) {
                res += "()";
            }
            res += "(";
            preorder(root.right);
            res += ")";
        }
    }
}

