package Tree.Plan3;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 traverse vertex
 优先考虑设计经典版本pure recursion
 */

/**
 * 思路：
 * 先计算整棵树的node value总和totalSum，再次traverse一遍，一旦发现subtree的node value之和 == totalSum / 2,
 * 说明可分，返回true
 *
 * 难点：
 * 上面思路是正确的的，但是如果tree中有负数，可能出现情况是整棵树总和是0，那么0 / 2 = 0, 所以一直到整棵树遍历完以后，结果一定是totalSum / 2,即0，
 * 所以这种情况下永远返回true
 * e.g.
 *       0
 *     /  \
 *   -1    1    依然返回true
 * 改进方法：
 * 只能在root以下，找到了subtree的node value之和 == totalSum / 2 时候，才能返回true
 */
public class EqualTreePartition {
    public boolean checkEqualTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return false;
        }
        int totalSum = checkTotalSum(root);
        if (totalSum % 2 != 0) {
            return false;
        }
        ifCanDivide(root, totalSum);
        return canDivide;
    }

    private int checkTotalSum(TreeNode root) {
        //base case
        if (root == null) {
            return 0;
        }
        int l = checkTotalSum(root.left);
        int r = checkTotalSum(root.right);
        int curSum = root.val + l + r;
        return curSum;
    }

    boolean canDivide = false;
    private int ifCanDivide(TreeNode root, int target) {
        //base case
        if (root == null) {
            return 0;
        }
        int l = ifCanDivide(root.left, target);
        int r = ifCanDivide(root.right, target);
        int curSum = root.val + l + r;
        /**
         * induction rule是难点：
         * 只能在root以下，找到了subtree的node value之和 == totalSum / 2 时候，才能返回true
         */
        if ((root.left != null && target - l == l) || ( root.right != null && target -r == r)) {
            canDivide = true;
        }
        return curSum;
    }
}

/**
 * 上一种解法，是遍历tree两遍，那么其实可以在遍历第一遍时候做memo，这样后一遍直接查memo即可
 * 本质：
 *  tree sum to target
 *  并且只要找到是否存在即可 --> 尝试preSum的set解法
 *  -->难点：依旧是刚刚corner case会有问题：如果tree中有负数，可能出现情况是整棵树总和是0，那么0 / 2 = 0, 所以一直到整棵树遍历完以后，结果一定是totalSum / 2,即0，
 *  * 所以这种情况下永远返回true
 *  说明此时set不够用，所以需要使用preSum map解法
 *  当totalSum == 0时候，最后map中totalSum / 2出现次数 > 1才能返回true（因为有一次一定是整棵树遍历完的0符合totalSum / 2）
 */
class Solution {
    public boolean checkEqualTree(TreeNode root) {
        //sanity check
        if (root == null) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>(); //出现过的<distinct preSum value, freq>
        int totalSum = checkTotalSum(root, map);
        if (totalSum % 2 != 0) {  //快速pruning，一定不可分
            return false;
        }
        if (totalSum == 0) {
            return map.get(0) > 1;  //此时totalSum / 2就是0
        }
        return map.containsKey(totalSum / 2);
    }

    private int checkTotalSum(TreeNode root, Map<Integer, Integer> map) {
        //base case
        if (root == null) {
            return 0;
        }
        //recursion
        int l = checkTotalSum(root.left, map);
        int r = checkTotalSum(root.right, map);
        //induction rule
        int curSum = root.val + l + r;
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        return curSum;
    }
}
