package Array.Plan2;

/**理解
 * 难点一：j <= k
 * 难点二：
 *    nums[j] == 2时候，与k交换后，只能k--，不可j++
 */
public class SortColors {
    public void sortColors(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        while (j <= k) {  //最后一位j与k重合时候，nums[j]仍是未知元素，需判断
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {  //同理，交换过去的nums[j]是未知数，需判断
                swap(nums, j, k);
                k--;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
