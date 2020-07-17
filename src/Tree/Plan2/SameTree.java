package Tree.Plan2;

import Tree.TreeNode;

/**
 traverse path/vertex? -->vertex
 所以考虑pure recursion
 寻找induction rule
 两棵树比较的，比较特殊，需要对p,q的情况分别进行判断后，分别对p,q的左右子树进行recursion
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
