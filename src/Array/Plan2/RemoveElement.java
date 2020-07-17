package Array.Plan2;

/**
 slow, fast pointer
 slow excluding
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}
