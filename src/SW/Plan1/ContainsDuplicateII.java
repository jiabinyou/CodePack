package SW.Plan1;

import java.util.HashSet;
import java.util.Set;

/**
 Sol1. FS-SW
 即题目要求：是否出现size = k的window中有duplicate
 */
public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return false;
        }
        //representative ds
        Set<Integer> set = new HashSet<>();
        //NF-SW
        for (int j = 0; j < nums.length; j++) {
            //add fast + check res
            if (!set.add(nums[j])) {
                return true;
            }
            //remove slow
            if (j >= k) {
                set.remove(nums[j - k]);
            }
        }
        return false;
    }
}



