package Tree.Plan3;

import Tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 traverse path, 直上直下，any to any node
 !!这道题不简单，实质上是preSum[]相关
 找是否存在：preSum[] DS 使用set足够
 找# path :preSum[] DS 使用需要map
 先设计经典backTracking，并且每一步都要判res
 side effect: prevSum,
 base case: null即可，在leafnode没有特别要做的事情
 */
public class PathSumIII {
    int glbNum = 0;
    public int pathSum(TreeNode root, int sum) {
        //sanity check
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<preSum, fre>
        map.put(0, 1); //preSum[] DS init!!
        backTracking(root, 0, map, sum);
        return glbNum;
    }

    private void backTracking(TreeNode root, int prevSum, Map<Integer, Integer> map, int target) {
        //base case
        if (root == null) {
            return;
        }
        //update path
        prevSum += root.val;
        //check res
        glbNum += map.get(prevSum - target) == null ? 0 : map.get(prevSum - target);
        map.put(prevSum, map.getOrDefault(prevSum, 0) + 1);
        //recursion
        backTracking(root.left, prevSum, map, target);
        backTracking(root.right, prevSum, map, target);
        //recover
        map.put(prevSum, map.get(prevSum) - 1);
    }
}

/**
 优化版本backTracking：
    使用return value优化glb
 */
class SolPSIII1a {
    public int pathSum(TreeNode root, int sum) {
        //sanity check
        if (root == null) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>(); //<preSum, fre>
        map.put(0, 1); //preSum[] DS init!!
        return backTracking(root, 0, map, sum);
    }

    private int backTracking(TreeNode root, int prevSum, Map<Integer, Integer> map, int target) {
        //base case
        if (root == null) {
            return 0;
        }
        //update path
        prevSum += root.val;
        //check res
        int curNum = 0;
        curNum += map.get(prevSum - target) == null ? 0 : map.get(prevSum - target);
        map.put(prevSum, map.getOrDefault(prevSum, 0) + 1);
        //recursion
        curNum += backTracking(root.left, prevSum, map, target);
        curNum += backTracking(root.right, prevSum, map, target);
        //recover
        map.put(prevSum, map.get(prevSum) - 1);
        //return value
        return curNum;
    }
}

