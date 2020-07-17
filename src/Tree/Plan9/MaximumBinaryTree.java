package Tree.Plan9;

import Tree.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Sol1. O(N^2)
 */
public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return null;
        }
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int l, int r) {
        //base case
        if (l > r) {
            return null;
        }
        int maxIdx = findMax(nums, l, r);
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = construct(nums, l, maxIdx - 1);
        root.right = construct(nums, maxIdx + 1, r);
        return root;
    }

    private int findMax(int[] nums, int l, int r) {
        int maxIdx = l;
        for (int i = l; i <= r; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }
        return maxIdx;
    }
}

/**
 * Sol2. Mono Stack  O(N)
 */
class SolMBT2 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return null;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        for (int num : nums) {
            TreeNode cur = new TreeNode(num);
            while (!stack.isEmpty() && num > stack.peek().val) { //单调增stack，先把最大的都连到左边
                cur.left = stack.pop();
            }
            if (!stack.isEmpty()) {
                stack.peek().right = cur;
            }
            stack.push(cur);
        }
        TreeNode root = null;
        while (!stack.isEmpty()) {
            root = stack.pop();
        }
        return root;
    }
}