package Tree.Plan9;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : to_delete) {
            set.add(i);
        }
        delete(root, set, res);
        /**
         易错点：要检查最后root是否被删除了，如果没有，就也要单独加入res中
         */
        if (!set.contains(root.val)) {
            res.add(root);
        }
        return res;
    }

    private TreeNode delete(TreeNode root, Set<Integer> set, List<TreeNode> res) {
        //base case
        if (root == null) {
            return root;
        }
        //recursion
        TreeNode l = delete(root.left, set, res);
        TreeNode r = delete(root.right, set, res);
        //induction rule
        if (set.contains(root.val)) {
            if (l != null) {
                res.add(l);
            }
            if (r != null) {
                res.add(r);
            }
            return null;
        }
        //connect
        root.left = l;
        root.right = r;
        //return val
        return root;
    }
}

/**
 * Sol1b.简化代码：recursion和connect可以合并
 */
class SolDNRF1b {
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        Set<Integer> set = new HashSet<>();
        for (int i : to_delete) {
            set.add(i);
        }
        delete(root, set, res);
        /**
         易错点：要检查最后root是否被删除了，如果没有，就也要单独加入res中
         */
        if (!set.contains(root.val)) {
            res.add(root);
        }
        return res;
    }

    private TreeNode delete(TreeNode root, Set<Integer> set, List<TreeNode> res) {
        //base case
        if (root == null) {
            return root;
        }
        //recursion + connect
        root.left = delete(root.left, set, res);
        root.right = delete(root.right, set, res);
        //induction rule
        /**
         因为上面已经connect，所以这里直接判断root.left, root.right即可
         */
        if (set.contains(root.val)) {
            if (root.left != null) {
                res.add(root.left);
            }
            if (root.right != null) {
                res.add(root.right);
            }
            return null;
        }
        //return val
        return root;
    }
}

