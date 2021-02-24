package TreeBST.Plan2;
import Tree.TreeNode;

/**
 * 这道题其实非常简单，本质就是tree inorder traverse，modify tree作为induction rule放在inorder位置，再用prefixSum优化一下求取node和的过程即可
 *
 * 思路：
 * 在BST上，要将每个node的值改成>=自己所有node之和
 * 因为">=自己所有node" 就是 "node自己 + node的所有right subtree"
 * 只要我们使用recursion，先遍历右子树，在右子树遍历过程中记录node之和，右子树遍历结束拿到的sum，再加上node自己，就应该是新的node值
 * 因为是在右子树遍历完成就需要去modify node value，所以用inorder traverse recursion
 * tips：
 * 站在每个node上，需不需要用o（n）去拿所有右子树node值？
 * 不需要，一路使用一个prefixSum变量记录到目前为止的所有右子树node之和即可
 *
 *
 * tc:o(n)
 * sc:o(height)
 * */
class BinarySearchTreeToGreaterSumTree {
    int[] prefixSum = new int[1];
    public TreeNode bstToGst(TreeNode root) {
        //corner case
        if (root == null) {
            return root;
        }
        modifyTree(root);
        return root;
    }

    private void modifyTree(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        //inorder recurison
        bstToGst(root.right);
        prefixSum[0] += root.val;
        root.val = prefixSum[0];  //modify tree
        bstToGst(root.left);
    }
}


/***
 * 下面两种sol纯属为了将prefixSum融合进recursion的逻辑中，（优先理解sol1）
 * 所以才重新按照pure recurison思路设计了return value，并且将prefixSum作为side effect
 * 注意：这里虽然prefixSum作为side effect，但仍然是pureRecursion，而不是backTracking
 * 因为这里的preSum并不是当前path的信息，而是recursion traverse至今的所有node信息
 * ：
 */

/**
 * Sol2 return value设计：当前subtree node的sum
 */
class SolCBSTGT1b {
    public TreeNode convertBST(TreeNode root) {
        //sanity check
        if (root == null) {
            return root;
        }
        convert(root, 0);
        return root;
    }

    /**return subtree preSum,包括往下传的也是subtree preSum*/
    private int convert(TreeNode root, int prefixSum) {
        //base case
        if (root == null) {
            return 0;
        }
        int right = convert(root.right, prefixSum);
        //inductoin rule
        int curVal = root.val;
        root.val = prefixSum + root.val + right;
        int left = convert(root.left, root.val);
        return right + curVal + left;
    }
}

/**
 * Sol3 return value设计：traverse至今所有遍历过的 node的sum
 */
class SolCBSTGT1c {
    public TreeNode convertBST(TreeNode root) {
        //sanity check
        if (root == null) {
            return root;
        }
        convert(root, 0);
        return root;
    }

    /**return val: preSum of all traversed node*/
    private int convert(TreeNode root, int preSum) {
        if (root == null) {
            return preSum;
        }
        root.val += convert(root.right, preSum);
        return convert(root.left, root.val);
    }
}

