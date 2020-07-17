package Array.Plan1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        //sanity check
        if (nums == null || nums.length == 0) {
            return res;
        }
        for (int l = 3; l < nums.length; l++) {
            while (l + 1 < nums.length && nums[l] == nums[l + 1]) {
                l++;
            }
            for (int k = 2; k < l; k++) {
                while (k + 1 < l && nums[k] == nums[k + 1]) {
                    k++;
                }
                int i = 0;
                int j = k - 1;
                while (i < j) {
                    int sum = nums[i] + nums[j] + nums[k] + nums[l];
                    if (sum == target) {
                        List<Integer> oneRes = new ArrayList<>();
                        oneRes.add(nums[i]);
                        oneRes.add(nums[j]);
                        oneRes.add(nums[k]);
                        oneRes.add(nums[l]);
                        res.add(oneRes);
                        while (j - 1 >= 0 && nums[j] == nums[j - 1]) {
                            j--;
                        }
                        j--;
                    } else if (sum < target) {
                        i++;
                    } else {
                        j--;
                    }
                }
            }
        }
        return res;
    }
}
