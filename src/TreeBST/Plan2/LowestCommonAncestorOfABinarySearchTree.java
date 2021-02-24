package TreeBST.Plan2;
import Tree.TreeNode;

/**
 * Sol1. R
 */
public class LowestCommonAncestorOfABinarySearchTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //sanity check  + base case
        if (root == null || p == null || q == null) {
            return null;
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;  /**如果p和q分别在root的左，右子树，就直接说明root是LCA*/
    }
}

/**
 * Sol2.I
 * tail recursion改写成iterative
 */
/**
 * tail recursion:
 * 指一个函数里的最后一个动作是返回一个函数的调用结果，该尾部调用位置被称为尾位置。尾部递归本质上等同于循环.
 * tail recursion:
        sum_aux(n,i,k) {
            if( i <= n ) {
                return sum_aux(n,i+1,k+i);
            } else {
                return k;
            }
        }

        sum(n) {
            return sum_aux(n,1,0);
        }
 *
 * iterative：
        sum(n) {
            int i = 1, k = 0;
            while( i <= n ) {
                k += i;
                ++i;
            }
            return k;
        }
 *
 */
class SolLCABST2 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //sanity check  + base case
        if (root == null || p == null || q == null) {
            return null;
        }
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;  /**如果p和q分别在root的左，右子树，就直接说明root是LCA*/
            }
        }
        return root;
    }
}



