package TreeBST.Plan1;

import Tree.TreeNode;

/**
 * SOL1. R
 * 先使用one，two两个pointer找到需要交换的两个node，最后再交换
 */
public class RecoverBinarySearchTree {
    TreeNode one = null;
    TreeNode two = null;
    TreeNode prev = null;
    public void recoverTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return;
        }
        inOrder(root);
        swap(one, two);
    }

    private void inOrder(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        inOrder(root.left);
        if (prev != null && root.val <= prev.val) {
            if (one == null) {
                one = prev;
            }
            if (one != null) {  //one != null
                two = root;
            }
        }
        prev = root;   //advance pointer
        inOrder(root.right);
    }

    private void swap(TreeNode one, TreeNode two) {
        if (one != null && two != null) {
            int temp = one.val;
            one.val = two.val;
            two.val = temp;
        }
    }
}

/**优化写法：使用class优化掉三个global pointer*/
class SolRBST1b {
    public void recoverTree(TreeNode root) {
        State state = new State();
        findNode(root, state);
        swap(state.one, state.two);
    }

    private void findNode(TreeNode root, State state) {
        if (root == null) {
            return;
        }
        findNode(root.left, state);
        if (state.prev != null && root.val < state.prev.val) {
            if (state.one == null) {
                state.one = state.prev;
            }
            state.two = root;
        }
        state.prev = root;
        findNode(root.right, state);
    }

    private void swap (TreeNode n1, TreeNode n2) {
        int tmp = n1.val;
        n1.val = n2.val;
        n2.val = tmp;
    }

    static class State {
        TreeNode prev;
        TreeNode one;
        TreeNode two;
    }
}
