package TreeBST.Plan3;

import Tree.TreeNode;

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return null;
        }
        return construct(nums, 0, nums.length - 1);
    }

    private TreeNode construct(int[] nums, int start, int end) {
        //base case
        if (start > end) {
            return null;
        }
        //recursion + induction rule
        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = construct(nums, start, mid - 1);
        root.right = construct(nums, mid + 1, end);
        return root;
    }
}
