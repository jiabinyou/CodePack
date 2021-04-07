package Array.Plan1;

import java.util.Arrays;
/**未说input sorted，可将input sort后，用two pointer*/
//two sum合并区间，相向而行two pointer
public class TwoSumGreaterThanTarget {
    public int twoSum2(int[] nums, int target) {
        // santy check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;

        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] > target) { //此时以j为基准，i和比i大的都能和j组成符合的pairs
                count += j - i;
                j--;
            } else {  //其他都偏小，i++
                i++;
            }
        }
        return count;
    }
}
