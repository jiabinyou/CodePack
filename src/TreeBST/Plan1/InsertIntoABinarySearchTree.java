package TreeBST.Plan1;
import Tree.TreeNode;

/**
 * sol1.Iterative
 */
public class InsertIntoABinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            return newNode;
        }
        TreeNode cur = root;
        while (cur.val != val) {
            if (cur.val > val) {
                if (cur.left == null) {
                    cur.left = newNode;
                    break;
                } else {
                    cur = cur.left;
                }
            } else {
                if (cur.right == null) {
                    cur.right = newNode;
                    break;
                } else {
                    cur = cur.right;
                }
            }
        }
        return root;
    }
}

/**
 * Sol12.recursion
 */
class SolIBST2 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        return insert(root, val);
    }

    private TreeNode insert(TreeNode root, int val) {
        if (val < root.val) {
            root.left = root.left == null ? new TreeNode(val) : insert(root.left, val);
        } else {
            root.right = root.right == null ? new TreeNode(val) : insert(root.right, val);
        }
        return root;
    }
}


