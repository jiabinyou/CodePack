package SW.Plan1;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Sol1. FS-SW
 */
public class ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return false;
        }
        //representative ds
        TreeSet<Integer> ts = new TreeSet<>();
        //FS-SW
        for (int j = 0; j < nums.length; j++) {
            //check res
            Integer floor = ts.floor(nums[j]);
            Integer ceiling = ts.ceiling(nums[j]);
            /**不能写nums[j] - floor <= t, ceiling - nums[j] <= t减法，会溢出，只能用加法*/
            if (floor != null && nums[j] <= t + floor ||
                    ceiling != null && ceiling <= t + nums[j] ) {
                return true;
            }
            //add fast
            ts.add(nums[j]);
            //remove slow
            if (j >= k) {
                ts.remove(nums[j - k]);
            }
        }
        return false;
    }
}

/**
 * sOL2. use size d + 1 bucket
 */
class SolCD2 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k == 0 || t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>();
        for (int fast = 0; fast < nums.length; fast++) {
            long bucketIndex = getBucketIndex(nums, fast, t);
            if (map.put(bucketIndex, (long) nums[fast]) != null) {
                return true;
            }
            Long left = map.get(bucketIndex - 1);
            if (left != null && nums[fast] - left <= t) {
                return true;
            }
            Long right = map.get(bucketIndex + 1);
            if (right != null && right - nums[fast] <= t) {
                return true;
            }
            if (fast >= k) {
                map.remove(getBucketIndex(nums, fast - k, t));
            }
        }
        return false;
    }

    private long getBucketIndex(int[] nums, int i, int t) {
        return nums[i] < 0 ? nums[i] / ((long) t + 1) - 1 : nums[i] / ((long) t + 1);

    }
}
