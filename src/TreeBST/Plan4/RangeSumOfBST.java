package TreeBST.Plan4;

import Tree.TreeNode;

/**
 *这道题乍一看会先思考，能不能从root开始往下，根据大小关系直接丢一root的一边？
 * 很显然是不行的，所以这种方法排除
 *
 * 再来思考能不能用inorder recursion，按照升序遍历找结果？
 * 是可以的。
 */

class RangeSumBSTsoL1 {
    private int glbSum = 0;
    public int rangeSumBST(TreeNode root, int L, int R) {
        //sanity check
        if (root == null) {
            return 0;
        }
        int[] glbSum = new int[]{0};
        getSum(root, L, R, glbSum);
        return glbSum[0];
    }

    private void getSum(TreeNode root, int L, int R, int[] glbSum) {
        //base case
        if (root == null) {
            return;
        }
        if (root.val >= L && root.val <= R) {
            glbSum[0] += root.val;
        }
        getSum(root.left, L, R, glbSum);
        getSum(root.right, L, R, glbSum);
    }
}

 /**
 * 再去想，如果只是recurison一遍，就没有用到BST的优势，再去想能不能根据inOrder BST的升序性质，进行有pruning的pure recursion？
 * 是可以的！
 * */
/**
 * Sol2.这个解法有点绕，但是recurison部分会更加简化
 * 这里<high往右，>low往左，相当于只有在符合条件的情况下才会继续去拓展边界
 * 并且单独判断每个node，只有符合条件范围才加入sum
 * */
public class RangeSumOfBST {
    int sum = 0;
    public int rangeSumBST(TreeNode root, int low, int high) {
        //sanity check
        if (root == null) {
            return 0;
        }
        inOrder(root, low, high);
        return sum;
    }

    private void inOrder(TreeNode root, int low, int high) {
        //base case
        if (root == null) {
            return;
        }
        //induction rule
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }
        //pruning recursion
        if (root.val > low) {
            inOrder(root.left, low, high);
        }
        if (root.val < high) {
            inOrder(root.right, low, high);
        }
    }
}


/**
 * Sol3.是经典的pure recursion，学习这种解答
 * 在BST的pruning pure recursion中，注意return value往往是和pruning条件融合在一起的，即每个条件下都要分别return不同的东西。
 * 最后再进行general case的induction rule和return。
 *
 * 这样写的好处是：
 * 不用计算出所有recursion结果，在root.val < low和root.val > high时候，有一半的recurison可以抛弃
 * */
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        //sanity check
        if (root == null) {
            return 0;
        }
        return inOrder(root, low, high);
    }

    /**include cur,以cur为起点的subtree的【low，high】范围内的node和*/
    private int inOrder(TreeNode root, int low, int high) {
        //base case
        if (root == null) {
            return 0;
        }
        //pruning recursion
        int sum = 0;
        if (root.val < low) {
            return inOrder(root.right, low, high);
        }
        if (root.val > high) {
            return inOrder(root.left, low, high);
        }
        sum = root.val + inOrder(root.left, low, high) + inOrder(root.right, low, high);
        return sum;
    }
}






