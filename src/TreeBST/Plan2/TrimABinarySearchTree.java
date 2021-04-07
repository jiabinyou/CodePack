package TreeBST.Plan2;

import Tree.TreeNode;

/**
 * O(logn)
 */
/**
 dfs过程中，我们要记录当前subtree的值包含的范围，下限是leftBorder， 上限是rightborder
 根据r【root.val大小，当前subtree的取值范围lB, rB】 与 所给的【minimum, maximum】大小关系，分成以下几种不同情况处理,
 分别对recursion过程进行不同的pruning：
 1.root.val < minimum, 整个左子树太小了，不要了，下一层recurison只去右边
 return val：直接return右子树recurison结果即可
 else
 2. root.val > maximum, 整个右子树太大了，不要了，下一层recurison只去左边
 return val：直接return左子树recurison结果即可
 else
 3. subtree的lB >= minimum && rB <= maximum,说明以root开头的整颗subtree都可以直接保留
 此时不需要处理，返回当前root即可
 else
 4.剩下的情况是：root.val符合条件，在【minimum, maximum】可以保留，
 但不知道左右子树情况，所以左右子树都需要recursion的，
 此时我们将root.left, root.right分别连接上去往两边进行recurison的结果
 return val：最后返回root即可
 */
public class TrimABinarySearchTree {
    /**
     * @param root: given BST
     * @param minimum: the lower limit
     * @param maximum: the upper limit
     * @return: the root of the new tree
     */
    public TreeNode trimBST(TreeNode root, int minimum, int maximum) {
        // sanity check
        if (root == null) {
            return root;
        }
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE, minimum, maximum);
    }

    //return val: dfs处理后当前层subtree的root
    private TreeNode dfs(TreeNode root, int lB, int rB, int minimum, int maximum) {
        //base case
        if (root == null) {
            return root;
        }
        //Induction + return value (pruning recursion,分case讨论)
        if (root.val < minimum) {  //case 1
            return dfs(root.right, root.val + 1, rB, minimum, maximum);
        } else if (root.val > maximum) {  //case 2
            return dfs(root.left, lB, root.val - 1, minimum, maximum);
        } else if (lB >= minimum && rB <= maximum) { //case 3
            return root;
        }
        //case 4
        root.left = dfs(root.left, lB, root.val - 1, minimum, maximum);
        root.right = dfs(root.right, root.val + 1, rB, minimum, maximum);
        return root;
    }
}



/**
 * leetcode版本：
public class TrimABinarySearchTree {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        //sanity check
        if (root == null) {
            return root;
        }
        return trim(root, Integer.MIN_VALUE, Integer.MAX_VALUE, L, R);
    }

    private TreeNode trim(TreeNode root, int min, int max, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            return trim(root.right, root.val + 1, max, L, R);
        }
        if (root.val > R) {
            return trim(root.left, min, root.val - 1, L, R);
        }
        if (min >= L && max <= R) {
            return root;
        }
        root.left = trim(root.left, min, root.val - 1, L, R);
        root.right = trim(root.right, root.val + 1, max, L, R);
        return root;
    }
}
*/
