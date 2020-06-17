package LinkedList.Plan1;


import LinkedList.TreeNode;

public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return root;
        }
        //base case
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode newRoot = upsideDownBinaryTree(root.left);

        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;

        return newRoot;
    }
}
