package TreeBST.Plan1;

import Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeIterator {
    private Deque<TreeNode> stack;
    public void BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        firstNode(root, stack);
    }

    /** root后面，按照inOrder，下一个要打印的node是谁--》@return the next smallest number */
    public int next() {
        if (!hasNext()) {
            return Integer.MIN_VALUE;
        }
        TreeNode cur = stack.pop();
        firstNode(cur.right, stack);
        return cur.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**root后面，按照inOrder，第一个要遍历的node是谁*/
    private void firstNode(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
