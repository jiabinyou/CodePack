package Tree.Plan9;

import Tree.TreeNode;

/**
 traverse vertex -->考虑使用pure recursion
 寻找induction rule，发现需要：
 按照r to l postOrder顺序，需使用单独变量prev记录right child
 比如按照postOrder：
 prev = null
   2
 /  \
 3   4
 4.right = prev;
 4.left = null;

   2
 /  \
 3   4 prev
 3.right = prev;
 3.left = null;

        2
      /  \
 prev3   4
 2.right = prev;
 2.left = null;
 以此类推即可，最后iterative update prev 即可
 “这道题是非常好的pure recursion和iterative traverse结合考察的tree题目”
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        //sanity check
        if (root == null) {
            return;
        }
        postOrder(root);
    }

    TreeNode prev = null;
    private void postOrder(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        //recursion r to l
        postOrder(root.right);
        postOrder(root.left);
        //induction rule
        root.right = prev;
        root.left = null;
        //update prev
        prev = root;
    }
}
