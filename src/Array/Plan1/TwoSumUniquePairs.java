package Array.Plan1;

import java.util.Arrays;


public class TwoSumUniquePairs {
    /**Sol1.Sorted + two pointer
     1.输入没有说sorted,可以先sorted再转为two sum（相向而行two pointer）
     2.input有dup，res要求unique，所以计算要dedup

     TC:O(NLOGN) + O(N)  -->因为需要sort
     SC: 根据选用的sort决定
     */
    public int twoSum6(int[] nums, int target) {
        // sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int count = 0;

        int i = 0;
        int j = nums.length - 1;
        while (i < j) { /**假设以j为基准dedup*/
            if (nums[i] + nums[j] == target) {
                count++;
                while (j >= 0 && nums[j] == nums[j - 1]) {
                    j--;
                }
                i++;
                j--;
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return count;
    }
}

/**过例子：
 *        [1,  1,  2,  45,  46,  46]     target = 47
 *idx:     0   1   2    3    4    5           res
 *         i                      j            1
 *                i         j
 *                i     j                      2 ->finish
 *
 * */
