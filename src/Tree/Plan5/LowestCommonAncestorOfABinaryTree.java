package Tree.Plan5;

import Tree.TreeNode;

/**
 * Clarify: a,b是否一定在tree中
 * 假设：a,b一定在tree中
 Sol: pure recursion,分8情况讨论
 return val: the lca of cur subtree, return null means there is no p/q in cur subtree
 case           1              2            3             4              5
               root          root         root          root            a/b
              /    \        /    \       /    \         /   \         /    \
             l     r       l   null   null    r     null  null      null  null
 return        root           l             r          null            root

 base case: root == null -> return root;
 -->base case与case 5合并（recursion前可处理）
   case 2,3 4合并处理
 */

/**过例子
 * Input：{4,3,7,#,#,5,6},3,5
 *                          LCA returnVal
 *       4                   4(case 1)
 *      / \
 *     3   7                3(base case)    5(case 2)
 *        / \
 *       5   6               5 （base case）
 *
 *
 * */
public class LowestCommonAncestorOfABinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // sanity check
        if (root == null) {
            return root;
        }
        return LCA(root, A, B);
    }

    private TreeNode LCA(TreeNode root, TreeNode A, TreeNode B) {
        //base case + case 5
        if (root == null || root == A || root == B) {
            return root;
        }
        //recursion
        TreeNode left = LCA(root.left, A, B);
        TreeNode right = LCA(root.right, A, B);
        //case 1
        if (left != null && right != null) {
            return root;
        }
        //case 2 + 3 + 4
        return left != null ? left : right;
    }
}