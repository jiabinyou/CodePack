package TreeBST.Plan1;

import Tree.TreeNode;

public class DeleteNodeInABST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root = deleteNode(root);
        }
        return root;
    }

    public TreeNode deleteNode(TreeNode root) {
        if (root.left == null && root.right == null) {
            return null;
        } else if (root.left == null) {
            return root.right;
        } else if (root.right == null) {
            return root.left;
        } else {
            root.val = getSuccessorVal(root.right); /**找到root的右子树中min val，与root值替换*/
            root.right = deleteNode(root.right, root.val);
            return root;
        }
    }

    public int getSuccessorVal(TreeNode root) {
        while(root.left != null)
            root = root.left;
        return root.val;
    }
}

