package Tree.Plan4;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 本质：也是求tree height，把height = 0的那一层放进list中即可
 * */

public class LeafSimilarTrees {
    /**
     * @param root1: the first tree
     * @param root2: the second tree
     * @return: returns whether the leaf sequence is the same
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        // sanity check
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null) {
            return false;
        } else if (root2 == null) {
            return false;
        }
        //dfs find leaves list of two tree
        List<Integer> leafOne = new ArrayList<>();
        dfs(root1, leafOne);
        List<Integer> leafTwo = new ArrayList<>();
        dfs(root2, leafTwo);
        return leafOne.equals(leafTwo);
    }

    //return value: height
    private int dfs(TreeNode root, List<Integer> leaf) {
        //base case
        if (root == null) {
            return -1;
        }
        //recursion
        int left = dfs(root.left, leaf);
        int right = dfs(root.right, leaf);
        //Induction
        int curHeight = Math.max(left, right) + 1;
        //pruning
        if (curHeight > 0) return 1; //返回大于0的即可
        //res processing
        if (curHeight == 0) {
            leaf.add(root.val);
        }
        //return val
        return curHeight;
    }
}
