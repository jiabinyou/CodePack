package DP.TrainingPlan.Plan2;

public class TargetSum {
    /**
     DFS3
     level：每个数字是一层
     branch：两个, +或者-
     */
    public int findTargetSumWays(int[] nums, int s) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] count = new int[]{0};
        backTracking(nums, 0, s, count);
        return count[0];
    }

    private void backTracking(int[] nums, int level, int remainedTarget, int[] count) {
        //base case
        if (level == nums.length) {  //pruning + check Res /**易错：res要先check*/
            if (remainedTarget == 0) {  //check res
                count[0]++;
            }
            return;
        }
        /**易错：下面这个常规pruning不能做：因为这一题有负数，sum <0并不代表可以结束*/
        // if (remainedTarget < 0) {  //pruning
        //     return;
        // }
        //recursion + recover
        backTracking(nums, level + 1, remainedTarget - nums[level], count); //"+"branch
        backTracking(nums, level + 1, remainedTarget + nums[level], count); //"-"branch
    }
}


