package Array.Plan5;

/**
 SOl. preSum
 重要！！input没有负数，所以使用arr记录的preSum具有升序sorted性质，
 可第三次优化：
 可以直接在preSum[]上应用two pointers
 因为题目要求minimum length，转化成NFS-SW on preSum[]
 O(N) O(N)

 可第四次优化：
 又因为preSum[]只从l to r遍历一遍，不需要回头look up
 所以可以使用sum直接边遍历边计算
 O(N) O(1)
*/

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int s, int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int glbShortest = Integer.MAX_VALUE;
        int sum = 0;  //preSum

        //NFS-SW
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            //add fast
            sum += nums[fast];  //update preSum
            ///remove slow
            while (sum >= s) {
                glbShortest = Math.min(glbShortest, fast - slow + 1);
                sum -= nums[slow];
                slow++;
            }
        }
        return glbShortest == Integer.MAX_VALUE ? 0 : glbShortest;
    }
}


