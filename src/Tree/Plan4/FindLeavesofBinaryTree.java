package Tree.Plan4;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 按照leaves顺序打印本质：
 * 求tree height，按照height打印，其实和dfs解法的按照层打印是一样的
 * height：root height最大，leaves height = 0
 *
 * 注意：base case为了方便，我们在root = null返回，此时可直接返回-1
 * */
public class FindLeavesofBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        dfs(root, res);
        return res;
    }

    //return val: height
    private int dfs(TreeNode root,  List<List<Integer>> res) {
        //base case
        if (root == null) {
            return -1;
        }
        //recursion
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        //induction
        int curHeight = Math.max(left, right) + 1;
        //res processing
        if (curHeight == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(curHeight).add(root.val);
        //return val
        return curHeight;
    }
}

