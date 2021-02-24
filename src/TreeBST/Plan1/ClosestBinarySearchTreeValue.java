package TreeBST.Plan1;

import Tree.TreeNode;

public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target)) {
                closest = root.val;
            }
            if (target > root.val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return closest;
    }
}
