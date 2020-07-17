package Array.Plan1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    /**
     保证input sorted后才能够使用pointers
     此题input dup，res无dup
     3 sum，需在k，（i， j）两个维度dedup
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }

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
                //check res
                if (sum == 0) {
                    List<Integer> oneRes = new ArrayList<>();
                    oneRes.add(nums[i]);
                    oneRes.add(nums[j]);
                    oneRes.add(nums[k]);
                    res.add(new ArrayList<>(oneRes));
                    //dedup at i,j
                    while (j - 1 >= 0 && nums[j - 1] == nums[j]) {
                        j--;
                    }
                    j--;
                } else if (sum < 0) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }
}
