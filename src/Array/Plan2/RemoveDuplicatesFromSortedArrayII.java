package Array.Plan2;

/**
 slow, fast pointer
 slow excluding

 dup留两个
 这个sol前提，必须保证input sorted
 */
public class RemoveDuplicatesFromSortedArrayII {
    public int removeDuplicates(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 2;
        for (int fast = 2; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 2]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}

