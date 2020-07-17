package Array.Plan1;

import java.util.Arrays;

/**
 2 diff问题，找#pairs，并且input dup，res无dup
 */
public class KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = 0;
        Arrays.sort(nums);
        int i = 0;
        int j = 1;
        while (j < nums.length) {
            if (nums[i] == nums[j] - k) {
                res++;
                //dedup (i,j)
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    j++;
                }
                j++;
            } else if (nums[i] < nums[j] - k) {
                i++;
            } else {
                j++;
            }
            /**
             难点：如果恰k == 0， 那么i == j也会被数进res，然而不是
             所以i == j时候要特殊处理，移走一个pointer（可j前移以为相当于重新开始）
             */
            if (i == j) {
                j++;
            }
        }
        return res;
    }
}
