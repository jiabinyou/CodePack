package Tree.Plan5;

import Tree.TreeNode;

/**
 因为需要计算BST #node，所以使用pureRecursion更方便
 */
public class LargestBSTSubtree {
    int glbMaxNum = 0;
    public int largestBSTSubtree(TreeNode root) {
        // sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbMaxNum;
    }

    private ReturnType pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return new ReturnType(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        //recursion
        ReturnType l = pureRecursion(root.left);
        ReturnType r = pureRecursion(root.right);
        if (l.size == -1 || r.size == -1 || root.val <= l.lB || root.val >= r.rB) {
            return new ReturnType(-1, 0, 0);
        }
        int size = l.size + r.size + 1;
        glbMaxNum = Math.max(glbMaxNum, size);

        return new ReturnType(size, Math.max(root.val, r.lB), Math.min(root.val, l.rB));
    }
}

//return value
class ReturnType {
    int size; //cur bst subtree size, if not bst return -1
    int lB;   //leftBorder
    int rB;   //rightBorder
    public ReturnType(int size, int lB, int rB) {
        this.size = size;
        this.lB = lB;
        this.rB = rB;
    }
}

/**
 * 使用int[]优化glb
 */
class SolLBST1b {
    public int largestBSTSubtree(TreeNode root) {
        // sanity check
        if (root == null) {
            return 0;
        }
        int[] glbMax = new int[]{Integer.MIN_VALUE};
        pureRecursion(root, glbMax);
        return glbMax[0];
    }

    private ReturnTypeLBST pureRecursion(TreeNode root, int[] glbMax) {
        //base case
        if (root == null) {
            return new ReturnTypeLBST(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        ReturnTypeLBST l = pureRecursion(root.left, glbMax);
        ReturnTypeLBST r = pureRecursion(root.right, glbMax);
        if (l.size == -1 || r.size == -1 || root.val <= l.lB || root.val >= r.rB) {
            return new ReturnTypeLBST(-1, 0, 0);
        }
        int size = l.size + r.size + 1;
        glbMax[0] = Math.max(glbMax[0], size);

        return new ReturnTypeLBST(size, Math.max(root.val, r.lB), Math.min(root.val, l.rB));
    }
}

//return value
class ReturnTypeLBST {
    int size; //cur bst subtree size, if not bst return -1
    int lB; //upper bound of cur bst
    int rB; //lower bound of cur bst
    public ReturnTypeLBST(int size, int lB, int rB) {
        this.size = size;
        this.lB = lB;
        this.rB = rB;
    }
}