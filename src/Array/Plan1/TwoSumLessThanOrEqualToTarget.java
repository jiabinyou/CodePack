package Array.Plan1;

import java.util.Arrays;

public class TwoSumLessThanOrEqualToTarget {
    /**Sol1.Sorted + two pointer
     1.输入没有说sorted,可以先sorted再转为two sum（相向而行two pointer）
     2.未说明input有dup，假设input没有dup
     */
    public int twoSum5(int[] nums, int target) {
        // sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;

        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] <= target) {
                count+= (j - i); /**难点：说明在当前i，搭配的j从[i+1, j]都符合，共(j - i)个pair*/
                i++;
                //j--;
            } else {
                j--;
            }
        }
        return count;
    }
}
