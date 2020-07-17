package Array.Plan2;

public class MoveZeroes {
    /**
     maintaining the relative order --> slow, fast pointer
     */
    public void moveZeroes(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }
        while (slow < nums.length) {
            nums[slow++] = 0;
        }
    }
}
