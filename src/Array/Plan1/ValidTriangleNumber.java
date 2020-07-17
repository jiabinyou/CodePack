package Array.Plan1;

/**
 input dup, res(#pairs) dup
 */

import java.util.Arrays;

/**
 转化：3Sum
 固定维度k，在i，j维度找nums[i] + nums[j] > nums[k] 的pair数
 */
public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        //3 sum res dup
        //prepare input
        Arrays.sort(nums);
        int res = 0;
        //fix k
        for (int k = 2; k < nums.length; k++) {
            //2 sum
            int i = 0;
            int j = k - 1;
            while (i < j) {
                int sum = nums[i] + nums[j];
                if (sum > nums[k]) {
                    res += j - i; //res(#pairs) 允许duplicate 写法
                    j--;
                } else {
                    i++;
                }
            }
        }
        return res;
    }
}
