package TreeBST.Plan2;
import Tree.TreeNode;

/**
 * Sol1. R
 */
public class MinimumDistanceBetweenBSTNodes {
    TreeNode prev;
    int glbMin = Integer.MAX_VALUE;
    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return glbMin;
    }

    private void inorder(TreeNode root) {
        //sanity check
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (prev != null) {
            glbMin = Math.min(glbMin, root.val - prev.val);
        }
        prev = root;
        inorder(root.right);
    }
}

/**
 * Sol1b.使用wrapper class优化glb pointer
 */
class Solution {
    public int minDiffInBST(TreeNode root) {
        State state = new State();
        inorder(root, state);
        return state.min;
    }
    private void inorder(TreeNode root, State state) {
        if (root == null) {
            return;
        }
        inorder(root.left, state);
        if (state.prev != null) {
            state.min = Math.min(state.min, root.val - state.prev.val);
        }
        state.prev = root;
        inorder(root.right, state);
    }
    static class State {
        TreeNode prev;
        int min;
        State() {
            this.min = Integer.MAX_VALUE;
        }
    }
}