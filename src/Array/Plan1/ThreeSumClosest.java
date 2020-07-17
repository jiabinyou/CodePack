package Array.Plan1;

import java.util.Arrays;

public class ThreeSumClosest {
    /**
     保证input sorted后才能够使用pointers
     此题input dup，res无dup
     3 sum，需在k，（i， j）两个维度dedup
     */
    public int threeSumClosest(int[] nums, int target) {
        int minDiff = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;

        //prepare input to apply pointers sol
        Arrays.sort(nums);
        for (int k = 2; k < nums.length; k++) {
            //dedup at k
            while (k + 1 < nums.length && nums[k] == nums[k + 1]) {
                k++;
            }
            int i = 0;
            int j = k - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                //update res
                int newDiff = Math.abs(target - sum);
                if (newDiff < minDiff) {
                    minDiff = newDiff;
                    res = sum;
                }
                //check res
                if (sum == target) {
                    return target;
                    // //dedup at i,j -->没必要写了，就一个res，都已经输出了
                    // while (j - 1 >= 0 && nums[j - 1] == nums[j]) {
                    //     j--;
                    // }
                    // j--;
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }

            }
        }
        return res;
    }
}

/**
 * 下面这种写法，case判断更加严格，无重复
 */
class SolTSC1b {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int minDiff = Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for (int k = 2; k < nums.length; k++) {
            int i = 0;
            int j = k - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == target) {
                    return target;
                } else if (sum < target) {
                    if (target - sum < minDiff) {
                        minDiff = target - sum;
                        res = sum;
                    }
                    i++;
                } else {
                    if (sum - target < minDiff) {
                        minDiff = sum - target;
                        res = sum;
                    }
                    j--;
                }
            }
        }
        return res;
    }
}
