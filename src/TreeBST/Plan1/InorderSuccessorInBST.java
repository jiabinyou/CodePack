package TreeBST.Plan1;
import Tree.TreeNode;

/***
 * Successor : 下一个
 */
public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        TreeNode smallestLarger = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                smallestLarger = root;
                root = root.left;
            }
        }
        return smallestLarger;
    }
}
