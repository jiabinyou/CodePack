package SW.Plan0;

/**
 经典FS-SW
 因为window size固定，所以s pointe可以省略
 slow idx = fast idx - window size
 */
public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //FS-SW
        double glbMax = Double.NEGATIVE_INFINITY; //double max = Integer.MIN_VALUE * 1.0;
        double preSum = 0.0;
        for (int j = 0; j < nums.length; j++) {
            //add fast
            preSum += nums[j];
            //remove slow
            if (j >= k) {
                preSum -= nums[j - k];
            }
            //check res
            if (j >= k - 1) {
                glbMax = Math.max(glbMax, preSum / k);
            }
        }
        return glbMax;
    }
}
