package Array.Plan4;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int[] leftPre = new int[nums.length];
        Arrays.fill(leftPre, 1);
        for (int i = 1; i < nums.length; i++) {
            leftPre[i] = leftPre[i - 1] * nums[i - 1];
        }

        int[] rightPre = new int[nums.length];
        Arrays.fill(rightPre, 1);
        for (int i = nums.length - 2; i >= 0; i--) {
            rightPre[i] = rightPre[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            leftPre[i] *= rightPre[i];
        }

        return leftPre;
    }
}
