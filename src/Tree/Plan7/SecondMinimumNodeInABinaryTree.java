package Tree.Plan7;

import Tree.TreeNode;

import java.util.TreeSet;

public class SecondMinimumNodeInABinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        //sanity check
        if (root == null || root.left == null && root.right == null) {
            return -1;
        }
        TreeSet<Integer> treeSet = new TreeSet();
        backTracking(root, treeSet);
        if (treeSet.size() <= 1) {
            return -1;
        }
        treeSet.pollFirst();
        return treeSet.first();
    }

    private void backTracking(TreeNode root, TreeSet<Integer> treeSet) {
        //base case
        if (root == null) {
            return;
        }
        treeSet.add(root.val);
        backTracking(root.left, treeSet);
        backTracking(root.right, treeSet);
    }
}

/**这个解法实际上不好写
 * Sol2.使用两个Integer优化掉treeMap o(n)的空间
 */

/**
 * 注意审题：
 * 这道题说这是full tree，即要么node没有孩子，要么俩孩子，并且parent一定比child小
 * --》意味着：root一定是最小的，每一层value从上往下递增
 * 所以实际上我们用first初始化成root.val,这就一定是最小值，后面更新second即可
 */
class SolSMNIBT2 {
    /**
     难点：巧妙应用Integer
     */
    private Integer first;  //第一小
    private Integer second; //第二小
    public int findSecondMinimumValue(TreeNode root) {
        //sanity check
        if (root == null || root.left == null && root.right == null) {
            return -1;
        }
        first = root.val;  //初始化first
        backTracking(root);
        return second == null ? -1 : second;
    }


    private void backTracking(TreeNode root){
        if(root == null) {
            return;
        }
        /**
         * 难点：不要漏case
         */
        if((second == null && root.val > first) ||
                (second != null && root.val > first && root.val < second)) {
            second = root.val;
        }
        backTracking(root.left);
        backTracking(root.right);
    }
}
