package Array.Plan1;

import java.util.Arrays;

/**
 res(#pairs) 允许duplicate
 */
public class ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }

        //prepare input to apply pointers sol
        Arrays.sort(nums);
        int count = 0;
        for (int k = 2; k < nums.length; k++) {
            int i = 0;
            int j = k - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                //check res
                if (sum < target) {
                    count += j - i; //res(#pairs) 允许duplicate 写法
                    i++;
                } else {
                    j--;
                }
            }
        }
        return count;
    }
}


