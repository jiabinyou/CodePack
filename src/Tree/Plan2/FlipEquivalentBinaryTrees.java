package Tree.Plan2;

import Tree.TreeNode;

/**
 * Sol1.recursion(DFS1)
 * If root1 and root2 have the same root value, then we only need to check if their children are equal
There are 3 cases:
If root1 or root2 is null, then they are equivalent if and only if they are both null.
Else, if root1 and root2 have different values, they aren't equivalent.
Else, let's check whether the children of root1 are equivalent to the children of root2. There are two different ways to pair these children.

 tc:o(min(n1, n2)), n1--#nodes of tree1, n2--#nodes of tree2
 sc:o(min(n1, n2))
 * */
class FlipEquivalentBinaryTrees {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == root2)
            return true;
        if (root1 == null || root2 == null || root1.val != root2.val)
            return false;

        return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
                flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
    }
}

/**如果题目给出条件：tree每个node都是all unique value
 * 可以做出优化：
 * 此时我们不需要再将children of root1 are equivalent to the children of root2镜像比较两次，因为此时left right children一定有明确的大小关系，
 * 所以根据大小关系，我们就明确知道，是left1和left2比较，还是left1和right1比较（Thus we for sure knows which subtrees should be matched together.）
 *
 * */
class FlipEquivalentBinaryTreesSol2 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;

        boolean b1 = compareNode(root1.left, root1.right);
        boolean b2 = compareNode(root2.left, root2.right);

        return (b1 == b2 ? flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
                : flipEquiv(root1.right, root2.left) && flipEquiv(root1.left, root2.right));
    }

    // return node1.val <= node2.val;  especially let the val of null be Integer.MIN_VALUE
    private boolean compareNode(TreeNode node1, TreeNode node2) {
        if (node1 == null) return true;
        if (node2 == null) return false;
        return node1.val <= node2.val;
    }
}
