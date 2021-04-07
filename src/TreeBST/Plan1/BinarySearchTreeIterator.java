package TreeBST.Plan1;

import Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**和tree iterative inorder traverse在一起写，一模一样*/
/**唯一注意：hasNext()帮助我们检查了stack是否走到了null，所以在next中，检查hasNext()==true后，直接traverse即可
 * 没有了while (!stack.isEmpty)*/
public class BinarySearchTreeIterator {
    private Deque<TreeNode> stack;
    public void BSTIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        firstNode(root, stack);   //初始化root
    }

    /** root后面，按照inOrder，下一个要打印的node是谁--》@return the next smallest number */
    public int next() {    /**和tree iterative inorder traverse在一起写，一模一样*/
        //sanity check
        if (!hasNext()) {
            return Integer.MIN_VALUE;  /**返回一个不可能的值即可*/
        }
        TreeNode cur = stack.pop();
        firstNode(cur.right, stack);
        return cur.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /**root后面，按照inOrder，第一个要遍历的node是谁  --》 其实就是pushLeft*/
    private void firstNode(TreeNode root, Deque<TreeNode> stack) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
}
