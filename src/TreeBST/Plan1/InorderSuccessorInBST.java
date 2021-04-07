package TreeBST.Plan1;
import Tree.TreeNode;

/***
 * Successor : 下一个
 * z这道题超级超级超级简单
 * 本质:p在BST上的successor，其实就在BST上找smallestLarger than p的element
 * 方法：
 * 使用一个glb val去记录在一整颗树上找smallestLarger的情况
 * case 1.   cur.val <= p, 说明cur小了，那么直接找cur的右子树即可
 * case 1.   cur.val > p, 那么cur有可能是符合的，但也有可能还有比cur更小但是也大于p的，
 *           那么将glb value更新成cur，表示目前cur是一个candidate，再去cur的左子树找找有没有更小的即可
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
