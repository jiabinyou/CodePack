package Tree.Plan3;

import Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 travese path --> backTracking
 base case:
 可在leafNode 或者 null，但这里null以及没什么可做的，并不能简化case，反而复杂化了，不如在leafNode就停止
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        //sanity check
        if (root == null) {
            return new LinkedList<>();
        }
        List<String> res = new LinkedList<>();
        backTracking(root, "", res);
        return res;
    }

    private void backTracking(TreeNode root, String prevPath, List<String> res) {
        //base case
        if (root.left == null && root.right == null) {
            prevPath += root.val;
            res.add(prevPath);
            return;
        }
        //update curPath
        prevPath = prevPath + root.val + "->";

        //recursion
        if (root.left != null) {
            backTracking(root.left, prevPath, res);
        }
        if (root.right != null) {
            backTracking(root.right, prevPath, res);
        }
    }
}
