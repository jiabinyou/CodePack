package SW.Plan1;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Sol1: use HashSet → Time: O(n), Space: O(n)
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int j = 0; j < nums.length; j++) {
            if (!set.add(nums[j])) {
                return true;
            }
        }
        return false;
    }
}

/**
 * Sol2: sort + traverse   Time: O(nlogn), Space: O(1)
 */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
