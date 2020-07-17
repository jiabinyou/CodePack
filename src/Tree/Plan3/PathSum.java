package Tree.Plan3;

/**
 traverse path ， 且root to leaf直上直下path
 所以首先设计经典backTracking
 side effect: prevSum
 base case: leafNode 或者null（null的话有条件，看下面） 都可以 (只要在leafNode判断是否等于sum即可）
 */

/**
 * 注意：这道题的假设是：只要root == null，返回就是false
 */

import Tree.TreeNode;

/**
 * 经典backTracking:
 *   base = leafNode;
 *   使用glb
 */
public class PathSum {
    boolean hasPathSum = false;
    public boolean hasPathSum(TreeNode root, int sum) {
        //sanity check
        if (root == null) {
            return false;
        }
        backTracking(root, 0, sum);
        return hasPathSum;
    }

    private void backTracking(TreeNode root, int prevSum, int sum) {
        //base case
        if (root.left == null &&  root.right == null) {
            prevSum += root.val;
            if (prevSum == sum) {
                hasPathSum = true;
            }
        }
        //update cur path
        prevSum += root.val;
        //recursion
        if (root.left != null) {
            backTracking(root.left, prevSum, sum);
        }
        if (root.right != null) {
            backTracking(root.right, prevSum, sum);
        }
    }
}

/**
 * 优化版本backTracking：
 *   base = leafNode;
 *   使用return value优化glb
 */
class SolPS2 {
    public boolean hasPathSum(TreeNode root, int sum) {
        //sanity check
        if (root == null) {
            return false;
        }
        return backTracking(root, 0, sum);
    }

    private boolean backTracking(TreeNode root, int prevSum, int sum) {
        //base case
        if (root.left == null &&  root.right == null) {
            prevSum += root.val;
            if (prevSum == sum) {
                return true;
            }
        }
        //update cur path
        prevSum += root.val;
        //recursion
        if (root.left != null) {
            if (backTracking(root.left, prevSum, sum)) {  //只要有一个是true，就可以直接返回true
                return true;
            }
        }
        if (root.right != null) {
            if (backTracking(root.right, prevSum, sum)) {
                return true;
            }
        }
        //return value
        return false;
    }
}

/**
 *简化上面的代码
 *  可以直接用target sum代替prevSum信息，每往下走一步，减掉root.val，最后在leaf判断是否与现在的sum相等即可
 */
class SolPS2a {
    public boolean hasPathSum(TreeNode root, int sum) {
        //sanity check
        if (root == null) {
            return false;
        }
        return backTracking(root, sum);
    }

    private boolean backTracking(TreeNode root, int remainSum) {
        //base case
        if (root.left == null &&  root.right == null) {
            return root.val == remainSum;
        }
        //update cur path
        remainSum -= root.val;
        //recursion
        if (root.left != null) {
            if (backTracking(root.left, remainSum)) {  //只要有一个是true，就可以直接返回true
                return true;
            }
        }
        if (root.right != null) {
            if (backTracking(root.right, remainSum)) {
                return true;
            }
        }
        //return value
        return false;
    }
}

/**这种写法是难点，虽然简洁，但是条件多，不建议
 * 注意：这道题只有先简化代码，使用sum来代替prevPath信息即可，才能将base case设置在null
 * 因为在null的话，不知道leafNode在什么时候，所以每一步都要检查pathSum与target关系，所以只有我们能得到实时的target时候才可以
 *
 *
 * base == null是控制recursion停止（便于合并case）
 * res要在leafNode判断，所以这一步实质上也属于base case要做的事情，不能少
 *
 */
class SolPS2b {
    public boolean hasPathSum(TreeNode root, int sum) {
        //sanity check
        if (root == null) {
            return false;
        }
        return backTracking(root, sum);
    }

    private boolean backTracking(TreeNode root, int remainSum) {
        //base case
        if (root == null) {
            return false;
        }
        //判res
        if (root.left == null &&  root.right == null) {
            return root.val == remainSum;
        }
        //update cur path
        remainSum -= root.val;
        //recursion
        return (backTracking(root.left, remainSum)) || (backTracking(root.right, remainSum));
    }
}

/**
 * 这样一来就和主函数重复了，可继续简化
 */
class SolPS2c {
    public boolean hasPathSum(TreeNode root, int sum) {
        //sanity check + base case
        if (root == null) {
            return false;
        }
        ////判res
        if (root.left == null &&  root.right == null) {
            return root.val == sum;
        }
        //update cur path
        sum -= root.val;
        //recursion
        return (hasPathSum(root.left, sum)) || (hasPathSum(root.right, sum));
    }
}





