package Array.Plan6;

public class WiggleSort {
    public void wiggleSort(int[] nums) {
        //sanity check
        if (nums == null || nums.length < 2) {
            return;
        }
        int flag = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if ((nums[i + 1] - nums[i]) * flag < 0) {
                swap(nums, i, i + 1);
            }
            flag *= -1;
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}



