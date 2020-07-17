package Tree.Plan3;

import Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 traverse path ， 且root to leaf直上直下path
 所以首先设计经典backTracking
 side effect: prevSum，List<Integer> prevPath
 base case: leafNode  (只要在leafNode判断是否等于sum即可）
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        List<Integer> prevPath = new ArrayList<>();
        backTracking(root, 0, prevPath, res, sum);
        return res;
    }

    private void backTracking(TreeNode root, int prevSum, List<Integer> prevPath, List<List<Integer>> res, int sum) {
        //base case
        if (root.left == null && root.right == null) {
            prevSum += root.val;
            prevPath.add(root.val);
            if (prevSum == sum) {
                res.add(new ArrayList<>(prevPath));
            }
            prevPath.remove(prevPath.size() - 1);  //recover
        }
        //update cur path
        prevSum += root.val;
        prevPath.add(root.val);
        //recursion
        if (root.left != null) {
            backTracking(root.left, prevSum, prevPath, res, sum);
            prevPath.remove(prevPath.size() - 1);
        }
        if (root.right != null) {
            backTracking(root.right, prevSum, prevPath, res, sum);
            prevPath.remove(prevPath.size() - 1);
        }
    }
}

/**
 *简化上面的代码
 *  可以直接用target sum代替prevSum信息，每往下走一步，减掉root.val，最后在leaf判断是否与现在的sum相等即可
 */
class SolPSII1a {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        List<Integer> prevPath = new ArrayList<>();
        backTracking(root, sum, prevPath, res);
        return res;
    }

    private void backTracking(TreeNode root, int remainSum, List<Integer> prevPath, List<List<Integer>> res) {
        //base case
        if (root.left == null && root.right == null) {
            remainSum -= root.val;
            prevPath.add(root.val);
            if (remainSum == 0) {
                res.add(new ArrayList<>(prevPath));
            }
            prevPath.remove(prevPath.size() - 1);  //recover
        }
        //update cur path
        remainSum -= root.val;
        prevPath.add(root.val);
        //recursion
        if (root.left != null) {
            backTracking(root.left, remainSum, prevPath, res);
            prevPath.remove(prevPath.size() - 1);
        }
        if (root.right != null) {
            backTracking(root.right, remainSum, prevPath, res);
            prevPath.remove(prevPath.size() - 1);
        }
    }
}

/**
 同pathsum I，这里我们可以使用base caes == null来继续合并case，简化代码
 */
class SolPBII1b {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        List<Integer> prevPath = new ArrayList<>();
        backTracking(root, sum, prevPath, res);
        return res;
    }

    private void backTracking(TreeNode root, int remainSum, List<Integer> prevPath, List<List<Integer>> res) {
        //base case
        if (root == null) {
            return;
        }
        //判res
        if (root.left == null && root.right == null) {
            remainSum -= root.val;
            prevPath.add(root.val);
            if (remainSum == 0) {
                res.add(new ArrayList<>(prevPath));
            }
            prevPath.remove(prevPath.size() - 1);  //recover
        }
        //update cur path
        remainSum -= root.val;
        prevPath.add(root.val);
        //recursion
        backTracking(root.left, remainSum, prevPath, res);
        backTracking(root.right, remainSum, prevPath, res);
        prevPath.remove(prevPath.size() - 1);   //recover
    }
}

/**
 * 这里我们观察，base == leaf时候，和update cur path所做事情相同，那可以先做update path，继续简化代码
 */
class SolPSII1c {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (root == null) {
            return res;
        }
        List<Integer> prevPath = new ArrayList<>();
        backTracking(root, sum, prevPath, res);
        return res;
    }

    private void backTracking(TreeNode root, int remainSum, List<Integer> prevPath, List<List<Integer>> res) {
        //base case
        if (root == null) {
            return;
        }
        //update cur path
        remainSum -= root.val;
        prevPath.add(root.val);
        //判res
        if (root.left == null && root.right == null) {
            if (remainSum == 0) {
                res.add(new ArrayList<>(prevPath));
            }
        }
        //recursion
        backTracking(root.left, remainSum, prevPath, res);
        backTracking(root.right, remainSum, prevPath, res);
        /**
         * 此时在这里做一次recover即可，和update path相对应
         */
        prevPath.remove(prevPath.size() - 1);   //recover
    }
}

