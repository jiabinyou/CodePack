package Array.Plan2;

/**
 slow, fast pointer
 slow excluding

 dup留一个
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        //sanity check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 1;
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow - 1]) {
                nums[slow++] = nums[fast];
            }
        }
        return slow;
    }
}

