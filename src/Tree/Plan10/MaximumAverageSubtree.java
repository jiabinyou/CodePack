package Tree.Plan10;

import Tree.TreeNode;

/**人字形问题，pure recursion解决
 return value：以自己为起点的subtree 的 node sum 和 #nodes
 站在每一层要做的事情：
 思考怎样计算当前层结果？
 cur layer node sum = left.sum + right.sum + root.val
 cur layer #nodes = left.num + right.num + 1
 思考怎样更新glbMaxSum？
 先计算cur layer average = cur layer node sum / cur layer #nodes
 比较glbMaxSum, cur layer average大小即可
 */
public class MaximumAverageSubtree {
    double glbMaxSum = Double.MIN_VALUE;
    public double maximumAverageSubtree(TreeNode root) {   /**注意：要求返回的是double，因为int除法向下取整结果不准，全部用double*/
        //sanity check
        if (root == null) {
            return 0;
        }
        pureRecursion(root);
        return glbMaxSum;
    }

    private ReturnType pureRecursion(TreeNode root) {
        //base case
        if (root == null) {
            return new ReturnType(0, 0);
        }
        //recursion
        ReturnType left = pureRecursion(root.left);
        ReturnType right = pureRecursion(root.right);
        //induction rule
        double curSum = left.sum + right.sum + root.val;
        double curNum = left.num + right.num + 1;
        //update glb
        double curAve = curSum / curNum;
        glbMaxSum = Math.max(glbMaxSum, curAve);
        //return val
        return new ReturnType(curNum, curSum);
    }
}

class ReturnType{
    double num;   //subtree #nodes
    double sum;   //subtree node sum
    public ReturnType(double num, double sum) {
        this.num = num;
        this.sum = sum;
    }
}


/**
 * 简化代码：可以将wrapper class简化成double[]
 * */
class MaximumAverageSubtreeSol2 {
    public double maximumAverageSubtree(TreeNode root) {
        //sanity check
        if (root == null) {
            return 0;
        }
        double[] glbMax = new double[]{Integer.MIN_VALUE}; //max subtree average
        pureRecursion(root, glbMax);
        return glbMax[0];
    }

    //return value: cur subtree [#node, node value sum]
    //因为是subtree，所以必须是人字形，不能直上直下
    private double[] pureRecursion(TreeNode root, double[] glbMax) {
        //base case
        if (root == null) {
            return new double[]{0, 0};
        }
        //move down
        double[] left = pureRecursion(root.left, glbMax);
        double[] right = pureRecursion(root.right, glbMax);
        //induction rule
        double curNum = 1 + left[0] + right[0];
        double curSum = root.val + left[1] + right[1];
        glbMax[0] = Math.max(glbMax[0], curSum / curNum);
        return new double[]{curNum, curSum};
    }
}