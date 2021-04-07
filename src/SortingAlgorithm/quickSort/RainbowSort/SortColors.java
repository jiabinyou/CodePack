package SortingAlgorithm.quickSort.RainbowSort;

public class SortColors {
    public void sortColors(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return;
        }
        int i = 0;
        int j = 0;
        int k = nums.length - 1;
        while (j <= k) {
            if (nums[j] == 0) {
                swap(nums, i, j);
                i++;
                j++;
            } else if (nums[j] == 1) {
                j++;
            } else {
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
