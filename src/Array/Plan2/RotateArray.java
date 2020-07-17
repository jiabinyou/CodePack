package Array.Plan2;

/**
 arr向右rotate k位
 k %= len
 reverse[0, len - 1]
 reverse[0, k - 1]
 reverse[k, len - 1]
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        //sanity check
        if (nums == null || nums.length == 0 || k == 0 || k == nums.length) {
            return;
        }
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int l, int r) {
        while (l < r) {
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;

            l++;
            r--;
        }
    }
}


